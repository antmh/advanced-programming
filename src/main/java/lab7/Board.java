package lab7;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Board {
    private int graph[][];
    private Random random;
    private int edges;
    private int currentPlayer;
    private int players;
    private final int MAXIMUM_TURNS = 10;
    private int turns;
    private boolean ended;

    public Board(int tokensNumber, int n, int players) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (tokensNumber < 0) {
            throw new IllegalArgumentException("The number of tokens must be positive");
        }
        this.players = players;
        currentPlayer = 0;
        turns = 0;
        edges = tokensNumber;
        graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(graph[i], 0);
        }
        random = new Random();
        for (int i = 0; i < tokensNumber; ++i) {
            int from, to;
            do {
                from = random.nextInt(n);
                to = random.nextInt(n);
            } while (graph[from][to] > 0 || from == to);
            int value = 1 + random.nextInt(100);
            graph[from][to] = value;
        }
    }

    public synchronized boolean takeTurn() throws InterruptedException {
        if (isOver()) {
            return false;
        }
        while (!Thread.currentThread().getName().equals(Integer.toString(currentPlayer))) {
            if (isOver()) {
                return false;
            }
            wait();
        }
        return true;
    }

    private void finishTurn() {
        currentPlayer = (currentPlayer + 1) % players;
        if (currentPlayer == 0) {
            ++turns;
        }
        notifyAll();
    }

    public synchronized Optional<Token> getTokenAt(int first, int second) {
        if (first < 1 || first > graph.length || second < 1 || second > graph.length) {
            throw new IllegalArgumentException("Token out of range");
        }
        if (graph[first - 1][second - 1] > 0) {
            return Optional.of(new Token(graph[first - 1][second - 1], first, second));
        }
        return Optional.empty();
    }

    public synchronized boolean takeToken(Token token) throws InterruptedException {
        if (token.getFirst() < 1 || token.getFirst() > graph.length || token.getSecond() < 1
                || token.getSecond() > graph.length || token.getValue() == 0) {
            throw new IllegalArgumentException("Token out of range");
        }
        if (!takeTurn() || graph[token.getFirst() - 1][token.getSecond() - 1] != token.getValue()) {
            return false;
        }
        graph[token.getFirst() - 1][token.getSecond() - 1] = 0;
        --edges;
        finishTurn();
        return true;
    }

    public synchronized void end() {
        if (!Thread.currentThread().getName().equals("timekeeper")) {
            throw new IllegalCallerException("Thread who is not timekeeper tried to end");
        }
        ended = true;
        notifyAll();
    }

    public synchronized int getSize() {
        return graph.length;
    }

    public synchronized boolean isOver() {
        if (ended || edges == 0 || turns == MAXIMUM_TURNS) {
            notifyAll();
            return true;
        }
        return false;
    }
}

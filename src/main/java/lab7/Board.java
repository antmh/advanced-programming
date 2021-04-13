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

    private boolean takeTurn() throws InterruptedException {
        if (isOver()) {
            finishTurn();
            return false;
        }
        while (Thread.currentThread().getName().equals(Integer.toString(currentPlayer))) {
            wait();
        }
        return true;
    }

    private void finishTurn() {
        currentPlayer = (currentPlayer + 1) % players;
        ++turns;
        notifyAll();
    }

    public synchronized Optional<Token> takeTokenWithFirst(int first) throws InterruptedException {
        if (!takeTurn()) {
            return Optional.empty();
        }
        for (int second = 1; second <= graph.length; ++second) {
            if (graph[first - 1][second - 1] > 0) {
                var token = new Token(graph[first - 1][second - 1], first, second);
                graph[first - 1][second - 1] = 0;
                --edges;
                finishTurn();
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    public synchronized Optional<Token> takeFirstToken() throws InterruptedException {
        if (!takeTurn()) {
            return Optional.empty();
        }
        for (int from = 0; from < graph.length; ++from) {
            for (int to = 0; to < graph.length; ++to) {
                if (graph[from][to] > 0) {
                    var token = new Token(graph[from][to], from + 1, to + 1);
                    graph[from][to] = 0;
                    --edges;
                    finishTurn();
                    return Optional.of(token);
                }
            }
        }
        return Optional.empty();
    }

    public synchronized boolean isOver() {
        return edges == 0 || turns == MAXIMUM_TURNS;
    }
}

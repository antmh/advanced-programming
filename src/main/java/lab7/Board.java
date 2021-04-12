package lab7;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Board {
    private int graph[][];
    private Random random;
    private int edges = 0;

    public Board(int tokensNumber, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (tokensNumber < 0) {
            throw new IllegalArgumentException("The number of tokens must be positive");
        }
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
            } while (graph[from][to] > 0);
            int value = 1 + random.nextInt(100);
            graph[from][to] = value;
        }
    }

    public synchronized Optional<Token> takeTokenWithFirst(int first) {
        for (int second = 1; second <= graph.length; ++second) {
            if (graph[first - 1][second - 1] > 0) {
                var token = new Token(graph[first - 1][second - 1], first, second);
                graph[first - 1][second - 1] = 0;
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    public synchronized Optional<Token> takeFirstToken() {
        for (int from = 0; from < graph.length; ++from) {
            for (int to = 0; to < graph.length; ++to) {
                if (graph[from][to] > 0) {
                    var token = new Token(graph[from][to], from + 1, to + 1);
                    graph[from][to] = 0;
                    return Optional.of(token);
                }
            }
        }
        return Optional.empty();
    }

    public synchronized boolean isEmpty() {
        return edges == 0;
    }
}

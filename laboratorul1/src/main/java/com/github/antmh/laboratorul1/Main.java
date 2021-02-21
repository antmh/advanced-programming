package com.github.antmh.laboratorul1;

import java.util.Random;
import java.util.Arrays;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

/**
 * @author Antonio Mihăeș
 */
public class Main {

    int digitsSum(int x) {
        int result = 0;

        while (x != 0) {
            result += x % 10;
            x /= 10;
        }

        return result;
    }

    void printLanguages() {
        String[] languages = { "C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java" };

        int n = (int) (1_000_000 * Math.random());
        n *= 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        while (n > 9) {
            n = digitsSum(n);
        }

        System.out.println("This semester I will learn " + languages[n]);
    }

    public void compulsory() {
        System.out.println("Hello World!");
        printLanguages();
    }

    int parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Exactly one command line argumet is required");
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException err) {
            throw new IllegalArgumentException("The argument must be an int");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("The argument must be greater than 0");
        }

        return n;
    }

    boolean[][] randomAdjacencyMatrix(int n) {
        Random generator = new Random();

        boolean[][] result = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            result[i][i] = false;
        }
        for (int line = 0; line < n - 1; ++line) {
            for (int column = 1 + line; column < n; ++column) {
                result[line][column] = generator.nextBoolean();
                result[column][line] = result[line][column];
            }
        }

        return result;
    }

    final int LARGE_MATRIX_SIZE = 100;

    void printMatrix(boolean[][] matrix) {
        if (matrix.length > LARGE_MATRIX_SIZE) {
            System.out.println("The matrix size is too big to be displayed");
            return;
        }

        for (int line = 0; line < matrix.length; ++line) {
            for (int column = 0; column < matrix.length; ++column) {
                System.out.print(matrix[line][column] ? 1 : 0);
                if (column != matrix.length - 1) {
                    System.out.print("┃");
                }
            }
            System.out.println();
            if (line != matrix.length - 1) {
                for (int column = 0; column < matrix.length; ++column) {
                    System.out.print("━");
                    if (column != matrix.length - 1) {
                        System.out.print("╋");
                    }
                }
            }
            System.out.println();
        }
    }

    Optional<boolean[]> getConnectedComponent(boolean[][] matrix, boolean[] visited) {
        boolean[] result = new boolean[visited.length];
        Arrays.fill(result, false);

        int root = -1;
        for (int i = 0; i < visited.length; ++i) {
            if (!visited[i]) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            return Optional.empty();
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(root);
        do {
            int v = stack.pop();
            if (!result[v]) {
                visited[v] = true;
                result[v] = true;
                for (int i = 0; i < matrix.length; ++i) {
                    if (matrix[v][i]) {
                        stack.push(i);
                    }
                }
            }
        } while (!stack.empty());

        return Optional.of(result);
    }

    boolean[][] getPartialTree(boolean[][] matrix, int root) {
        boolean[][] result = new boolean[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; ++row) {
            for (int column = 0; column < matrix.length; ++column) {
                result[row][column] = false;
            }
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(root);

        boolean[] visited = new boolean[matrix.length];
        Arrays.fill(visited, false);
        visited[root] = true;

        do {
            int v = queue.remove();
            for (int i = 0; i < matrix.length; ++i) {
                if (!visited[i] && matrix[v][i]) {
                    visited[i] = true;
                    queue.add(i);
                    result[v][i] = true;
                }
            }
        } while (!queue.isEmpty());

        return result;
    }

    void optional(String[] args) {
        int n;
        try {
            n = parseArgs(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        long startTime = 0;
        if (n > LARGE_MATRIX_SIZE) {
            startTime = System.currentTimeMillis();
        }

        boolean[][] matrix = randomAdjacencyMatrix(n);
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        System.out.println("Graph adjacency matrix:");
        printMatrix(matrix);
        Optional<boolean[]> component = getConnectedComponent(matrix, visited);
        boolean connected = true;
        for (int i = 0; i < n; ++i) {
            if (!component.get()[i]) {
                connected = false;
                break;
            }
        }
        if (connected) {
            System.out.println("The graph is connected");
            System.out.println("Partial tree:");
            printMatrix(getPartialTree(matrix, 0));
        } else {
            System.out.println("Connected components:");
            do {
                boolean[] value = component.get();
                for (int i = 0; i < n; ++i) {
                    if (value[i]) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
                component = getConnectedComponent(matrix, visited);
            } while (component.isPresent());
        }

        if (n > LARGE_MATRIX_SIZE) {
            long endTime = System.currentTimeMillis();
            System.out.println("Running time (in ms): " + (endTime - startTime));
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.compulsory();
        m.optional(args);
    }
}

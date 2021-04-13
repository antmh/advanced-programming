package lab7.players;

import java.util.Scanner;

import lab7.Board;

public class ManualPlayer extends Player {
    int size;

    public ManualPlayer(Board board) {
        super(board);
        size = board.getSize();
    }

    @Override
    public void run() {
        var scanner = new Scanner(System.in);
        while (true) {
            try {
                if (!board.takeTurn()) {
                    break;
                }
                printBoard();
                int first, second;
                while (true) {
                    try {
                        first = Integer.parseInt(scanner.next());
                        second = Integer.parseInt(scanner.next());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input");
                    }
                }
                var token = board.getTokenAt(first, second);
                if (token.isEmpty()) {
                    System.err.println("Token doesn't exist");
                } else if (!board.takeToken(token.get())) {
                    System.err.println("Could not take token");
                }
            } catch (InterruptedException e) {
                System.err.println(e);
                break;
            }
        }
        scanner.close();
    }

    private void printBoard() {
        for (int first = 1; first <= size; ++first) {
            for (int second = 1; second <= size; ++second) {
                var token = board.getTokenAt(first, second);
                if (token.isPresent()) {
                    System.out.println(token.get());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ManualPlayer [name=" + name + "]";
    }
}

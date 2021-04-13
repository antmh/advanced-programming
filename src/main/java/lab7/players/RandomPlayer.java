package lab7.players;

import java.util.Random;

import lab7.Board;

public class RandomPlayer extends Player {
    public RandomPlayer(Board board) {
        super(board);
    }

    @Override
    public void run() {
        try {
            var random = new Random();
            while (!board.isOver()) {
                int first = 1 + random.nextInt(board.getSize());
                int second = 1 + random.nextInt(board.getSize());
                var token = board.getTokenAt(first, second);
                if (token.isPresent()) {
                    board.takeToken(token.get());
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    @Override
    public String toString() {
        return "RandomPlayer [name=" + name + "]";
    }
}

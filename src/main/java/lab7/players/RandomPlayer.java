package lab7.players;

import java.util.Random;

public class RandomPlayer extends Player {
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
        return "Random player " + name;
    }
}

package lab7.players;

import lab7.Board;
import lab7.Token;

public class AutomatedPlayer extends Player {
    private int size;
    private int playerNumber;

    public AutomatedPlayer(Board board) {
        super(board);
        size = board.getSize();
    }

    @Override
    public void run() {
        playerNumber = Integer.parseInt(Thread.currentThread().getName());
        try {
            OUTER_LOOP: while (!board.isOver()) {
                var tokens = board.getTakenTokens().get(playerNumber);
                if (!tokens.isEmpty()) {
                    for (var token : tokens) {
                        if (takeChainedToken(token)) {
                            continue OUTER_LOOP;
                        }
                    }
                }
                if (takeFirstToken()) {
                    continue;
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        return;
    }

    private boolean takeFirstToken() throws InterruptedException {
        for (int first = 1; first <= size; ++first) {
            for (int second = 1; second <= size; ++second) {
                var optionalToken = board.getTokenAt(first, second);
                if (optionalToken.isPresent()) {
                    if (board.takeToken(optionalToken.get())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean takeChainedToken(Token token) throws InterruptedException {
        for (int second = 1; second <= size; ++second) {
            var optionalToken = board.getTokenAt(token.getFirst(), second);
            if (optionalToken.isPresent()) {
                if (board.takeToken(optionalToken.get())) {
                    return true;
                }
            }
        }
        return false;
    }
}

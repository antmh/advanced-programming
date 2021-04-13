package lab7.players;

import java.util.Optional;

import lab7.Board;
import lab7.Token;

public class AutomatedPlayer extends Player {
    private int size;

    public AutomatedPlayer(Board board) {
        super(board);
        size = board.getSize();
    }

    @Override
    public void run() {
        try {
            OUTER_LOOP: while (!board.isOver()) {
                if (!tokens.isEmpty()) {
                    for (var token : tokens) {
                        var taken = takeChainedToken(token);
                        if (taken.isPresent()) {
                            tokens.add(taken.get());
                            continue OUTER_LOOP;
                        }
                    }
                }
                var first = takeFirstToken();
                if (first.isPresent()) {
                    tokens.add(first.get());
                    continue;
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        return;
    }

    private Optional<Token> takeFirstToken() throws InterruptedException {
        for (int first = 1; first <= size; ++first) {
            for (int second = 1; second <= size; ++second) {
                var optionalToken = board.getTokenAt(first, second);
                if (optionalToken.isPresent()) {
                    var result = optionalToken.get();
                    if (board.takeToken(result)) {
                        return Optional.of(result);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private Optional<Token> takeChainedToken(Token token) throws InterruptedException {
        for (int second = 1; second <= size; ++second) {
            var optionalToken = board.getTokenAt(token.getFirst(), second);
            if (optionalToken.isPresent()) {
                var result = optionalToken.get();
                if (board.takeToken(result)) {
                    return Optional.of(result);
                }
            }
        }
        return Optional.empty();
    }
}

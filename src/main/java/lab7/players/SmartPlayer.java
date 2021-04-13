package lab7.players;

import java.util.Optional;

import lab7.Board;
import lab7.Token;

public class SmartPlayer extends Player {
    private int playerNumber;

    public SmartPlayer(Board board) {
        super(board);
    }

    @Override
    public void run() {
        playerNumber = Integer.parseInt(Thread.currentThread().getName());
        try {
            while (!board.isOver()) {
                takeNextToken();
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    private int getTokenScore(Token token) throws InterruptedException {
        int result = 0;
        for (var ourToken : board.getTakenTokens().get(playerNumber)) {
            if (ourToken.getSecond() == token.getFirst()) {
                result += token.getValue() * 2;
            }
        }
        for (int player = 0; player < board.getTakenTokens().size(); ++player) {
            if (player == playerNumber) {
                continue;
            }
            for (var othersToken : board.getTakenTokens().get(player)) {
                if (othersToken.getSecond() == token.getFirst()) {
                    result += token.getValue();
                }
            }
        }
        return result;
    }

    private void takeNextToken() throws InterruptedException {
        int maxScore = -1;
        Optional<Token> maxToken = Optional.empty();
        for (int first = 1; first <= board.getSize(); ++first) {
            for (int second = 1; second <= board.getSize(); ++second) {
                var token = board.getTokenAt(first, second);
                if (token.isPresent()) {
                    int score = getTokenScore(token.get());
                    if (score > maxScore) {
                        maxScore = score;
                        maxToken = token;
                    }
                }
            }
        }
        if (maxToken.isPresent()) {
            board.takeToken(maxToken.get());
        }
    }

    @Override
    public String toString() {
        return "SmartPlayer [name=" + name + "]";
    }
}

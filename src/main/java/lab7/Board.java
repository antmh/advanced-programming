package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Board {
    private List<Token> tokens;

    public Board(int tokensNumber, int n) {
        tokens = new ArrayList<>(tokensNumber);
        var random = new Random();
        for (int i = 0; i < tokensNumber; ++i) {
            Token token;
            do {
                token = new Token(1 + random.nextInt(n), 1 + random.nextInt(n));
            } while (tokenExists(token));
            tokens.add(token);
        }
    }
    
    public synchronized Optional<Token> takeTokenWithFirst(int first) {
        for (int i = 0; i < tokens.size(); ++i) {
            if (tokens.get(i).getFirst() == first) {
                return Optional.of(tokens.remove(i));
            }
        }
        return Optional.empty();
    }

    public synchronized Optional<Token> takeToken(int position) {
        if (position < tokens.size()) {
            return Optional.of(tokens.remove(position));
        }
        return Optional.empty();
    }

    public synchronized boolean tokenExists(Token token) {
        for (var boardToken : tokens) {
            if (boardToken.equals(token)) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized int size() {
        return tokens.size();
    }

    public synchronized boolean isEmpty() {
        return tokens.isEmpty();
    }

    @Override
    public String toString() {
        return "Board " + tokens;
    }
}

package lab7;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private Token[] tokens;

    public Board(int tokensNumber, int n) {
        tokens = new Token[tokensNumber];
        var random = new Random();
        for (int index = 0; index < tokens.length; ++index) {
            Token token;
            do {
                token = new Token(1 + random.nextInt(n + 1), 1 + random.nextInt(n + 1));
            } while (tokenExists(token, index));
            tokens[index] = token;
        }
    }

    private boolean tokenExists(Token token, int end) {
        for (int i = 0; i < end; ++i) {
            if (tokens[i].equals(token)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Board [" + Arrays.toString(tokens) + "]";
    }
}

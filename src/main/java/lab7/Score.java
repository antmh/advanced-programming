package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class Score {
    private boolean visited[];
    private Token[] tokens;
    private int score;
    private List<Token> chain;

    public Score(Set<Token> tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("tokens cannot be null");
        }
        this.tokens = (Token[]) tokens.toArray(new Token[tokens.size()]);
        calculate();
    }

    public int getValue() {
        return score;
    }

    private void calculate() {
        score = 0;
        chain = new ArrayList<>(tokens.length);
        visited = new boolean[tokens.length];
        Arrays.fill(visited, false);
        OUTER_LOOP: while (true) {
            for (int token = 0; token < tokens.length; ++token) {
                if (!visited[token]) {
                    chain.add(tokens[token]);
                    visited[token] = true;
                    explore();
                    continue OUTER_LOOP;
                }
            }
            break;
        }
    }

    private void explore() {
        for (int token = 0; token < tokens.length; ++token) {
            if (!visited[token] && chain.get(chain.size() - 1).getSecond() == tokens[token].getFirst()) {
                visited[token] = true;
                chain.add(tokens[token]);
                for (int previous = 0; previous < chain.size() - 1; ++previous) {
                    if (chain.get(previous).getFirst() == chain.get(chain.size() - 1).getSecond()) {
                        score = Math.max(score, getChainSegmentScore(previous, chain.size() - 1));
                        break;
                    }
                }
                explore();
            }
        }
        chain.remove(chain.size() - 1);
    }

    private int getChainSegmentScore(int first, int last) {
        int score = 0;
        for (int i = first; i <= last; ++i) {
            score += chain.get(i).getValue();
        }
        return score;
    }
}

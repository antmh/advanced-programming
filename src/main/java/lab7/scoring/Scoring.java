package lab7.scoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import lab7.Token;

public abstract class Scoring {
    private boolean visited[];
    private Token[] tokens;
    private int maxScore;
    private List<Token> sequence;

    public final int calculate(Set<Token> taken) {
        if (taken == null) {
            throw new IllegalArgumentException("tokens cannot be null");
        } 
        tokens = (Token[]) taken.toArray(new Token[taken.size()]);
        maxScore = 0;
        sequence = new ArrayList<>(tokens.length);
        visited = new boolean[tokens.length];
        Arrays.fill(visited, false);
        OUTER_LOOP: while (true) {
            for (int token = 0; token < tokens.length; ++token) {
                if (!visited[token]) {
                    sequence.add(tokens[token]);
                    visited[token] = true;
                    explore();
                    continue OUTER_LOOP;
                }
            }
            break;
        }
        return maxScore;
    }
    
    protected abstract int sequenceScore(List<Token> sequence);

    private void explore() {
        for (int token = 0; token < tokens.length; ++token) {
            if (!visited[token] && sequence.get(sequence.size() - 1).getSecond() == tokens[token].getFirst()) {
                visited[token] = true;
                sequence.add(tokens[token]);
                for (int previous = 0; previous < sequence.size() - 1; ++previous) {
                    if (sequence.get(previous).getFirst() == sequence.get(sequence.size() - 1).getSecond()) {
                        int score = sequenceScore(sequence);
                        if (score > maxScore) {
                            maxScore = score;
                        }
                        break;
                    }
                }
                explore();
            }
        }
        sequence.remove(sequence.size() - 1);
    }
}

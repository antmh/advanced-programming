package lab7.scoring;

import java.util.List;

import lab7.Token;

public class SequenceLengthScoring extends Scoring {
    @Override
    protected int sequenceScore(List<Token> sequence) {
        return sequence.size();
    }
}

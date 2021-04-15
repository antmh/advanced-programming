package lab7.scoring;

import java.util.List;

import lab7.Token;

public class SequenceValueScoring extends Scoring {
    @Override
    protected int sequenceScore(List<Token> sequence) {
        return sequence.stream().map(Token::getValue).reduce(Integer::sum).get();
    }

    @Override
    public String toString() {
        return "Sequence value scoring";
    }
}

package lab7.scoring;

import java.util.List;

import lab7.Token;

public class HamiltonianBonusScoring extends Scoring {
    private Scoring baseScoring;

    @Override
    protected int sequenceScore(List<Token> sequence) {
        int score = baseScoring.sequenceScore(sequence);
        if (game.getBoard().getSize() == sequence.size()) {
            return score * 2;
        }
        return score;
    }

    public Scoring getBaseScoring() {
        return baseScoring;
    }

    public void setBaseScoring(Scoring baseScoring) {
        if (baseScoring == null) {
            throw new IllegalArgumentException("baseScoring cannot be null");
        }
        this.baseScoring = baseScoring;
    }

    @Override
    public String toString() {
        return baseScoring + " with hamiltonian bonus";
    }
}

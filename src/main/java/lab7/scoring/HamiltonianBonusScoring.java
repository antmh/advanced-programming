package lab7.scoring;

import java.util.List;

import lab7.Token;

public class HamiltonianBonusScoring extends Scoring {
    private int nodes;
    private int bonusMultiplier;
    private Scoring baseScoring;

    @Override
    protected int sequenceScore(List<Token> sequence) {
        int score = baseScoring.sequenceScore(sequence);
        if (nodes == sequence.size()) {
            return score * bonusMultiplier;
        }
        return score;
    }

    public int getNodes() {
        return nodes;
    }

    public int getBonusMultiplier() {
        return bonusMultiplier;
    }

    public Scoring getBaseScoring() {
        return baseScoring;
    }

    public void setBonusMultiplier(int bonusMultiplier) {
        if (bonusMultiplier <= 1) {
            throw new IllegalArgumentException("bonusMultiplier must be greater than 1");
        }
        this.bonusMultiplier = bonusMultiplier;
    }

    public void setNodes(int nodes) {
        if (nodes < 1) {
            throw new IllegalArgumentException("nodes must be positive");
        }
        this.nodes = nodes;
    }

    public void setBaseScoring(Scoring baseScoring) {
        if (baseScoring == null) {
            throw new IllegalArgumentException("baseScoring cannot be null");
        }
        this.baseScoring = baseScoring;
    }
}

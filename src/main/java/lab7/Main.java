package lab7;

import lab7.scoring.HamiltonianBonusScoring;
import lab7.scoring.SequenceValueScoring;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        game.setN(100);
        game.setSmartPlayers(10);
        game.setRandomPlayers(10);
        game.setManualPlayer(false);
        game.setTokensNumber(1000);
        var scoring = new HamiltonianBonusScoring();
        scoring.setBaseScoring(new SequenceValueScoring());
        scoring.setNodes(game.getN());
        scoring.setBonusMultiplier(2);
        game.setScoring(scoring);
        game.play();
        game.printResult();
        game.printWinners();
    }
}

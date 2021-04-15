package lab7.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import lab7.Game;
import lab7.scoring.HamiltonianBonusScoring;
import lab7.scoring.Scoring;
import lab7.scoring.SequenceLengthScoring;
import lab7.scoring.SequenceValueScoring;

public class ControlPanel extends VBox {
    private Spinner<Integer> randomPlayersSpinner;
    private Spinner<Integer> smartPlayersSpinner;
    private Spinner<Integer> timeLimitSpinner;
    private CheckBox participateCheckBox;
    private ComboBox<Scoring> scoringComboBox;
    private Spinner<Integer> nSpinner;
    private Spinner<Integer> tokensSpinner;
    private Game game;

    public ControlPanel(Game game) {
        this.game = game;

        var randomPlayersLabel = new Label("Random players:");
        randomPlayersSpinner = new Spinner<>(0, 100, 0);
        randomPlayersSpinner.setEditable(true);

        var smartPlayersLabel = new Label("Smart players:");
        smartPlayersSpinner = new Spinner<>(0, 100, 0);
        smartPlayersSpinner.setEditable(true);

        var nLabel = new Label("N:");
        nSpinner = new Spinner<>(1, 10000, 10);
        nSpinner.setEditable(true);

        var tokensLabel = new Label("Tokens:");
        tokensSpinner = new Spinner<>(1, 10000, 50);
        tokensSpinner.setEditable(true);

        participateCheckBox = new CheckBox("Participate");

        var timeLimitLabel = new Label("Time limit");
        timeLimitSpinner = new Spinner<Integer>(0, 10000, 120);
        timeLimitSpinner.setEditable(true);

        constructScoring();

        getChildren().addAll(randomPlayersLabel, randomPlayersSpinner, smartPlayersLabel, smartPlayersSpinner, nLabel,
                nSpinner, tokensLabel, tokensSpinner, participateCheckBox, scoringComboBox, timeLimitLabel,
                timeLimitSpinner);
    }

    private void constructScoring() {
        scoringComboBox = new ComboBox<>();
        var sequenceValue = new SequenceValueScoring();
        var sequenceLength = new SequenceLengthScoring();
        var sequenceValueBonus = new HamiltonianBonusScoring();
        sequenceValueBonus.setBaseScoring(sequenceValue);
        var sequenceLengthBonus = new HamiltonianBonusScoring();
        sequenceLengthBonus.setBaseScoring(sequenceLength);
        scoringComboBox.getItems().addAll(sequenceValue, sequenceLength, sequenceValueBonus, sequenceLengthBonus);
        scoringComboBox.setValue(sequenceLength);
        for (var item : scoringComboBox.getItems()) {
            item.setGame(game);
        }
    }

    public int getRandomPlayers() {
        return randomPlayersSpinner.getValue();
    }

    public int getSmartPlayers() {
        return smartPlayersSpinner.getValue();
    }

    public int getTimeLimit() {
        return timeLimitSpinner.getValue();
    }

    public boolean getParticipate() {
        return participateCheckBox.selectedProperty().get();
    }

    public Scoring getScoring() {
        return scoringComboBox.getValue();
    }
    
    public int getN() {
        return nSpinner.getValue();
    }
    
    public int getTokens() {
        return tokensSpinner.getValue();
    }
}

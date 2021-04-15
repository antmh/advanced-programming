package lab7.gui;

import java.util.Set;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Pair;
import lab7.Game;
import lab7.Token;
import lab7.players.Player;

public class ResultsPanel extends VBox {
    private Label winnersLabel;
    private ListView<Pair<Player, Set<Token>>> takenTokens;
    private UpdateResultsService service;
    private Label secondsElapsedLabel;

    public ResultsPanel(Game game) {
        secondsElapsedLabel = new Label();

        winnersLabel = new Label();
        winnersLabel.setVisible(false);

        takenTokens = new ListView<Pair<Player, Set<Token>>>();
        service = new UpdateResultsService(game);
        service.setPeriod(Duration.millis(50));
        service.setOnSucceeded(this::onUpdate);
        service.start();

        getChildren().addAll(secondsElapsedLabel, winnersLabel, takenTokens);
    }

    public void showWinners(boolean show) {
        winnersLabel.setVisible(show);
    }

    public void setWinners(Set<Player> winners) {
        winnersLabel.setText("Winners: " + winners);
    }
    
    public void onUpdate(WorkerStateEvent e) {
        secondsElapsedLabel.setText("Seconds elapsed: " + service.getSecondsElapsed());
        takenTokens.getItems().setAll(service.getResults());
    }
    
    public void shutdown() {
        service.cancel();
    }
}

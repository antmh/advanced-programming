package lab7.gui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import lab7.Game;
import lab7.players.GUIPlayer;
import lab7.players.RandomPlayer;
import lab7.players.SmartPlayer;

public class Root extends VBox {
    private Game game;
    private Button playButton;
    private ControlPanel controlPanel;
    private GUIPlayer guiPlayer;
    private ResultsPanel resultsPanel;
    private ExecutorService playExecutor;

    public Root(Game game) {
        this.game = game;
        controlPanel = new ControlPanel(game);

        playButton = new Button("Play");
        playButton.setOnAction(this::onPlay);

        resultsPanel = new ResultsPanel(game);

        guiPlayer = new GUIPlayer();
        guiPlayer.getListView().setVisible(false);

        playExecutor = Executors.newFixedThreadPool(1);

        getChildren().addAll(controlPanel, playButton, resultsPanel, guiPlayer.getListView());
    }

    private void onPlay(ActionEvent e) {
        playButton.setDisable(true);
        game.getPlayers().clear();
        for (int i = 0; i < controlPanel.getRandomPlayers(); ++i) {
            game.getPlayers().add(new RandomPlayer());
        }
        for (int i = 0; i < controlPanel.getSmartPlayers(); ++i) {
            game.getPlayers().add(new SmartPlayer());
        }
        if (controlPanel.getParticipate()) {
            game.getPlayers().add(guiPlayer);
        }
        game.setTimeLimit(controlPanel.getTimeLimit() * 1000);
        game.setScoring(controlPanel.getScoring());
        game.setN(controlPanel.getN());
        game.setTokensNumber(controlPanel.getTokens());
        resultsPanel.showWinners(false);
        guiPlayer.getListView().setVisible(true);
        var playTask = new Task<>() {
            @Override
            protected Void call() {
                game.play();
                return null;
            }
        };
        playTask.setOnSucceeded(this::showResults);
        playExecutor.execute(playTask);
    }

    private void showResults(WorkerStateEvent e) {
        resultsPanel.setWinners(game.getWinners());
        resultsPanel.showWinners(true);
        guiPlayer.getListView().setVisible(false);
        playButton.setDisable(false);
    }

    public void shutdown() {
        resultsPanel.shutdown();
        playExecutor.shutdown();
    }
}

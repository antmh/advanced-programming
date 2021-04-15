package lab7.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Pair;
import lab7.Game;
import lab7.Token;
import lab7.players.Player;

public class UpdateResultsService extends ScheduledService<Void> {
    private Game game;
    private List<Pair<Player, Set<Token>>> results;
    private long secondsElapsed;

    public UpdateResultsService(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("game cannot be null");
        }
        this.game = game;
        results = new ArrayList<>();
        secondsElapsed = 0;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                results.clear();
                for (int i = 0; i < game.getPlayers().size(); ++i) {
                    results.add(new Pair<Player, Set<Token>>(game.getPlayers().get(i),
                            game.getBoard().getTakenTokens().get(i)));
                }
                secondsElapsed = game.getSecondsElapsed();
                return null;
            }
        };
    }

    public List<Pair<Player, Set<Token>>> getResults() {
        return results;
    }

    public long getSecondsElapsed() {
        return secondsElapsed;
    }
}

package lab7.players;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import lab7.Token;

public class GUIPlayer extends Player {
    private ListView<Token> listView;
    private int size;
    private String name;

    public GUIPlayer() {
        listView = new ListView<Token>();
    }

    @Override
    public void run() {
        name = Thread.currentThread().getName();
        listView.getSelectionModel().selectedIndexProperty().addListener(this::select);
        var service = createAvailableTokensService();
        service.setPeriod(Duration.millis(50));
        service.setOnSucceeded((t) -> listView.getItems().setAll(service.getValue()));
        service.start();
        size = board.getSize();
        while (!board.isOver()) {
            synchronized (board) {
                try {
                    board.wait();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }
    }

    private void select(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
        int index = newVal.intValue();
        if (index == -1) {
            return;
        }
        var thread = new Thread(() -> {
            try {
                board.takeToken(listView.getItems().get(newVal.intValue()));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        });
        thread.setName(name);
        thread.start();
    }

    private ScheduledService<List<Token>> createAvailableTokensService() {
        return new ScheduledService<List<Token>>() {
            @Override
            protected Task<List<Token>> createTask() {
                return new Task<List<Token>>() {
                    @Override
                    protected List<Token> call() throws Exception {
                        return getAvailableTokens();
                    }
                };
            }
        };
    }

    private List<Token> getAvailableTokens() {
        List<Token> tokensList = new ArrayList<>();
        for (int first = 1; first <= size; ++first) {
            for (int second = 1; second <= size; ++second) {
                var token = board.getTokenAt(first, second);
                if (token.isPresent()) {
                    tokensList.add(token.get());
                }
            }
        }
        return tokensList;
    }

    public ListView<Token> getListView() {
        return listView;
    }

    @Override
    public String toString() {
        return "GUI Player " + name;
    }
}

package lab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import lab7.gui.Root;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var game = new Game();
        var root = new Root(game);
        stage.setScene(new Scene(root, 500, 500));
        stage.setOnCloseRequest((e) -> root.shutdown());
        stage.show();
    }
}

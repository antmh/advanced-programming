package lab6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lab6.tabs.CommandsTab;
import lab6.tabs.FreeDrawTab;
import lab6.tabs.OvalTab;
import lab6.tabs.RectangleTab;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        var commandsTab = new CommandsTab();
        var configurationTabPane = new TabPane(new RectangleTab(), new OvalTab(), new FreeDrawTab(), commandsTab);
        configurationTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        var canvas = new Canvas(configurationTabPane);
        commandsTab.setCanvas(canvas);
        var scrollPane = new ScrollPane(canvas);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        var controlPanel = new ControlPanel();
        controlPanel.setImageSupplier(canvas::getImage);
        controlPanel.setImageConsumer(canvas::setImage);
        controlPanel.setOnReset((event) -> canvas.reset());
        controlPanel.setOnUndo((event) -> canvas.undo());
        controlPanel.setOnExit((event) -> Platform.exit());

        var vbox = new VBox(configurationTabPane, scrollPane, controlPanel);
        scrollPane.maxHeightProperty().bind(vbox.heightProperty().multiply(3).divide(5));
        vbox.setSpacing(10);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 500, 500));
        stage.show();
    }
}

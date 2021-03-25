package lab6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        var configurationPanel = new ConfigurationPanel();

        var canvas = new Canvas();
        canvas.setShapeWidth(configurationPanel.getWidthProperty());
        canvas.setShapeHeight(configurationPanel.getHeightProperty());
        canvas.setShapeColor(configurationPanel.getColorProperty());
        var scrollPane = new ScrollPane(canvas);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        var controlPanel = new ControlPanel();
        controlPanel.setImageSupplier(canvas::getImage);
        controlPanel.setImageConsumer(canvas::setImage);
        controlPanel.setOnReset((event) -> canvas.reset());
        controlPanel.setOnExit((event) -> Platform.exit());

        var vbox = new VBox(configurationPanel, scrollPane, controlPanel);
        vbox.setSpacing(10);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 500, 500));
        stage.show();
    }
}

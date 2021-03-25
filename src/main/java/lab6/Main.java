package lab6;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        var widthLabel = new Label("Width:");
        var widthSpinner = new Spinner<Integer>(0, 100, 20);
        widthSpinner.setEditable(true);

        var heightLabel = new Label("Height:");
        var heightSpinner = new Spinner<Integer>(0, 100, 20);
        heightSpinner.setEditable(true);

        var colorLabel = new Label("Color:");
        var colorPicker = new ColorPicker(Color.BLACK);

        var canvas = new Canvas();
        canvas.setShapeWidth(widthSpinner.valueProperty());
        canvas.setShapeHeight(heightSpinner.valueProperty());
        canvas.setShapeColor(colorPicker.valueProperty());

        var scrollPane = new ScrollPane(canvas);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        var vbox = new VBox(widthLabel, widthSpinner, heightLabel, heightSpinner, colorLabel, colorPicker, scrollPane);
        vbox.setSpacing(10);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 500, 500));
        stage.show();
    }

}

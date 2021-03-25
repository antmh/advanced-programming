package lab6;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ConfigurationPanel extends VBox {
    private Spinner<Integer> widthSpinner;
    private Spinner<Integer> heightSpinner;
    private ColorPicker colorPicker;

    public ConfigurationPanel() {
        var widthLabel = new Label("Width:");
        widthSpinner = new Spinner<>(0, 100, 20);
        widthSpinner.setEditable(true);

        var heightLabel = new Label("Height:");
        heightSpinner = new Spinner<>(0, 100, 20);
        heightSpinner.setEditable(true);

        var colorLabel = new Label("Color:");
        colorPicker = new ColorPicker(Color.BLACK);

        getChildren().addAll(widthLabel, widthSpinner, heightLabel, heightSpinner, colorLabel, colorPicker);
    }
    
    public ReadOnlyObjectProperty<Integer> getWidthProperty() {
        return widthSpinner.valueProperty();
    }
    
    public ReadOnlyObjectProperty<Integer> getHeightProperty() {
        return heightSpinner.valueProperty();
    }
    
    public ReadOnlyObjectProperty<Color> getColorProperty() {
        return colorPicker.valueProperty();
    }
}

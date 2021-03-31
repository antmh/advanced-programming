package lab6.tabs;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RectangleTab extends Tab {
    private Spinner<Integer> widthSpinner;
    private Spinner<Integer> heightSpinner;
    private ColorPicker colorPicker;

    public RectangleTab() {
        var widthLabel = new Label("Width:");
        widthSpinner = new Spinner<>(0, 100, 20);
        widthSpinner.setEditable(true);

        var heightLabel = new Label("Height:");
        heightSpinner = new Spinner<>(0, 100, 20);
        heightSpinner.setEditable(true);

        var colorLabel = new Label("Color:");
        colorPicker = new ColorPicker(Color.BLACK);

        setText("Rectangle");
        setContent(new VBox(widthLabel, widthSpinner, heightLabel, heightSpinner, colorLabel, colorPicker));
    }

    public final Spinner<Integer> getWidthSpinner() {
        return widthSpinner;
    }

    public final Spinner<Integer> getHeightSpinner() {
        return heightSpinner;
    }

    public final ColorPicker getColorPicker() {
        return colorPicker;
    }
}

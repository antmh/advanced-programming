package lab6.tabs;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FreeDrawTab extends Tab {
    private Spinner<Integer> lineWidthSpinner;
    private ColorPicker colorPicker;

    public FreeDrawTab() {
        var sizeLabel = new Label("Line width:");
        lineWidthSpinner = new Spinner<>(0, 100, 5);
        lineWidthSpinner.setEditable(true);

        var colorLabel = new Label("Color:");
        colorPicker = new ColorPicker(Color.BLACK);

        setText("Free Draw");
        setContent(new VBox(sizeLabel, lineWidthSpinner, colorLabel, colorPicker));
    }

    public final Spinner<Integer> getLineWidthSpinner() {
        return lineWidthSpinner;
    }

    public final ColorPicker getColorPicker() {
        return colorPicker;
    }
}

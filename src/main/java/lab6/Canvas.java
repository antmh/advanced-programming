package lab6;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Canvas extends javafx.scene.canvas.Canvas {
    private ReadOnlyObjectProperty<Integer> shapeWidth;
    private ReadOnlyObjectProperty<Integer> shapeHeight;
    private ObjectProperty<Color> shapeColor;

    public Canvas() {
        super(1000, 1000);
        setOnMouseClicked(this::mouseClicked);
    }

    private void mouseClicked(MouseEvent event) {
        var context = getGraphicsContext2D();
        context.setFill(shapeColor.get());
        var x = event.getX();
        var y = event.getY();
        var width = shapeWidth.get();
        var height = shapeHeight.get();
        context.fillRect(x - width / 2, y - height / 2, width, height);
    }

    public ReadOnlyObjectProperty<Integer> getShapeWidth() {
        return shapeWidth;
    }

    public ReadOnlyObjectProperty<Integer> getShapeHeight() {
        return shapeHeight;
    }

    public final ObjectProperty<Color> getShapeColor() {
        return shapeColor;
    }

    public void setShapeWidth(ReadOnlyObjectProperty<Integer> shapeWidth) {
        if (shapeWidth == null) {
            throw new IllegalArgumentException("Shape widthcannot be null");
        }
        this.shapeWidth = shapeWidth;
    }

    public void setShapeHeight(ReadOnlyObjectProperty<Integer> shapeHeight) {
        if (shapeHeight == null) {
            throw new IllegalArgumentException("Shape height cannot be null");
        }
        this.shapeHeight = shapeHeight;
    }

    public final void setShapeColor(ObjectProperty<Color> shapeColor) {
        if (shapeColor == null) {
            throw new IllegalArgumentException("Shape color cannot be null");
        }
        this.shapeColor = shapeColor;
    }

}

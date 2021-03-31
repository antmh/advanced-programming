package lab6.shapes;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Cloneable {
    protected Color color;

    public abstract void draw(GraphicsContext context);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("color cannot be null");
        }
        this.color = color;
    }

    public abstract Bounds getBounds();
}

package lab6.shapes;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape {
    private double x, y;
    private double size;
    private double width;

    @Override
    public void draw(GraphicsContext context) {
        context.setStroke(color);
        context.setLineWidth(width);
        context.strokeOval(x - size / 2, y - size / 2, size, size);
    }

    @Override
    public Bounds getBounds() {
        double boxSize = size + width;
        double distFromCenter = size / 2 + width / 2;
        return new BoundingBox(x - distFromCenter, y - distFromCenter, boxSize, boxSize);
    }

    public final double getX() {
        return x;
    }

    public final void setX(double x) {
        this.x = x;
    }

    public final double getY() {
        return y;
    }

    public final void setY(double y) {
        this.y = y;
    }

    public final double getSize() {
        return size;
    }

    public final void setSize(double size) {
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative");
        }
        this.size = size;
    }

    public final double getWidth() {
        return width;
    }

    public final void setWidth(double width) {
        this.width = width;
    }
}

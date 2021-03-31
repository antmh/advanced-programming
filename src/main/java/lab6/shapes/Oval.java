package lab6.shapes;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Oval extends Shape {
    protected double x, y;
    protected int width, height;

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillOval(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public Bounds getBounds() {
        return new BoundingBox(x - width / 2, y - height / 2, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("width cannot be negative");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("height cannot be negative");
        }
        this.height = height;
    }
}

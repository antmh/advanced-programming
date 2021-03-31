package lab6.shapes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Line extends Shape {
    private List<Double> xPoints, yPoints;
    private double width;

    public Line() {
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setStroke(color);
        context.setLineWidth(width);
        context.strokePolyline(xPoints.stream().mapToDouble((i) -> i).toArray(),
                yPoints.stream().mapToDouble((i) -> i).toArray(), xPoints.size());
    }

    @Override
    public Bounds getBounds() {
        var halfWidth = this.width / 2;
        var minX = xPoints.get(0) - halfWidth;
        var minY = yPoints.get(0) - halfWidth;
        var width = this.width;
        var height = this.width;
        for (int i = 1; i < xPoints.size(); ++i) {
            minX = Math.min(minX, xPoints.get(i) - halfWidth);
            minY = Math.min(minY, yPoints.get(i) - halfWidth);
            width = Math.max(width, xPoints.get(i) - halfWidth);
            height = Math.max(height, yPoints.get(i) - halfWidth);
        }
        return new BoundingBox(minX, minY, width, height);
    }

    public void addPoint(double x, double y) {
        this.xPoints.add(x);
        this.yPoints.add(y);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("width cannot be negative");
        }
        this.width = width;
    }
}

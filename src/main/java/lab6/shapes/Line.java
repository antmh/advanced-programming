package lab6.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Line extends Shape {
    private List<Double> xPoints, yPoints;
    private double width;
    private final double MAX_STRAIGHT_LINE_POINT_DEVIATION = 10.0;
    private final double MAX_CIRCLE_START_END_DEVIATION_DIAMETER_RATIO = 1.0 / 4.0;
    private final double MAX_CIRCLE_POINT_DEVIATION_DIAMETER_RATIO = 3.0 / 4.0;

    public Line() {
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
    }

    public Optional<Shape> detectShape() {
        var straightLine = detectStraightLine();
        if (straightLine.isPresent()) {
            return Optional.of(straightLine.get());
        }
        var circle = detectCircle();
        if (circle.isPresent()) {
            return Optional.of(circle.get());
        }
        return Optional.empty();
    }

    private Optional<Line> detectStraightLine() {
        double firstX = xPoints.get(0);
        double firstY = yPoints.get(0);
        double lastX = xPoints.get(xPoints.size() - 1);
        double lastY = yPoints.get(yPoints.size() - 1);
        // The first and last point form a line with the equation ax + by + c = 0
        double a = lastY - firstY;
        double b = firstX - lastX;
        double c = firstY * lastX - firstX * lastY;
        boolean isStraight = true;
        for (int i = 1; i < xPoints.size() - 1; ++i) {
            double dist = Math.abs(a * xPoints.get(i) + b * yPoints.get(i) + c)
                    / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
            if (dist > MAX_STRAIGHT_LINE_POINT_DEVIATION) {
                isStraight = false;
                break;
            }
        }
        if (isStraight) {
            var line = new Line();
            line.setColor(color);
            line.setWidth(width);
            line.addPoint(firstX, firstY);
            line.addPoint(lastX, lastY);
            return Optional.of(line);
        }
        return Optional.empty();
    }

    private Optional<Circle> detectCircle() {
        double firstX = xPoints.get(0);
        double firstY = yPoints.get(0);
        double lastX = xPoints.get(xPoints.size() - 1);
        double lastY = yPoints.get(yPoints.size() - 1);
        double farthestX = firstX;
        double farthestY = firstY;
        for (int i = 1; i < xPoints.size(); ++i) {
            if (distance(firstX, firstY, farthestX, farthestY) < distance(firstX, firstY, xPoints.get(i),
                    yPoints.get(i))) {
                farthestX = xPoints.get(i);
                farthestY = yPoints.get(i);
            }
        }
        double diameter = distance(firstX, firstY, farthestX, farthestY);
        if (distance(firstX, firstY, lastX, lastY) > diameter * MAX_CIRCLE_START_END_DEVIATION_DIAMETER_RATIO) {
            return Optional.empty();
        }
        double centerX = (firstX + farthestX) / 2;
        double centerY = (firstY + farthestY) / 2;
        boolean isCircle = true;
        for (int i = 1; i < xPoints.size() - 1; ++i) {
            if (distance(centerX, centerY, xPoints.get(i), yPoints.get(i)) > diameter
                    * MAX_CIRCLE_POINT_DEVIATION_DIAMETER_RATIO) {
                isCircle = false;
                break;
            }
        }
        if (isCircle) {
            var circle = new Circle();
            circle.setColor(color);
            circle.setSize(diameter);
            circle.setWidth(width);
            circle.setX(centerX);
            circle.setY(centerY);
            return Optional.of(circle);
        }
        return Optional.empty();
    }

    private double distance(double xa, double ya, double xb, double yb) {
        return Math.sqrt(Math.pow(xa - xb, 2) + Math.pow(ya - yb, 2));
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

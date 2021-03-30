package lab6;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Canvas extends javafx.scene.canvas.Canvas {
    private ReadOnlyObjectProperty<Integer> shapeWidth;
    private ReadOnlyObjectProperty<Integer> shapeHeight;
    private ReadOnlyObjectProperty<Color> shapeColor;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private List<Shape> history;
    private int historyPosition;

    public Canvas() {
        super(WIDTH, HEIGHT);
        history = new ArrayList<>();
        historyPosition = -1;
        setOnMouseClicked(this::mouseClicked);
    }

    private void mouseClicked(MouseEvent event) {
        Shape rectangle = new Rectangle();
        rectangle.setColor(shapeColor.get());
        rectangle.setX(event.getX());
        rectangle.setY(event.getY());
        rectangle.setWidth(shapeWidth.get());
        rectangle.setHeight(shapeHeight.get());
        rectangle.draw(getGraphicsContext2D());
        ++historyPosition;
        while (historyPosition != history.size()) {
            history.remove(historyPosition);
        }
        history.add(rectangle);
    }

    public BufferedImage getImage() {
        var writableImage = new WritableImage(WIDTH, HEIGHT);
        snapshot(null, writableImage);
        return SwingFXUtils.fromFXImage(writableImage, null);
    }

    public void setImage(BufferedImage image) {
        var context = getGraphicsContext2D();
        var fxImage = SwingFXUtils.toFXImage(image, null);
        context.drawImage(fxImage, 0, 0);
    }

    public void reset() {
        getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
        history.clear();
    }
    
    public void undo() {
        if (historyPosition < 0) {
            return;
        }       
        var bounds = history.get(historyPosition).getBounds();
        var context = getGraphicsContext2D();
        context.clearRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
        for (int i = 0; i < historyPosition; ++i) {
            var shape = history.get(i);
            if (shape.getBounds().intersects(bounds)) {
                shape.draw(context);
            }
        }
        --historyPosition;
    }

    public ReadOnlyObjectProperty<Integer> getShapeWidth() {
        return shapeWidth;
    }

    public ReadOnlyObjectProperty<Integer> getShapeHeight() {
        return shapeHeight;
    }

    public final ReadOnlyObjectProperty<Color> getShapeColor() {
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

    public final void setShapeColor(ReadOnlyObjectProperty<Color> shapeColor) {
        if (shapeColor == null) {
            throw new IllegalArgumentException("Shape color cannot be null");
        }
        this.shapeColor = shapeColor;
    }
}

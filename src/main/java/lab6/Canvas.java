package lab6;

import java.awt.image.BufferedImage;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Canvas extends javafx.scene.canvas.Canvas {
    private ReadOnlyObjectProperty<Integer> shapeWidth;
    private ReadOnlyObjectProperty<Integer> shapeHeight;
    private ReadOnlyObjectProperty<Color> shapeColor;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private History history;

    public Canvas() {
        super(WIDTH, HEIGHT);
        history = new History();
        setOnMouseClicked(this::mouseClicked);
    }

    private void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            addShape(event.getX(), event.getY());
        } else if (event.getButton() == MouseButton.SECONDARY) {
            removeShape(event.getX(), event.getY());
        }
    }
        
    private void addShape(double x, double y) {
        Shape rectangle = new Rectangle();
        rectangle.setColor(shapeColor.get());
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(shapeWidth.get());
        rectangle.setHeight(shapeHeight.get());
        rectangle.draw(getGraphicsContext2D());
        history.addShape(rectangle);
    }
    
    private void removeShape(double x, double y) {
        var actions = history.removeShape(x, y);
        for (var action : actions) {
            action.execute(getGraphicsContext2D());
        }
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
        var actions = history.undo();
        for (var action : actions) {
            action.execute(getGraphicsContext2D());
        }
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

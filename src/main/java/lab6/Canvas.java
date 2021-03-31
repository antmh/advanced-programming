package lab6;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lab6.shapes.Oval;
import lab6.shapes.Rectangle;
import lab6.shapes.Shape;
import lab6.tabs.OvalTab;
import lab6.tabs.RectangleTab;

public class Canvas extends javafx.scene.canvas.Canvas {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private History history;
    private TabPane configurationTabPane;

    public Canvas(TabPane configurationTabPane) {
        super(WIDTH, HEIGHT);
        if (configurationTabPane == null) {
            throw new IllegalArgumentException("configurationTabPane cannot be null");
        }
        this.configurationTabPane = configurationTabPane;
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
        Shape shape = null;
        Tab selectedTab = null;
        for (var tab : configurationTabPane.getTabs()) {
            if (tab.isSelected()) {
                selectedTab = tab;
                break;
            }
        }
        if (selectedTab instanceof RectangleTab) {
            var rectangleTab = (RectangleTab) selectedTab;
            shape = new Rectangle();
            shape.setColor(rectangleTab.getColorPicker().getValue());
            shape.setWidth(rectangleTab.getWidthSpinner().getValue());
            shape.setHeight(rectangleTab.getHeightSpinner().getValue());
        } else if (selectedTab instanceof OvalTab) {
            var ovalTab = (OvalTab) selectedTab;
            shape = new Oval();
            shape.setColor(ovalTab.getColorPicker().getValue());
            shape.setWidth(ovalTab.getWidthSpinner().getValue());
            shape.setHeight(ovalTab.getHeightSpinner().getValue());
        }
        shape.setX(x);
        shape.setY(y);
        shape.draw(getGraphicsContext2D());
        history.addShape(shape);
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
}

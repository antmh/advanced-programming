package lab6;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lab6.shapes.Oval;
import lab6.actions.Action;
import lab6.shapes.Line;
import lab6.shapes.Rectangle;
import lab6.shapes.Shape;
import lab6.tabs.FreeDrawTab;
import lab6.tabs.OvalTab;
import lab6.tabs.RectangleTab;

public class Canvas extends javafx.scene.canvas.Canvas {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private History history;
    private TabPane configurationTabPane;
    private boolean dragging;
    private Line line;

    public Canvas(TabPane configurationTabPane) {
        super(WIDTH, HEIGHT);
        if (configurationTabPane == null) {
            throw new IllegalArgumentException("configurationTabPane cannot be null");
        }
        this.configurationTabPane = configurationTabPane;
        history = new History();
        setOnMouseClicked(this::onMouseClicked);
        setOnMouseDragged(this::onMouseDragged);
        dragging = false;
        line = null;
    }

    private void onMouseClicked(MouseEvent event) {
        dragging = false;
        if (event.getButton() == MouseButton.PRIMARY) {
            addShape(event.getX(), event.getY());
        } else if (event.getButton() == MouseButton.SECONDARY) {
            removeShape(event.getX(), event.getY());
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && getSelectedTab() instanceof FreeDrawTab) {
            dragging = true;
            addShape(event.getX(), event.getY());
        }
    }

    private Tab getSelectedTab() {
        for (var tab : configurationTabPane.getTabs()) {
            if (tab.isSelected()) {
                return tab;
            }
        }
        return null;
    }

    private void addShape(double x, double y) {
        Shape shape = null;
        Tab selectedTab = getSelectedTab();
        if (selectedTab instanceof RectangleTab) {
            var rectangleTab = (RectangleTab) selectedTab;
            var rectangle = new Rectangle();
            rectangle.setColor(rectangleTab.getColorPicker().getValue());
            rectangle.setWidth(rectangleTab.getWidthSpinner().getValue());
            rectangle.setHeight(rectangleTab.getHeightSpinner().getValue());
            rectangle.setX(x);
            rectangle.setY(y);
            shape = rectangle;
        } else if (selectedTab instanceof OvalTab) {
            var ovalTab = (OvalTab) selectedTab;
            var oval = new Oval();
            oval.setColor(ovalTab.getColorPicker().getValue());
            oval.setWidth(ovalTab.getWidthSpinner().getValue());
            oval.setHeight(ovalTab.getHeightSpinner().getValue());
            oval.setX(x);
            oval.setY(y);
            shape = oval;
        } else if (selectedTab instanceof FreeDrawTab) {
            var freeDrawTab = (FreeDrawTab) selectedTab;
            if (line == null) {
                line = new Line();
                line.setWidth(freeDrawTab.getLineWidthSpinner().getValue());
                line.setColor(freeDrawTab.getColorPicker().getValue());
            }
            line.addPoint(x, y);
            line.draw(getGraphicsContext2D());
            if (!dragging) {
                history.addShape(line);
                var detectedShape = line.detectShape();
                if (detectedShape.isPresent()) {
                    undo();
                    detectedShape.get().draw(getGraphicsContext2D());
                    history.addShape(detectedShape.get());
                }
                line = null;
            }
            return;
        }
        shape.draw(getGraphicsContext2D());
        history.addShape(shape);
    }

    private void removeShape(double x, double y) {
        var actions = history.removeShape(x, y);
        doActions(actions);
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
        doActions(actions);
    }
    
    private void doActions(List<Action> actions) {
        for (var action : actions) {
            action.execute(getGraphicsContext2D());
        }
    }
    
    public void drawShapes(List<Shape> shapes) {
        for (var shape : shapes) {
            history.addShape(shape);
            shape.draw(getGraphicsContext2D());
        }
    }
}

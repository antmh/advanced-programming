package lab6.actions;

import javafx.scene.canvas.GraphicsContext;
import lab6.shapes.Shape;

public class ClearAction extends Action {
    public ClearAction(Shape shape) {
        super(shape);
    }

    @Override
    public void execute(GraphicsContext context) {
        var bounds = shape.getBounds();
        context.clearRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
    }
}

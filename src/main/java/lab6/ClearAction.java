package lab6;

import javafx.scene.canvas.GraphicsContext;

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

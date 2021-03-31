package lab6.actions;

import javafx.scene.canvas.GraphicsContext;
import lab6.shapes.Shape;

public class DrawAction extends Action {
    public DrawAction(Shape shape) {
        super(shape);
    }

    @Override
    public void execute(GraphicsContext context) {
        shape.draw(context);
    }
}

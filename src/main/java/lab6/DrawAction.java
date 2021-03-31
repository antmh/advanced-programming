package lab6;

import javafx.scene.canvas.GraphicsContext;

public class DrawAction extends Action {
    public DrawAction(Shape shape) {
        super(shape);
    }

    @Override
    public void execute(GraphicsContext context) {
        shape.draw(context);
    }
}

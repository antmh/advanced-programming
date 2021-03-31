package lab6.actions;

import javafx.scene.canvas.GraphicsContext;
import lab6.shapes.Shape;

public abstract class Action {
    protected Shape shape;

    public Action() {
    }

    public Action(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("shape cannot be null");
        }
        this.shape = shape;
    }
    
    public abstract void execute(GraphicsContext context);

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("shape cannot be null");
        }
        this.shape = shape;
    }
}

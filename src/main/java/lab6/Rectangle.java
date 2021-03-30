package lab6;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape {
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x - width / 2, y - height / 2, width, height);
    }
}

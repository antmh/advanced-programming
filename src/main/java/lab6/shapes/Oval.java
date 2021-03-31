package lab6.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Oval extends Rectangle {
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillOval(x - width / 2, y - height / 2, width, height);
    }
}

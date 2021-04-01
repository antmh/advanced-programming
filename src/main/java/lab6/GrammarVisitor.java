package lab6;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import lab6.DrawerParser.OvalContext;
import lab6.DrawerParser.RectangleContext;
import lab6.actions.Action;
import lab6.actions.DrawAction;
import lab6.shapes.Oval;
import lab6.shapes.Rectangle;

public class GrammarVisitor extends DrawerBaseVisitor<List<Action>> {
    private List<Action> result;

    public final List<Action> getResult() {
        return result;
    }

    public final void setResult(List<Action> result) {
        this.result = result;
    }

    public GrammarVisitor() {
        result = new ArrayList<>();
    }

    @Override
    public List<Action> visitRectangle(RectangleContext ctx) {
        int width = Integer.parseInt(ctx.width().getText());
        int height = Integer.parseInt(ctx.height().getText());
        int x = Integer.parseInt(ctx.x().getText());
        int y = Integer.parseInt(ctx.y().getText());
        int red = Integer.parseInt(ctx.red().getText());
        int green = Integer.parseInt(ctx.green().getText());
        int blue = Integer.parseInt(ctx.blue().getText());
        var rectangle = new Rectangle();
        rectangle.setColor(new Color(red / 255.0, green / 255.0, blue / 255.0, 1.0));
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setX(x);
        rectangle.setY(y);
        result.add(new DrawAction(rectangle));
        return result;
    }

    @Override
    public List<Action> visitOval(OvalContext ctx) {
        int width = Integer.parseInt(ctx.width().getText());
        int height = Integer.parseInt(ctx.height().getText());
        int x = Integer.parseInt(ctx.x().getText());
        int y = Integer.parseInt(ctx.y().getText());
        int red = Integer.parseInt(ctx.red().getText());
        int green = Integer.parseInt(ctx.green().getText());
        int blue = Integer.parseInt(ctx.blue().getText());
        var oval = new Oval();
        oval.setColor(new Color(red / 255.0, green / 255.0, blue / 255.0, 1.0));
        oval.setHeight(height);
        oval.setWidth(width);
        oval.setX(x);
        oval.setY(y);
        result.add(new DrawAction(oval));
        return result;
    }
}

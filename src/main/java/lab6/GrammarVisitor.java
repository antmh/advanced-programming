package lab6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import lab6.DrawerParser.AddExprContext;
import lab6.DrawerParser.AssignContext;
import lab6.DrawerParser.BlockContext;
import lab6.DrawerParser.DivExprContext;
import lab6.DrawerParser.ForContext;
import lab6.DrawerParser.IdExprContext;
import lab6.DrawerParser.LitExprContext;
import lab6.DrawerParser.MulExprContext;
import lab6.DrawerParser.OvalContext;
import lab6.DrawerParser.ParenExprContext;
import lab6.DrawerParser.RectangleContext;
import lab6.DrawerParser.SubExprContext;
import lab6.shapes.Oval;
import lab6.shapes.Rectangle;
import lab6.shapes.Shape;

public class GrammarVisitor extends DrawerBaseVisitor<Integer> {
    private List<Shape> result;
    private Map<String, Integer> memory;

    public GrammarVisitor() {
        result = new ArrayList<>();
        memory = new HashMap<>();
    }

    @Override
    public Integer visitRectangle(RectangleContext ctx) {
        var rectangle = new Rectangle();
        rectangle.setColor(new Color(visit(ctx.red) / 255.0, visit(ctx.green) / 255.0, visit(ctx.blue) / 255.0, 1.0));
        rectangle.setHeight(visit(ctx.height));
        rectangle.setWidth(visit(ctx.width));
        rectangle.setX(visit(ctx.x));
        rectangle.setY(visit(ctx.y));
        result.add(rectangle);
        return 0;
    }

    @Override
    public Integer visitOval(OvalContext ctx) {
        var oval = new Oval();
        oval.setColor(new Color(visit(ctx.red) / 255.0, visit(ctx.green) / 255.0, visit(ctx.blue) / 255.0, 1.0));
        oval.setHeight(visit(ctx.height));
        oval.setWidth(visit(ctx.width));
        oval.setX(visit(ctx.x));
        oval.setY(visit(ctx.y));
        result.add(oval);
        return 0;
    }

    @Override
    public Integer visitAssign(AssignContext ctx) {
        memory.put(ctx.ID().getText(), visit(ctx.expr()));
        return 0;
    }
    
    @Override
    public Integer visitBlock(BlockContext ctx) {
        for (var statement : ctx.statement()) {
            visit(statement);
        }
        return 0;
    }
    
    @Override
    public Integer visitFor(ForContext ctx) {
        int start = visit(ctx.start);
        int end = visit(ctx.end);
        String ident = ctx.ID().getText();
        for (int i = start; i <= end; ++i) {
            memory.put(ident, i);
            visit(ctx.block());
        }
        return 0;
    }

    @Override
    public Integer visitIdExpr(IdExprContext ctx) {
        return memory.getOrDefault(ctx.getText(), 0);
    }

    @Override
    public Integer visitLitExpr(LitExprContext ctx) {
        return Integer.parseInt(ctx.getText());
    }
    
    @Override
    public Integer visitParenExpr(ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitMulExpr(MulExprContext ctx) {
        return visit(ctx.left) * visit(ctx.right);
    }

    @Override
    public Integer visitDivExpr(DivExprContext ctx) {
        return visit(ctx.left) / visit(ctx.right);
    }

    @Override
    public Integer visitAddExpr(AddExprContext ctx) {
        return visit(ctx.left) + visit(ctx.right);
    }

    @Override
    public Integer visitSubExpr(SubExprContext ctx) {
        return visit(ctx.left) - visit(ctx.right);
    }


    public final List<Shape> getResult() {
        return result;
    }

    public final void setResult(List<Shape> result) {
        this.result = result;
    }
}

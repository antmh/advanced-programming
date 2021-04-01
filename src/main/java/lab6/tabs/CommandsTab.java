package lab6.tabs;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import lab6.Canvas;
import lab6.DrawerLexer;
import lab6.DrawerParser;
import lab6.GrammarVisitor;
import lab6.DrawerParser.ParseContext;

public class CommandsTab extends Tab {
    private TextArea textArea;
    private Canvas canvas;

    public CommandsTab() {
        textArea = new TextArea();
        var runButton = new Button("Run");
        runButton.setOnAction(this::run);
        setText("Commands");
        setContent(new VBox(textArea, runButton));
    }

    private void run(ActionEvent event) {
        var text = textArea.getText();
        var stream = CharStreams.fromString(text);
        var lexer = new DrawerLexer(stream);
        var parser = new DrawerParser(new CommonTokenStream(lexer));
        ParseContext context = parser.parse();
        var visitor = new GrammarVisitor();
        visitor.visit(context);
        canvas.drawShapes(visitor.getResult());
    }

    public final Canvas getCanvas() {
        return canvas;
    }

    public final void setCanvas(Canvas canvas) {
        if (canvas == null) {
            throw new IllegalArgumentException("canvas cannot be null");
        }
        this.canvas = canvas;
    }
}

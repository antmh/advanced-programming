package lab6;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ControlPanel extends HBox {
    private Supplier<BufferedImage> imageSupplier;
    private Consumer<BufferedImage> imageConsumer;
    private Button undoButton;
    private Button resetButton;
    private Button exitButton;

    public ControlPanel() {
        var loadButton = new Button("Load");
        loadButton.setOnAction(this::load);
        var saveButton = new Button("Save");
        saveButton.setOnAction(this::save);
        undoButton = new Button("Undo");
        resetButton = new Button("Reset");
        exitButton = new Button("Exit");
        setSpacing(10);
        getChildren().addAll(loadButton, saveButton, undoButton, resetButton, exitButton);
    }

    private void save(ActionEvent event) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        var window = getScene().getWindow();
        var file = fileChooser.showSaveDialog(window);
        try {
            ImageIO.write(imageSupplier.get(), "png", file);
        } catch (IOException e) {
            var alert = new Alert(AlertType.ERROR, "Could not save image");
            alert.show();
        }
    }

    private void load(ActionEvent event) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Load");
        var window = getScene().getWindow();
        var file = fileChooser.showOpenDialog(window);
        try {
            var image = ImageIO.read(file);
            imageConsumer.accept(image);
        } catch (IOException e) {
            var alert = new Alert(AlertType.ERROR, "Could not load image");
            alert.show();
        }
    }

    public void setImageSupplier(Supplier<BufferedImage> imageSupplier) {
        if (imageSupplier == null) {
            throw new IllegalArgumentException("imageSupplier cannot be null");
        }
        this.imageSupplier = imageSupplier;
    }

    public void setImageConsumer(Consumer<BufferedImage> imageConsumer) {
        if (imageConsumer == null) {
            throw new IllegalArgumentException("imageConsumer cannot be null");
        }
        this.imageConsumer = imageConsumer;
    }

    public void setOnReset(EventHandler<ActionEvent> onReset) {
        if (onReset == null) {
            throw new IllegalArgumentException("onReset cannot be null");
        }
        resetButton.setOnAction(onReset);
    }
    
    public void setOnUndo(EventHandler<ActionEvent> onUndo) {
        if (onUndo == null) {
            throw new IllegalArgumentException("onUndo cannot be null");
        }
        undoButton.setOnAction(onUndo);
    }

    public void setOnExit(EventHandler<ActionEvent> onExit) {
        if (onExit == null) {
            throw new IllegalArgumentException("onExit cannot be null");
        }
        exitButton.setOnAction(onExit);
    }
}

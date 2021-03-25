package lab6;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ControlPanel extends HBox {
    private Supplier<BufferedImage> imageSupplier;

    public ControlPanel() {
        var loadButton = new Button("Load");
        var saveButton = new Button("Save");
        saveButton.setOnAction(this::save);
        var resetButton = new Button("Reset");
        var exitButton = new Button("Exit");
        setSpacing(10);
        getChildren().addAll(loadButton, saveButton, resetButton, exitButton);
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

    public void setImageSupplier(Supplier<BufferedImage> imageSupplier) {
        if (imageSupplier == null) {
            throw new IllegalArgumentException("imageSupplier cannot be null");
        }
        this.imageSupplier = imageSupplier;
    }
}

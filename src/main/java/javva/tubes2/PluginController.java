package javva.tubes2;

import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;

public class PluginController {
    @FXML
    private Button back_button;

    @FXML
    private Label file_label;

    @FXML
    private Label information;

    @FXML
    private Button load_button;

    @FXML
    void chooseFile(ActionEvent event) {
        // Create a new FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Jar files (*.jar)", "*.jar");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog and get the current stage from any control, here load_button is used
        File file = fileChooser.showOpenDialog(load_button.getScene().getWindow());

        if (file != null) {
            System.out.println(file.getAbsolutePath());
            file_label.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }
}

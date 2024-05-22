package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SaveController {
    @FXML
    private Button back_button;

    @FXML
    private TextField folder_box;

    @FXML
    private ComboBox<?> format_choice;

    @FXML
    private Label information;

    @FXML
    private Button save_button;

    @FXML
    private void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

}

package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javva.tubes2.GameMaster.*;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javva.tubes2.GameMaster.GameMaster;
import javva.tubes2.dataLoader.SaveManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SaveController implements Initializable {
    @FXML
    private Button back_button;

    @FXML
    private TextField folder_box;

    @FXML
    private ComboBox<String> format_choice;

    @FXML
    private Label information;

    @FXML
    private Button save_button;

    private SaveManager save_manager = SaveManager.getInstance(); ;

    private GameMaster game_master ;


    @FXML
    private void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

    public void setGameMaster(GameMaster game_master){
        System.out.println("Game master setted");
        this.game_master = game_master;
    }

    public void save(){
        // pastiin setGameMaster dulu
        String path = folder_box.getText();
        if(path == null || path.isEmpty()){
            information.setText("Please enter a folder name");
            information.setStyle("-fx-text-fill: red");
            return;
        }

        String format = format_choice.getValue();
        if(format == null || format.isEmpty()){
            information.setText("Please select a format");
            information.setStyle("-fx-text-fill: red");
        }

        try {
            save_manager.saveGame(game_master.player1, game_master.player2, game_master.main_shop, game_master.turn, path ,format );
        } catch (Exception e) {
            information.setText("Failed to save game " + path + "." + format);
            information.setStyle("-fx-text-fill: red");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        format_choice.getItems().addAll(save_manager.getSaveFormats());
    }
}

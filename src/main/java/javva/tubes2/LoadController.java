package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javva.tubes2.GameMaster.GameMaster;
import javva.tubes2.dataLoader.SaveManager;
import javva.tubes2.Player.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    private Button back_button;

    @FXML
    private TextField folder_box;

    @FXML
    private ComboBox<String> format_choice;

    @FXML
    private Label information;

    @FXML
    private Button load_button;

    private SaveManager load_manager = SaveManager.getInstance();

    public static MainController main;


    @FXML
    private void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        format_choice.getItems().addAll(load_manager.getSaveFormats());
        information.setText("");
    }


    @FXML
    public void load(){
        // Check text fields
        String path = folder_box.getText();
        if (path == null || path.isEmpty()) {
            information.setText("Please enter a valid folder path");
            information.setStyle("-fx-text-fill: red");
            return;
        }
        // Check choice box choice
        String format = format_choice.getValue();
        if (format == null || format.isEmpty()) {
            information.setText("Please pick a format");
            information.setStyle("-fx-text-fill: red");
            return;
        }

        // load player 1
        try {
            main.game.player1 = load_manager.loadPlayer(path, "player1",format );
        } catch (Exception e) {
            information.setText("Failed to load Player 1");
            information.setStyle("-fx-text-fill: red");
            return ;
        }
        // load player 2
        try{
            main.game.player2 = load_manager.loadPlayer(path,"player2", format);
            System.out.println("GULDEN 2 " +main.game.player2.getGulden());
        }catch(Exception e){
            information.setText("Failed to load Player 2");
            information.setStyle("-fx-text-fill: red");
        }
        // load gamestate (load turn)
        try{
            main.game.turn = (int) load_manager.loadGameState(path, format);
        }catch (Exception e){
            information.setText("Failed to load Game State");
            information.setStyle("-fx-text-fill: red");
            return;
        }
        information.setText("File " + path + "." + format + " loaded successfully");
        information.setStyle("-fx-text-fill: #0eab79");

        main.renderField(main.game.current_player);
        main.renderActiveDeck(main.game.current_player);



    }


}

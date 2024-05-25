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

    private GameMaster game;

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


    public void setGameMaster(GameMaster game) {
        this.game = game;
    }

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
            game.player1 = load_manager.loadPlayer(path, "player1",format );
        } catch (Exception e) {
            information.setText("Failed to load Player 1");
            information.setStyle("-fx-text-fill: red");
//            e.printStackTrace();

//            return ;
        }
        // load player 2
        try{
            game.player2 = load_manager.loadPlayer(path,"player2", format);
        }catch(Exception e){
            information.setText("Failed to load Player 2");
            information.setStyle("-fx-text-fill: red");
        }
        // load gamestate (load turn)
        try{
            game.turn = (int) load_manager.loadGameState(path, format);
        }catch (Exception e){
            information.setText("Failed to load Game State");
            information.setStyle("-fx-text-fill: red");
//            e.printStackTrace();

//            return;
        }
        System.out.println("Uang player 1 " + game.player1.getGulden());
        System.out.println("Uang player 2 " + game.player2.getGulden());




    }


}

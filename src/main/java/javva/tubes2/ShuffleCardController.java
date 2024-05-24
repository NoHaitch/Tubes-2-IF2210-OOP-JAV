package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class ShuffleCardController  {
    private List<TempCard> cards;

    @FXML
    private GridPane card_grid;

    @FXML
    private Button done_button;

    @FXML
    void reShuffle(ActionEvent event) {

    }

    @FXML
    void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) done_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

    public List<TempCard> getCards(){
        return cards;
    }


}

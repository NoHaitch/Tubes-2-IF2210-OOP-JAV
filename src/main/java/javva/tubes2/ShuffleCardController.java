package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javva.tubes2.Card.*;

public class ShuffleCardController implements Initializable {
    private List<Card> cards = new ArrayList<>();
    private List<CardController> controllers = new ArrayList<>();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize empty
        renderShuffled();
    }

    public void setData(List<Card> cards) {
        emptyField();
        for (int i = 0; i < cards.size(); i++) {
            controllers.get(i).setData(cards.get(i));
        }
    }

    private void emptyField(){
        for (CardController controller: controllers) {
            Card new_card = new NullCard();
            controller.setData(new_card);
        }
    }

    private void renderShuffled(){
        // Render the card field
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < 4; i++) {
                Card new_card = new NullCard();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(new_card);
                cardController.setType("view");
                controllers.add(cardController);

                // Add the anchorPane to the GridPane
                card_grid.add(anchorPane, column, row);


                // Increment column and row for the next card
                column++;
                if (column == 2) {
                    column = 0;
                    row++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}

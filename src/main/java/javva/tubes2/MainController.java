package javva.tubes2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private GridPane card_field;

    private List<TempCard> list_of_cards = new ArrayList<>();

    private List<TempCard> generateRandom(){
        List<TempCard> tempCards = new ArrayList<>();
        TempCard tempCard = new TempCard();
        for(int i = 0; i<20; i++){
            tempCard.setName("Chicken");
            tempCard.setImgSrc("javva/tubes2/images/Hewan/chicken.png");
            tempCards.add(tempCard);
        }
        return tempCards;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list_of_cards.addAll(generateRandom());
        int column = 0;
        int row = 0;
        try {
            for(int i = 0; i<list_of_cards.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();


                CardController cardController = fxmlLoader.getController();
                cardController.setData(list_of_cards.get(i));

                card_field.add(anchorPane, column, row);

                column++;
                System.out.println(column);
                System.out.println(row);
                if (column == 4){
                    column = 0;
                    row ++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package javva.tubes2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Random;

public class MainController implements Initializable  {

    @FXML
    private GridPane card_field;

    @FXML
    private GridPane deck_field;

    private List<TempCard> list_of_cards = new ArrayList<>();

    private List<TempCard> generateRandom(){
        List<TempCard> tempCards = new ArrayList<>();
        TempCard tempCard = new TempCard();
        for(int i = 0; i<20; i++){
            // Generate random number
            Random random = new Random();
            int min = 1;
            int max = 6;
            int randomInRange = random.nextInt(max - min + 1) + min;
            switch(randomInRange){
                case 1:
                    tempCard.setName("Chicken");
                    tempCard.setImgSrc("@images/Hewan/chicken.png");
                    tempCards.add(tempCard);
                    System.out.println("Chicken");
                    break;
                case 2:
                    tempCard.setName("Bear");
                    tempCard.setImgSrc("@images/Hewan/bear.png");
                    tempCards.add(tempCard);
                    System.out.println("Bear");
                    break;
                case 3:
                    tempCard.setName("Cow");
                    tempCard.setImgSrc("@images/Hewan/cow.png");
                    tempCards.add(tempCard);
                    System.out.println("Cow");
                    break;
                case 4:
                    tempCard.setName("Hiu Darat");
                    tempCard.setImgSrc("@images/Hewan/hiu_darat.png");
                    tempCards.add(tempCard);
                    System.out.println("Shark");
                    break;
                case 5:
                    tempCard.setName("Horse");
                    tempCard.setImgSrc("@images/Hewan/horse.png");
                    tempCards.add(tempCard);
                    System.out.println("Horse");
                    break;
                case 6:
                    tempCard.setName("Sheep");
                    tempCard.setImgSrc("@images/Hewan/sheep.png");
                    tempCards.add(tempCard);
                    System.out.println("Sheep");
                    break;
            }


            tempCard.setName("Chicken");
            tempCard.setImgSrc("@images/Hewan/chicken.png");
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
            for (int i = 0; i < 20; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(list_of_cards.get(i));

                // Add the anchorPane to the GridPane
                card_field.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(10)); // Set a uniform margin

                // Increment column and row for the next card
                column++;
                if (column == 5) {
                    column = 0;
                    row++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            column = 0;
            for (int i = 0; i < 6; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(list_of_cards.get(i));

                // Add the anchorPane to the GridPane
                deck_field.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(10)); // Set a uniform margin
                column ++;
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

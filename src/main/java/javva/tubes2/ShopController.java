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
import java.util.Random;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    private Button back_button;

    @FXML
    private GridPane shop_grid;

    private List<TempCard> items = new ArrayList<>(); ;

    @FXML
    private void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderShopItems(generateRandom(9));
    }


    public void renderShopItems(List<TempCard> cards ){
        // Render the card field
        items.addAll(cards);
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < 9; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card-shop.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardShopController cardShopController = fxmlLoader.getController();
                cardShopController.setData(items.get(i));

                // Add the anchorPane to the GridPane
                shop_grid.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(0)); // Set a uniform margin

                // Increment column and row for the next card
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<TempCard> generateRandom(int len){
        List<TempCard> tempCards = new ArrayList<>();
        for(int i = 0; i<len; i++){
            TempCard tempCard = new TempCard();
            // Generate random number
            Random random = new Random();
            int min = 1;
            int max = 6;
            int randomInRange = random.nextInt(max - min + 1) + min;
            switch(randomInRange){
                case 1:
                    tempCard.setName("Chicken");
                    tempCard.setImgSrc("/javva/tubes2/images/Hewan/chicken.png");
                    tempCards.add(tempCard);
                    System.out.println("Chicken");
                    break;
                case 2:
                    tempCard.setName("Bear");
                    tempCard.setImgSrc("/javva/tubes2/images/Hewan/bear.png");
                    tempCards.add(tempCard);
                    System.out.println("Bear");
                    break;
                case 3:
                    tempCard.setName("Cow");
                    tempCard.setImgSrc("/javva/tubes2/images/Hewan/cow.png");
                    tempCards.add(tempCard);
                    System.out.println("Cow");
                    break;
                case 4:
                    tempCard.setName("Hiu Darat");
                    tempCard.setImgSrc("/javva/tubes2/images/Hewan/hiu_darat.png");
                    tempCards.add(tempCard);
                    System.out.println("Shark");
                    break;
                case 5:
                    tempCard.setName("Horse");
                    tempCard.setImgSrc("/javva/tubes2/images/Hewan/horse.png");
                    tempCards.add(tempCard);
                    System.out.println("Horse");
                    break;
                case 6:
                    tempCard.setName("Sheep");
                    tempCard.setImgSrc("/javva/tubes2/images/Hewan/sheep.png");
                    tempCards.add(tempCard);
                    System.out.println("Sheep");
                    break;
            }

        }
        return tempCards;
    }



}

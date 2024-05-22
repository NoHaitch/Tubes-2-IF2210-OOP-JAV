package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
import javafx.scene.control.Button;

public class MainController implements Initializable  {
    // Attributes
    private List<TempCard> list_of_cards = new ArrayList<>();

    // GridPanes
    @FXML
    private GridPane active_deck;
    @FXML
    private GridPane field;

    // Labels
    @FXML
    private Label active_deck_number;
    @FXML
    private Label player_1;
    @FXML
    private Label player_11;
    @FXML
    private Label turn_label;

    // Buttons
    @FXML
    private Button load_plugin_button;
    @FXML
    private Button enemy_field_button;
    @FXML
    private Button load_state_button;
    @FXML
    private Button my_field_button;
    @FXML
    private Button save_state_button;
    @FXML
    private Button shop_button;



    //  Methode

    // Buttons
    @FXML
    void nextTurn(ActionEvent event) {
    }

    @FXML
    void loadPlugin(ActionEvent event) {
        load_plugin_button.setDisable(true);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);
    }

    @FXML
    void loadState(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(true);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);
    }


    @FXML
    void saveState(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(true);
        shop_button.setDisable(false);
    }

    @FXML
    void showEnemyField(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(true);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

        field.getChildren().clear();
        active_deck.getChildren().clear();
        renderField(generateRandom());
        renderActiveDeck(generateRandom());
    }

    @FXML
    void showMyField(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(true);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

        field.getChildren().clear();
        active_deck.getChildren().clear();
        renderField(generateRandom());
        renderActiveDeck(generateRandom());
    }

    @FXML
    void showShop(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderField(generateRandom());

    }

    // Others
    private List<TempCard> generateRandom(){
        List<TempCard> tempCards = new ArrayList<>();
        for(int i = 0; i<20; i++){
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
//    private List<TempCard> setRedZone(List<TempCard> tempCards, Integer row_start, Integer col_start){
//
//    }
//    private List<TempCard> clearRedZone(List<TempCard> tempCards){
//
//    }


    // Renders
    public void renderField(List<TempCard> cards ){
        // Render the card field
        list_of_cards.clear();
        list_of_cards.addAll(cards);
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
                field.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(0)); // Set a uniform margin

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
    }

    public void renderActiveDeck(List<TempCard> cards){
        try {
            Integer column = 0;
            Integer row = 0;
            for (int i = 0; i < 6; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(list_of_cards.get(i));

                // Add the anchorPane to the GridPane
                active_deck.add(anchorPane, column, row);

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

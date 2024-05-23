package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.ls.LSOutput;

public class MainController implements Initializable  {
    // Constant
    public static String BEARZONE = "FDC7C7";

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
    @FXML
    private Label information;

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

    // Anchors
    @FXML
    private AnchorPane pane;

    // Attributes
    private GaussianBlur blur;
    private List<TempCard> list_of_cards = new ArrayList<>();
    private List<CardController> field_controllers = new ArrayList<>();
    private List<CardController> active_deck_controllers = new ArrayList<>();

    //  Methods
    // Buttons
    @FXML
    void nextTurn(ActionEvent event) {}
    @FXML
    void loadPlugin(ActionEvent event) {
//        load_plugin_button.setDisable(true);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

        renderLoadPlugin();
    }
    @FXML
    void loadState(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
//        load_state_button.setDisable(true);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

        renderLoadState();
    }
    @FXML
    void saveState(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
//        save_state_button.setDisable(true);
        shop_button.setDisable(false);

        renderSaveState();
    }
    @FXML
    void showEnemyField(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(true);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);


    }
    @FXML
    void showMyField(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(true);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

    }
    @FXML
    void showShop(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
//        shop_button.setDisable(true);
        renderShop();

    }


    private void setInformationLabel(String text){
        information.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Rendering the field
        try {
            Integer column = 0;
            Integer row = 0;
            for (int i = 0; i < 20; i++) {
                TempCard empty = new TempCard();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(empty);

                // adding card controller for further manipulation
                field_controllers.add(cardController);

                // Add the anchorPane to the GridPane
                field.add(anchorPane, column, row);

                // Set margin around the anchorPane
//                GridPane.setMargin(anchorPane, new Insets(10)); // Set a uniform margin
                column++;
                if (column == 5) {
                    column = 0;
                    row++;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Gagal me-load ladang");;
        }

        // Rendering active deck
        try {
            Integer column = 0;
            Integer row = 0;
            for (int i = 0; i < 6; i++) {
                TempCard empty = new TempCard();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(empty);

                // adding card controller to further manipulation
                active_deck_controllers.add(cardController);
                // Add the anchorPane to the GridPane
                active_deck.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(10)); // Set a uniform margin
                column ++;
            }
        }
        catch (IOException e) {
            System.out.println("Gagal me-load deck aktif");
        }

        // Testing buat render active deck
        for (int i = 0; i < 6; i++) {
            TempCard change = new TempCard();
            change.setName("Chiken");
            change.setImgSrc("/javva/tubes2/images/Hewan/chicken.png");
            setActiveDeckCard(i, change);
        }

        // Testing set bear zone
        List<Integer> area = new ArrayList<>(List.of(1, 3, 4, 5, 6, 9, 19));
        setBearZone(area);
        removeBearZone();
    }

    // Generate
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

    // Renders
    public void renderLoadState(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("load-state.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Failed to load");
            e.printStackTrace();
            return; // Return here to prevent further execution in case of error
        }

        // Membuat stage baru untuk popup
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        popupStage.setScene(scene);

        // Tampilkan popup
        popupStage.show();
    }
    public void renderLoadPlugin(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("load-plugin.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Failed to load");
            e.printStackTrace();
            return; // Return here to prevent further execution in case of error
        }

        // Membuat stage baru untuk popup
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        popupStage.setScene(scene);

        // Tampilkan popup
        popupStage.show();
    }
    public void renderSaveState(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("save-state.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Failed to load");
            e.printStackTrace();
            return; // Return here to prevent further execution in case of error
        }

        // Membuat stage baru untuk popup
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        popupStage.setScene(scene);

        // Tampilkan popup
        popupStage.show();
    }
    public void renderShop(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Failed to load");
            e.printStackTrace();
            return; // Return here to prevent further execution in case of error
        }

        // Membuat stage baru untuk popup
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        popupStage.setScene(scene);

        // Tampilkan popup
        popupStage.show();
    }

    // Getters
    public TempCard getFieldCard(int id){
        return field_controllers.get(id).getCard();
    }
    public TempCard getActiveDeckCard(int id){
        return active_deck_controllers.get(id).getCard();
    }

    // Setters
    public void setFieldCard(int id, TempCard card){
        field_controllers.get(id).setData(card);
    }
    public void setActiveDeckCard(int id, TempCard card){
        field_controllers.get(id).setData(card);
    }
    public void setBearZone(List<Integer> ids){
        for (Integer id : ids){
            field_controllers.get(id).setCardBackground(BEARZONE);
        }
    }
    public void removeBearZone(){
        for (CardController field: field_controllers){
            field.setCardBackground(null);
        }
    }
}

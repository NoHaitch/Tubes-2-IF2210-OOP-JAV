package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javva.tubes2.Card.*;

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
    private List<Card> list_of_cards = new ArrayList<>();
    private Stage stage_shop = new Stage();
    private Stage save_state_stage = new Stage();
    private Stage load_state_stage = new Stage();
    private Stage load_plugin_stage = new Stage();
    private Stage shuffle_stage = new Stage();
    private List<CardController> field_controllers = new ArrayList<>();
    private List<CardController> active_deck_controllers = new ArrayList<>();
    private ShopController shop_controller = new ShopController();
    private PluginController plugin_controller = new PluginController();
    private LoadController load_controller = new LoadController();
    private SaveController save_controller = new SaveController();
    private ShuffleCardController shuffle_controller = new ShuffleCardController();

    //  Methods
    // Buttons
    @FXML
    void nextTurn(ActionEvent event) {
        // Implement GUI
    }
    @FXML
    void loadPlugin(ActionEvent event) {
//        load_plugin_button.setDisable(true);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

        load_plugin_stage.show();
    }
    @FXML
    void loadState(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
//        load_state_button.setDisable(true);
        my_field_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);

        load_state_stage.show();
    }
    @FXML
    void saveState(ActionEvent event) {
        load_plugin_button.setDisable(false);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        my_field_button.setDisable(false);
//        save_state_button.setDisable(true);
        shop_button.setDisable(false);

        save_state_stage.show();
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
        stage_shop.show();

    }


    private void setInformationLabel(String text){
        information.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderIntitiate();
        renderLoadState();
        renderLoadPlugin();
        renderSaveState();
        renderShop();
        renderShuffle();

        // Testing buat render active deck
        for (int i = 0; i < 6; i++) {
            Card change = new Card("","","");
            change.setName("Chicken");
            change.setPath("/javva/tubes2/images/Hewan/chicken.png");
            setActiveDeckCard(i, change);
        }
        shuffle_controller.setCards(generateRandom(3));
        shuffle_stage.show();

//        while(shuffle_stage.isShowing()){
//            System.out.println("Waiting ... ");
//        }

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

        // Nyimpen controller
        load_controller = loader.getController();

        // Membuat stage baru untuk popup
        load_state_stage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        load_state_stage.setScene(scene);

        // Tampilkan popup
//        load_state_stage.show();
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

        plugin_controller = loader.getController();

        // Membuat stage baru untuk popup
        load_plugin_stage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        load_plugin_stage.setScene(scene);

        // Tampilkan popup
//        load_plugin_stage.show();
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

        save_controller = loader.getController();

        // Membuat stage baru untuk popup

        save_state_stage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        save_state_stage.setScene(scene);

        // Tampilkan popup
//        popupStage.show();
    }
    public void renderShuffle(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("card-shuffle.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Failed to load");
            e.printStackTrace();
            return; // Return here to prevent further execution in case of error
        }

        shuffle_controller = loader.getController();

        // Membuat stage baru untuk popup

        shuffle_stage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        shuffle_stage.setScene(scene);

        // Tampilkan popup
//        popupStage.show();
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

        shop_controller = loader.getController();

        // Membuat stage baru untuk popup
        stage_shop.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        stage_shop.setScene(scene);

        // Tampilkan popup
//        stage_shop.show();

    }
    public void renderIntitiate(){
        // Rendering the field
        Boolean game_stopped = false;
        try {
            Integer column = 0;
            Integer row = 0;
            for (int i = 0; i < 20; i++) {
                Card empty = new Card("","","");
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
            System.out.println("Gagal me-load ladang");
        }

        // Rendering active deck
        try {
            Integer column = 0;
            Integer row = 0;
            for (int i = 0; i < 6; i++) {
                Card empty = new Card("","","");
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

    }


    // Getters
    public Card getFieldCard(int id){
        return field_controllers.get(id).getCard();
    }
    public Card getActiveDeckCard(int id){
        return active_deck_controllers.get(id).getCard();
    }

    // Setters
    public void setFieldCard(int id, Card card){
        field_controllers.get(id).setData(card);
    }
    public void setActiveDeckCard(int id, Card card){
        active_deck_controllers.get(id).setData(card);
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

    // Generate
    private List<Card> generateRandom(int len){
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i<len; i++){
            Card card = new Card("","","");
            // Generate random number
            Random random = new Random();
            int min = 1;
            int max = 6;
            int randomInRange = random.nextInt(max - min + 1) + min;
            switch(randomInRange){
                case 1:
                    card.setName("Chicken");
                    card.setPath("/javva/tubes2/images/Hewan/chicken.png");
                    cards.add(card);
                    System.out.println("Chicken");
                    break;
                case 2:
                    card.setName("Bear");
                    card.setPath("/javva/tubes2/images/Hewan/bear.png");
                    cards.add(card);
                    System.out.println("Bear");
                    break;
                case 3:
                    card.setName("Cow");
                    card.setPath("/javva/tubes2/images/Hewan/cow.png");
                    cards.add(card);
                    System.out.println("Cow");
                    break;
                case 4:
                    card.setName("Hiu Darat");
                    card.setPath("/javva/tubes2/images/Hewan/hiu_darat.png");
                    cards.add(card);
                    System.out.println("Shark");
                    break;
                case 5:
                    card.setName("Horse");
                    card.setPath("/javva/tubes2/images/Hewan/horse.png");
                    cards.add(card);
                    System.out.println("Horse");
                    break;
                case 6:
                    card.setName("Sheep");
                    card.setPath("/javva/tubes2/images/Hewan/sheep.png");
                    cards.add(card);
                    System.out.println("Sheep");
                    break;
            }

        }
        return cards;
    }

}

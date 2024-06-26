package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
import javva.tubes2.Player.*;
import javva.tubes2.GameMaster.*;

public class MainController implements Initializable  {
    // Constant
    public static String BEARZONE = "FDC7C7";

    // GridPanes
    @FXML
    private GridPane active_deck;
    @FXML
    private GridPane field;
    @FXML
    private GridPane card_grid;

    // Labels
    @FXML
    public Label active_deck_number;
    @FXML
    public Label player_1;
    @FXML
    public Label player_2;
    @FXML
    private Label turn_label;
    @FXML
    private Label information;
    @FXML
    private HBox player1_color;
    @FXML
    private HBox player2_color;

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
    public ShopController shop_controller = new ShopController();
    private PluginController plugin_controller = new PluginController();
    private LoadController load_controller = new LoadController();
    private SaveController save_controller = new SaveController();
    private ShuffleCardController shuffle_controller = new ShuffleCardController();
    
    public List<CardController> active_deck_controllers = new ArrayList<>();
    public GameMaster game;
    public Boolean field_shown = true;

    public Boolean isFull(){
        int count = 0;
        for(int i = 0 ; i < active_deck_controllers.size() ; i++){
            if(active_deck_controllers.get(i).card.getName() != "null"){
                count++;
            }
        }
        return count == 6;
    }

    //  Methods
    // Buttons
    @FXML
    void nextTurn(ActionEvent event) {
        saveField();
        saveDeck();

        resetAll();

        field_controllers.clear();
        active_deck_controllers.clear();


        if(game.player_turn){
            player1_color.setStyle("-fx-background-color: #F3FFEF;");
            player2_color.setStyle("-fx-background-color: #ffbf00;");
            
        } else {
            player1_color.setStyle("-fx-background-color: #ffbf00;");
            player2_color.setStyle("-fx-background-color: #F3FFEF;");
        }
        game.player1.getField().updatePlant();
        game.player2.getField().updatePlant();
        game.changeTurn();

        try{
            callShuffle(game.current_player.drawCards());
        } catch (Exception e){

        }

        turn_label.setText(game.turn + "");
        renderField(game.current_player);
        renderActiveDeck(game.current_player);
    }
    @FXML
    void loadPlugin(ActionEvent event) {
//        load_plugin_button.setDisable(true);
        resetAll();

        load_plugin_stage.show();
    }
    @FXML
    void loadState(ActionEvent event) {
        load_state_stage.show();
    }
    @FXML
    void saveState(ActionEvent event) {
        save_state_stage.show();
    }
    @FXML
    void showEnemyField(ActionEvent event) {
        enemy_field_button.setDisable(true);
        my_field_button.setDisable(false);

        if(!field_shown){
            return;
        } else {

        }
        
        saveField();

        field_shown = false;
        field_controllers.clear();
        if(game.player_turn){
            renderField(game.player2);
        } else {
            renderField(game.player1);
        }
    }
    @FXML
    void showMyField(ActionEvent event) {
        my_field_button.setDisable(true);
        enemy_field_button.setDisable(false);

        if(field_shown){
            return;
        } else {
        }
        field_shown = true;
        field_controllers.clear();
        renderField(game.current_player);
    }
    @FXML
    void showShop(ActionEvent event) {
        stage_shop.show();

    }


    // Game utility
    public void saveField(){
        if(field_shown){
                ArrayList<Harvestable> temp_field = new ArrayList<>();
                for(int i = 0 ; i < field_controllers.size() ; i++){
                    if(!field_controllers.get(i).getCard().getName().equals("null")){
                        temp_field.add((Harvestable)field_controllers.get(i).getCard());
                    } else {
                        temp_field.add(new NullCard());
                    }
                }
                game.current_player.getField().setContent(temp_field);
            }
        }

    public void saveDeck(){
        ArrayList<Card> temp_active_deck = new ArrayList<>();
        for(int i = 0 ; i < active_deck_controllers.size() ; i++){
            // if(!active_deck_controllers.get(i).getCard().getName().equals("null")){
                temp_active_deck.add(active_deck_controllers.get(i).getCard());
            // } else {
                // temp_active_deck.add(new NullCard());
            // }  
        }
        game.current_player.setActive_deck(temp_active_deck);
    }

    public void resetAll(){
        field_shown = true;
        my_field_button.setDisable(true);
        enemy_field_button.setDisable(false);
        load_state_button.setDisable(false);
        save_state_button.setDisable(false);
        shop_button.setDisable(false);
    }

    private void setInformationLabel(String text){
        information.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new GameMaster();
        my_field_button.setDisable(true);

        CardController.main = this;
        CardInfoController.main = this;
        CardShopController.main = this;
        ShopController.main = this;

        renderInitiate();
        renderLoadState();
        renderLoadPlugin();
        renderSaveState();
        renderShop();
        renderShuffle();


        active_deck_number.setText("Deck Count : " + game.avail_deck_count);

        try {

            callShuffle(game.current_player.drawCards());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        player_1.setText(game.player1.getGulden() + "");
        player_2.setText(game.player1.getGulden() + "");

        turn_label.setText(game.turn + "");

        player1_color.setStyle("-fx-background-color: #ffbf00;");


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
        shuffle_controller.main = this;

        // Membuat stage baru untuk popup

        shuffle_stage.initStyle(StageStyle.TRANSPARENT); // Use TRANSPARENT instead of UNDECORATED to allow transparency
        Scene scene = new Scene(root);
        scene.setFill(null); // Set the Scene's background to transparent
        shuffle_stage.setScene(scene);

        // Tampilkan popup
//        popupStage.show();
    }
    public void callShuffle(List<Card> cards){
        shuffle_controller.setData(cards);
        javafx.application.Platform.runLater(() -> {
            shuffle_stage.show();
        });
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

    public void renderField(Player player){
        try {
            field.getChildren().clear();
            Integer column = 0;
            Integer row = 0;
            
            field_controllers.clear();
            for (int i = 0; i < 20; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
        
                CardController cardController = fxmlLoader.getController();

                try {
                    cardController.setData(player.getField().getElement(i));
                    cardController.setType("field");
                } catch (Throwable e){
                    cardController.setData(new NullCard());
                    cardController.setType("field");
                }
                cardController.setId_field(i);
                cardController.setContainer("field");
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
        } catch (Throwable e) {

        }
    }

    public void renderActiveDeck(Player player){
        try {
            active_deck.getChildren().clear();
            active_deck_controllers.clear();
            Integer column = 0;
            Integer row = 0;
            for (int i = 0; i < 6; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setData(player.getActiveDeck().get(i));

                // adding card controller to further manipulation
                cardController.setContainer("deck");
                cardController.setId_deck(i);
                cardController.setType("deck");

                active_deck_controllers.add(cardController);

                // Add the anchorPane to the GridPane
                active_deck.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(10)); // Set a uniform margin
                column ++;
            }
        } catch (Throwable e) {

        }
    }

    public void renderInitiate(){
        renderField(game.current_player);

        renderActiveDeck(game.current_player);
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
}

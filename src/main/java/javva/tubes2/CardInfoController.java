package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.List;
import javva.tubes2.Card.*;
public class CardInfoController {

    @FXML
    private Button back_button;

    @FXML
    private ImageView item_image;

    @FXML
    private Label item_name;

    @FXML
    private Label item_weight;

    @FXML
    private Label items_active;

    @FXML
    private Label tipe;

    @FXML
    private Button panen_button;

    public static MainController main;
    public static Boolean full = false;

    private Card card;
    private Integer id = -1;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @FXML

    public void setData(Card card){
        this.card = card;
        item_name.setText(card.getName());
        if(card instanceof Animal){
            Animal add = (Animal)card;
            item_weight.setText(" "+ add.getWeight() + " / " + add.getHarvestWeight());
            tipe.setText("Weight");

            if(add.isHarvestReady()){
                panen_button.setDisable(false);
            } else {
                panen_button.setDisable(true);
            }
        }

        if(card instanceof Plants){
            Plants add = (Plants)card;
            item_weight.setText(" "+ add.getProgress() + " / " + add.getHarvestLimit());
            tipe.setText("Growth");
            
            if(add.isHarvestReady()){
                if(full){
                    panen_button.setDisable(true);
                } else {
                    panen_button.setDisable(false);
                }
            } else {
                panen_button.setDisable(true);
            }
        }
        
        if(!main.field_shown){
            panen_button.setDisable(true);
        }

        try{
            Image image = new Image(getClass().getResourceAsStream((card.getPath())));
            item_image.setImage(image);
        }
        catch(Exception e){
            // System.out.println("Image not found :" + card.getPath() );
        }
    }

    public void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

    @FXML
    void panenItem(ActionEvent event) {
        if(card instanceof Animal){
            Animal panen = (Animal)card;
        }

        if(card instanceof Plants){
            Plants panen = (Plants)card;
        }

        try {
            main.saveField();
            main.saveDeck();
            System.out.println(id);
            main.game.current_player.harvest(id);
            main.renderField(main.game.current_player);
            main.renderActiveDeck(main.game.current_player);
            main.saveField();
            main.saveDeck();
            
        } catch (Throwable e){
            
        }
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

}

package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.List;

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
    private Button panen_button;

    private TempCard card;

    @FXML

    public void setData(TempCard card){
        this.card = card;
        item_name.setText(card.getName());
        try{
            Image image = new Image(getClass().getResourceAsStream((card.getImgSrc())));
            item_image.setImage(image);
        }
        catch(Exception e){
            System.out.println("Image not found :" + card.getImgSrc() );
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
    }

}

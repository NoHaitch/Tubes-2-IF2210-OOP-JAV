package javva.tubes2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CardController {

    @FXML
    private AnchorPane card_background;

    @FXML
    private ImageView card_image;

    @FXML
    private Label card_name;

    private TempCard card;

    public void setData(TempCard card){
        this.card = card;
        card_name.setText(card.getName());
        card_background.setStyle("-fx-background-color: " + card.getHexColor() + ";");

        try{
            Image image = new Image(getClass().getResourceAsStream((card.getImgSrc())));
            card_image.setImage(image);
        }
        catch(Exception e){
            System.out.println("Image not found :" + card.getImgSrc() );
        }

    }

    public void setCardBackground(String color){
        card_background.setStyle("-fx-background-color: " + color + ";");
    }


}

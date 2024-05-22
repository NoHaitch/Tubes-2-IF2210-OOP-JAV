package javva.tubes2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CardShopController{

    @FXML
    private VBox card_frame;

    @FXML
    private ImageView card_image;

    @FXML
    private Label card_name;

    @FXML
    private Label harga;

    @FXML
    private Label jumlah;

    private TempCard card;

    public void setData(TempCard card){
        this.card = card;
        card_name.setText(card.getName());

        try{
            Image image = new Image(getClass().getResourceAsStream((card.getImgSrc())));
            card_image.setImage(image);
        }
        catch(Exception e){
            System.out.println("Image not found :" + card.getImgSrc() );
        }
    }


}

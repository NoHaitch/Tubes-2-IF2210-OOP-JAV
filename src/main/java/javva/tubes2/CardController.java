package javva.tubes2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardController {

    @FXML
    private ImageView card_image;

    @FXML
    private Label card_name;

    private TempCard card;

    public void setData(TempCard card){
        this.card = card;
        card_name.setText(card.getName());
//        Image image = new Image(getClass().getResourceAsStream((card.getImgSrc())));
//        card_image.setImage(image);

    }
}

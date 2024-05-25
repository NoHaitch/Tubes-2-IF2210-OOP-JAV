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
import javva.tubes2.Card.*;
public class CardShopController{
    public static MainController main;

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

    @FXML
    public void buyItem(){
        main.saveDeck();
        main.game.current_player.buy(card);
        main.renderActiveDeck(main.game.current_player);
        main.shop_controller.renderShopItems();
        if(main.game.player_turn){
            main.player_1.setText(main.game.current_player.getGulden() + "");
            main.player_2.setText(main.game.player2.getGulden() + "");
        } else {
            main.player_2.setText(main.game.current_player.getGulden() + "");
            main.player_1.setText(main.game.player1.getGulden() + "");
        }
    }

    @FXML
    public void Sell(){
        main.saveDeck();
        main.game.current_player.sell(card);
        main.renderActiveDeck(main.game.current_player);
        main.shop_controller.renderShopItems();
        if(main.game.player_turn){
            main.player_1.setText(main.game.current_player.getGulden() + "");
            main.player_2.setText(main.game.player2.getGulden() + "");
        } else {
            main.player_2.setText(main.game.current_player.getGulden() + "");
            main.player_1.setText(main.game.player1.getGulden() + "");
        }
    }

    private Product card;

    public void setData(Product card, int price, int stock){
        // Set kartu
        this.card = card;
        // Set label nama
        card_name.setText(card.getName());
        // Set gambar
        try{
            Image image = new Image(getClass().getResourceAsStream((card.getPath())));
            card_image.setImage(image);
        }
        catch(Exception e){
            // System.out.println("Image not found :" + card.getPath() );
        }
        // set harga
        harga.setText(Integer.toString(price));
        // set stok
        jumlah.setText(Integer.toString(stock));
    }


}

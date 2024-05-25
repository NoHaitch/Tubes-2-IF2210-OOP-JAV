package javva.tubes2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javva.tubes2.Card.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    public static MainController main;
    
    @FXML
    private Button back_button;

    @FXML
    private GridPane shop_grid;

    private List<Product> items = new ArrayList<>(); ;

    @FXML
    private void close() {
        // Get the current stage using the button's scene
        Stage stage = (Stage) back_button.getScene().getWindow();
        // Close the current stage
        stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderShopItems();
    }


    public void renderShopItems(){
        shop_grid.getChildren().clear();
        CardShopController.main = main;
        // Render the card field
        // items.addAll(cards);
        int column = 0;
        int row = 0;
        try {

            for (int i = 0; i < 9; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainController.class.getResource("card-shop.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardShopController cardShopController = fxmlLoader.getController();
                // productConfig;
                Product prod = main.game.main_shop.getItemConfig().get(i);
                cardShopController.setData(prod,main.game.main_shop.getPrice(prod.getName()), main.game.main_shop.getQuantity(prod.getName()));

                // Add the anchorPane to the GridPane
                shop_grid.add(anchorPane, column, row);

                // Set margin around the anchorPane
                GridPane.setMargin(anchorPane, new Insets(0)); // Set a uniform margin

                // Increment column and row for the next card
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

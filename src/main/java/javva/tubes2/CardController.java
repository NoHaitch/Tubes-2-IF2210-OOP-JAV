package javva.tubes2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardController {

    public static TempCard dragged_item;
    @FXML
    private VBox card_frame;

    @FXML
    private AnchorPane card_background;

    @FXML
    private ImageView card_image;

    @FXML
    private Label card_name;

    private TempCard card;



    public void setData(TempCard card){
        this.card = card;
        System.out.println();
        card_name.setText(card.getName());
        card_background.setStyle("-fx-background-color: " + card.getHexColor() + ";");

        try{
            Image image = new Image(getClass().getResourceAsStream((card.getImgSrc())));
            card_image.setImage(image);
        }
        catch(Exception e){
            System.out.println("Image not found :" + card.getImgSrc() );
            card_image.setImage(null);
        }

    }

    // Mouse activity
    public void dragDetected(MouseEvent event){
        Dragboard db = card_frame.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        cb.putString("Test");
        dragged_item = card;

        db.setContent(cb);
        event.consume();
        System.out.println("dragDetected");
    }
    public void dragOver(DragEvent event){
        System.out.println("You hovering while dragging on " + card.getName());
        event.acceptTransferModes(TransferMode.ANY);
    }
    public void dragDropped(){
        card = dragged_item;
        setData(card);
        System.out.println("Dropped");
    }
    public void onExitingHover(){
        setCardColor("");
    }
    public void onHover(){
        setCardColor("#B4FFD6");
    }

    public void setCardBackground(String color){
        card_background.setStyle("-fx-background-color: " + color + ";");
    }

    public void setCardColor(String color){
        card_frame.setStyle("-fx-background-color: " + color + ";");
    }


    public void showInfo() {
        FXMLLoader loader = new FXMLLoader(CardController.class.getResource("card-info.fxml"));
        Parent root = null;


        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Failed to load Info Cards");
//            e.printStackTrace();
            return;
        }

        CardInfoController controller = loader.getController();

        if (controller != null) {
            controller.setData(card);  // Assuming 'card' is a variable you want to pass to the controller
        } else {
            System.out.println("Controller is null");
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


}

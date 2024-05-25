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
import javva.tubes2.Card.*;
import java.io.IOException;

public class CardController {
    // GUIAttributes
    @FXML
    private VBox card_frame;
    @FXML
    private AnchorPane card_background;
    @FXML
    private ImageView card_image;
    @FXML
    private Label card_name;

    public static MainController main;

    // Attributes
    public Card card;
    private String type = "field";    // field : bisa di drag + liat info, deck : bisa di drag tapi gabisa liat info, view: gabisa diliat infonya dan gabisa di drag
    private String container = "deck";
    private static Card dragged_item;
    private static Card bottom_item;
    private static Boolean is_transferable_area = true;
    private static Boolean is_destroyable = false;
    private static Boolean is_area_clear = true;
    public static String GREEN = "#B4FFD6";

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    private int id_field = -1;

    // Mouse activity
    public void dragEntered(){
        is_transferable_area = true;
        System.out.println(is_transferable_area);
    }
    public void dragExited(){
        is_transferable_area = false;
        System.out.println(is_transferable_area);
    }
    public void dragDetected(MouseEvent event){
        System.out.println("woi "+main.field_shown.toString());
        if(type.equals("view") || (!main.field_shown && dragged_item instanceof Animal) || (!main.field_shown && dragged_item instanceof Plants)){
            return;
        }

        Dragboard db = card_frame.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        cb.putString("Test");
        dragged_item = card;

        db.setContent(cb);
        event.consume();
        System.out.println("drag detected on "+ card.getName());
    }
    public void dragOver(DragEvent event){
        if(card_image.getImage() == null){
            is_area_clear = true;
        }
        else if (card_image.getImage()!=null){
            is_area_clear = false;
        }
        bottom_item = card;
        // System.out.println("You hovering while dragging on " + card.getName() + is_transferable_area);
        event.acceptTransferModes(TransferMode.ANY);
    }
    public void dragDropped(){
        System.out.println(bottom_item.getName());
        System.out.println(dragged_item.getName());
        if(dragged_item instanceof Product){
            if(bottom_item instanceof Harvestable){
                if(bottom_item instanceof Animal){
                    try {
                        ((Animal)card).feed((Product)dragged_item);
                        setData(card);
                        is_destroyable = true;

                    } catch (Throwable e){
                        
                    }
                }
            }
        }

        if(dragged_item instanceof Item){
            return;
        }

        if (is_transferable_area && is_area_clear){
            card = dragged_item;
            setData(card);
            System.out.println("Dropped");
            is_destroyable = true;
        }
    }
    public void dragDone(){
        if (is_destroyable){
            card = new NullCard();
            setData(card);
            is_destroyable = false;
        }

    }
    public void onExitingHover(){
        setCardColor("");
    }
    public void onHover(){
        setCardColor(GREEN);
    }

    // SETTERS
    public void setCardBackground(String color){
        card_background.setStyle("-fx-background-color: " + color + ";");
    }
    public void setCardColor(String color){
        card_frame.setStyle("-fx-background-color: " + color + ";");
    }
    public void setData(Card card){
        this.card = card;
        if(!card.getName().equals("null")){
            card_name.setText(card.getName());
        } else {
            card_name.setText("");
        }
//        card_background.setStyle("-fx-background-color: " + null + ";");

        try{
            Image image = new Image(getClass().getResourceAsStream((card.getPath())));
            card_image.setImage(image);
        }
        catch(Exception e){
            // System.out.println("Image not found : " + card.getPath());
            card_image.setImage(null);
        }

    }
    public void setType(String type){
        this.type =type;
    }

    // GETTERS
    public Card getCard(){
        return card;
    }

    // RENDERS
    public void showInfo() {
        if (type.equals("view") || type.equals("deck") || card_image.getImage() == null){
            return;
        }

        CardInfoController.full = main.isFull();

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
        controller.setId(id_field);

        if (controller != null) {
            controller.setData(card);
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

    public int getId_field() {
        return id_field;
    }

    public void setId_field(int id_field) {
        this.id_field = id_field;
    }
}

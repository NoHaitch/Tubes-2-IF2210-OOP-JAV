package javva.tubes2.Card;

public class Item extends Card{
    public Item(String nameString, String tyString, String imgpth) {
        super(nameString, tyString, imgpth) ;
    }

    public Item(Item item) {
        super(item.getName(), item.getType(), item.getPath()) ;
    }
}

package javva.tubes2.Player;
import javva.tubes2.Card.* ;
import javva.tubes2.Shop;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int gulden ;
    private Deck deck ;
    private List<Card> active_deck ;
    private Field field;

    public Player() {
        this.gulden = 0 ;
        this.deck = new Deck(40) ;
        this.active_deck = new ArrayList<>() ;
        for (int i = 0 ; i < 6 ; i++) {
            active_deck.add(new NullCard()) ;
        }
        field = new Field(20);
    }

    public Player(int gulden, int deck_capacity) {
        this.gulden = gulden ;
        this.deck = new Deck(deck_capacity) ;
        this.active_deck = new ArrayList<>() ;
        for (int i = 0 ; i < 6 ; i++) {
            active_deck.add(new NullCard()) ;
        }
        field = new Field(20);        
    }

    public Field getField() {
        return this.field ;
    }

    public void addField(Card stuff, int index) {
        try {
            this.field.addElement((Harvestable)stuff, index);
        }
        catch (Throwable e) {
            System.out.println(e.getMessage()) ;
        }
    }

    public int getCapacity() {
        return this.deck.getCapacity() ;
    }

    public int getGulden() {
        return this.gulden ;
    }

    public void addGulden(int gulden) {
        this.gulden += gulden ;
    }

    // return pointer to active_deck. Jadi perubahan yang terjadi akan tersimpan.
    public List<Card> getActiveDeck() {
        List<Card> result = new ArrayList<>() ;
        for (int i = 0 ; i < 6 ; i++) {
            if(this.active_deck.get(i).getName() != "null") {
                result.add(active_deck.get(i)) ;
            }
        }
        return this.active_deck ;
    }

    public int countActiveCard() {
        int count = 0;
        for(int i = 0 ; i < 6 ; i++) {
            if (active_deck.get(i).getName() != "null") {
                count += 1 ;
            }
        }
        return count ;
    }

    public int findSlot() {
        int index = -1 ;
        for (int i = 0 ; i < 6 ; i++) {
            if (active_deck.get(i).getName() == "null") {
                index = i ;
                return index ;
            }
        }
        return index ;
    }

    public void addToActiveDeck(Card card) throws DeckIsFull{
        if (countActiveCard() < 6) {
            int index = findSlot() ;
            this.active_deck.remove(index) ;
            this.active_deck.add(index, card) ;
        }
        else {
            throw new DeckIsFull() ;
        }
    }

    public void removeFromActiveDeck(int index) throws IndexInvalid{
        if (index >= 0 && index < countActiveCard()) {
            this.active_deck.remove(index) ;
            this.active_deck.add(new NullCard()) ;
        }
        else {
            throw new IndexInvalid() ;
        }
    }

    // Melihat maksimal 4 kartu dari atas deck tanpa mengubah deck.
    public List<Card> drawCards() throws ActiveDeckFull{
        int draw_size = 6 - this.active_deck.size() ;
        if (draw_size <= 0) {
            throw new ActiveDeckFull() ;
        }
        if (draw_size > 4) {
            draw_size = 4 ;
        }
        return this.deck.drawCards(draw_size) ;
    }

    // Menghapus kartu sebanyak length dari atas deck 
    public void takeCards(int length) throws DeckIsEmpty{
        if (this.deck.getCapacity() < length) {
            throw new DeckIsEmpty() ;
        }
        this.deck.removeCards(length);
    }

    public void harvest(int index) {
        try {
            if (countActiveCard() <= 0) {
                throw new ActiveDeckFull() ;
            }
            Product newProduct = this.field.getAndRemove(index) ;
            addToActiveDeck(newProduct);
        }
        catch (ActiveDeckFull e) {
            System.out.println(e.getMessage()) ;
        }
        catch (Throwable e) {
            System.out.println(e.getMessage()) ;
        }      
    }

    public void feed(int food_index, int animal_index) {
        try {
            Animal animal = (Animal)this.field.getElement(animal_index) ;
            Product food = (Product)this.active_deck.get(food_index) ;
            animal.feed(food) ;
        }
        catch(Throwable e) {
            System.out.println(e.getMessage()) ;
        }
    }

    public void addToField(int card_index, int field_index) {
        try {
            this.field.addElement((Harvestable)this.active_deck.get(card_index), field_index);
            removeFromActiveDeck(card_index);
        }
        catch(Throwable e) {
            System.out.println(e.getMessage()) ;
        }
    }

    public void moveField(int start_index, int dest_index) {
        try {
            Harvestable stuff = this.field.getElement(start_index) ;
            if (stuff.getName() != "null") {
                this.field.addElement(stuff, dest_index);
            }
        }
        catch(Throwable e) {
            System.out.println(e.getMessage()) ;
        }
    }

    public void buy(String productName){
        try {
            Shop shop = Shop.getInstance();
            if (getGulden() >= shop.getPrice(productName)) {
                if (getActiveDeck().size()!=6){
                    addToActiveDeck(new Card(productName, "Product"));
                    shop.removeProduct(productName);
                    addGulden(-shop.getPrice(productName));
                } else {
                    throw new ActiveDeckFull();
                }
            } else {
                throw new NotEnoughMoney();
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    public void sell(int index){
        try {
            Shop shop = Shop.getInstance();
            if (this.active_deck.get(index).getType() == "Product"){
                removeFromActiveDeck(index);
                String productName = this.active_deck.get(index).getName();
                shop.addProduct(productName);
                addGulden(shop.getPrice(productName));
            } else {
                throw new NotSellable();
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     Player player = new Player() ;
    //     Card animal = new Animal("LandShark", "Carnivore", "", new Product("SharkFin", "Product", "", 12, 500), 0, 20) ;
    //     player.addField(animal, 0);
    //     try {
    //         System.out.println(player.field.getElement(0).getName()) ;
    //     }
    //     catch(Throwable e) {

    //     }
}

class DeckIsFull extends Exception {
    DeckIsFull(){
        super("It is already full!");
    }
}

class DeckIsEmpty extends Exception {
    DeckIsEmpty(){
        super("Deck is empty!");
    }
}

class IndexInvalid extends Exception {
    IndexInvalid(){
        super("Index out of range!");
    }
}

class ActiveDeckFull extends Exception {
    ActiveDeckFull(){
        super("Active deck is full!");
    }
}

class NotEnoughMoney extends Exception {
    NotEnoughMoney(){
        super("Gulden is not enough for this action!");
    }
}

class NotSellable extends Exception {
    NotSellable(){
        super("Card is not sellable since it's not a product");
    }
}
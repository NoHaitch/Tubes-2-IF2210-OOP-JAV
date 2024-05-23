package javva.tubes2.Player;
import javva.tubes2.Card.* ;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int gulden ;
    private Deck deck ;
    private List<Card> active_deck ;
    private Field field;

    public Player() {
        this.gulden = 0 ;
        this.deck = new Deck() ;
        this.active_deck = new ArrayList<>() ;
        for (int i = 0 ; i < 6 ; i++) {
            active_deck.add(new NullCard()) ;
        }
        field = new Field(20);
    }

    public Player(int gulden) {
        this.gulden = gulden ;
    }

    public int getDeckCapacity() {
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

    public void 

    public static void main(String[] args) {
        Player player = new Player() ;

        
        
        
        // System.out.println("aa");
        // Deck tes = new Deck() ;
        // List<Card> hand = tes.drawCards(1) ;
        // System.out.println(hand.size());
        // String name = hand.get(0).getName() ;
        // System.out.println(hand.get(0).getName()); 

        // hand.remove(0) ;
        // tes.removeCards(1);
    }
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
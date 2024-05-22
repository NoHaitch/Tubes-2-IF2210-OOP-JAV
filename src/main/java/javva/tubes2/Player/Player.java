package javva.tubes2.Player;
import javva.tubes2.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int gulden ;
    private Deck deck ;
    private List<Card> active_deck ;

    public Player() {
        this.gulden = 0 ;
        this.deck = new Deck() ;
        this.active_deck = new ArrayList<>() ;
    }

    public Player(int gulden) {
        this.gulden = gulden ;
    }

    public int getGulden() {
        return this.gulden ;
    }

    public void addGulden(int gulden) {
        this.gulden += gulden ;
    }

    // return pointer to active_deck. Jadi perubahan yang terjadi akan tersimpan.
    public List<Card> getActiveDeck() {
        return this.active_deck ;
    }

    public void addToActiveDeck(Card card, int index) throws DeckIsFull{
        if (this.active_deck.size() < 6 && index <= 5 && index >= 0) {
            this.active_deck.add(index, card) ;
        }
        else {
            throw new DeckIsFull() ;
        }
    }

    public void removeFromActiveDeck(int index) throws IndexInvalid{
        if (index >= 0 && index < this.active_deck.size()) {
            this.active_deck.remove(index) ;
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
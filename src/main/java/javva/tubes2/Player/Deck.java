package javva.tubes2.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javva.tubes2.Card.Card;

public class Deck {
    private List<Card> contain ;
    private int capacity ;

    public Deck() {
        contain = new ArrayList<>() ;
        capacity = 0 ;
    }

    public int getCapacity() {
        return this.capacity ;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.contain) ;
    }

    // Membuang kartu dari deck berdasarkan index
    public void removeCard(int index) {
        if (index < this.capacity) {
            this.contain.remove(index) ;
            this.capacity -= 1 ;
        }
    }

    // Membuang sejumlah kartu pertama sebanyak length
    public void removeCards(int length) {
        if (length > this.capacity) {
            length = this.capacity ;
        }
        for (int i = 0 ; i < length ; i++) {
            removeCard(i);
        }
    }

    // Menambahkan kartu pada deck
    public void addCard(Card card) {
        this.contain.add(card) ;
        this.capacity += 1 ;
    }

    // Mengambil kartu dari atas deck sebanyak length tanpa membuangnya dari deck
    public List<Card> drawCards(int length) {
        List<Card> result = new ArrayList<>() ;
        if (length > this.capacity) {
            length = this.capacity ;
        }
        for (int i = 0 ; i < length ; i++) {
            result.add(this.contain.get(i)) ;
        }
        return result ;
    }

    public List<Card> getAllCards() {
        return this.contain ;
    }

    public static void main(String[] args) {
        System.out.println("aa");
        Deck tes = new Deck() ;
        Card card = new Card("name", "type") ;
        tes.addCard(card);
        System.out.println(tes.contain.get(0).getName());

        List<Card> temp = tes.drawCards(1) ;

        tes.removeCard(0);
        System.out.println(temp.get(0).getName());

        // temp.remove(0) ;
        // System.out.println(tes.drawCards(1).get(0).getName());

        

    }

}

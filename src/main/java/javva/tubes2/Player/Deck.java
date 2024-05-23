package javva.tubes2.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javva.tubes2.Card.Card;
import javva.tubes2.CardConfig ;

public class Deck {
    private int capacity ;
    private List<Card> deck ;


    public Deck(int capacity) {
        CardConfig cardConfig = new CardConfig() ;
        deck = new ArrayList<>() ;
        deck.addAll(cardConfig.getAnimalConfig()) ;
        deck.addAll(cardConfig.getPlantConfig()) ;
        this.capacity = capacity;

        for (int i = 0 ; i < deck.size() ; i++) {
            if (deck.get(i).getName() == "Bear") {
                deck.remove(i) ;
                break ;
            }
        }
    }

    public int getCapacity() {
        return this.capacity ;
    }

    // Hanya melihat 4 kartu random dari deck tanpa mengambilnya.
    public List<Card> drawCards(int size) {
        Collections.shuffle(this.deck) ;
        if (size > this.capacity) {
            size = this.capacity ;
        }
        List<Card> result = new ArrayList<>() ;

        for (int i = 0 ; i < size ; i++) {
            result.addLast(this.deck.get(i));
        }

        return result ;
    }

    // Mengambil 4 kartu dari deck.
    public void removeCards(int size) {
        // for (int i = 0 ; i < this.deck.size() ; i++) {
        //     if (deck.get(i).getName() == name) {
        //         System.out.println("FOUND") ;
        //     }
        // }

        this.capacity -= size ;
    }

    // public void shuffleDeck() {
    //     Collections.shuffle(this.contain) ;
    // }

    // // Membuang kartu dari deck berdasarkan index
    // public void removeCard(int index) {
    //     if (index < this.capacity) {
    //         this.contain.remove(index) ;
    //         this.capacity -= 1 ;
    //     }
    // }

    // // Membuang sejumlah kartu pertama sebanyak length
    // public void removeCards(int length) {
    //     if (length > this.capacity) {
    //         length = this.capacity ;
    //     }
    //     for (int i = 0 ; i < length ; i++) {
    //         removeCard(i);
    //     }
    // }

    // // Menambahkan kartu pada deck
    // public void addCard(Card card) {
    //     this.contain.add(card) ;
    //     this.capacity += 1 ;
    // }

    // // Mengambil kartu dari atas deck sebanyak length tanpa membuangnya dari deck
    // public List<Card> drawCards(int length) {
    //     List<Card> result = new ArrayList<>() ;
    //     if (length > this.capacity) {
    //         length = this.capacity ;
    //     }
    //     for (int i = 0 ; i < length ; i++) {
    //         result.add(this.contain.get(i)) ;
    //     }
    //     return result ;
    // }

    // public List<Card> getAllCards() {
    //     return this.contain ;
    // }

    // public static void main(String[] args) {
    //     System.out.println("aa");
    //     Deck tes = new Deck(5) ;
    //     List<Card> hand = tes.drawCards(1) ;
    //     System.out.println(hand.size());
    //     String name = hand.get(0).getName() ;
    //     System.out.println(hand.get(0).getName()); 

    //     hand.remove(0) ;
    //     tes.removeCards(1);
    // }
}

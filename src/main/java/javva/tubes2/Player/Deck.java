package javva.tubes2.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javva.tubes2.Card.Card;
import javva.tubes2.Card.Product;
import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Plants;

public class Deck {
    // private List<Card> contain ;
    private int capacity ;
    private List<Product> products ;
    private List<Card> deck ;
    // private List<Animal> animals ;
    // private List<Plants> plants ;

    public Deck(int capacity) {
        deck = new ArrayList<>() ;
        this.capacity = capacity;

        products = new ArrayList<>() ;
        products.addLast(new Product("SharkFin", "Product", "", 12, 500));
        products.addLast(new Product("Milk", "Product", "", 4, 100));
        products.addLast(new Product("LambMeat", "Product", "", 6, 120));
        products.addLast(new Product("HorseMeat", "Product", "", 8, 150));
        products.addLast(new Product("Egg", "Product", "", 2, 50));
        products.addLast(new Product("Bearmeat", "Product", "", 12, 500));
        products.addLast(new Product("Corn", "Product", "", 3, 150));
        products.addLast(new Product("Pumpkin", "Product", "", 10, 500));
        products.addLast(new Product("Strawberry", "Product", "", 5, 350));

        // animals = new ArrayList<>() ;
        deck.addLast(new Animal("LandShark", "Carnivore", "", new Product("SharkFin", "Product", "", 12, 500), 0, 20));
        deck.addLast(new Animal("Cow", "Herbivore", "", new Product("Milk", "Product", "", 4, 100), 0, 10));
        deck.addLast(new Animal("Lamb", "Herbivore", "", new Product("LambMeat", "Product", "", 6, 120), 0, 12));
        deck.addLast(new Animal("Horse", "Herbivore", "", new Product("HorseMeat", "Product", "", 8, 150), 0, 14));
        deck.addLast(new Animal("Chicken", "Omnivore", "", new Product("Egg", "Product", "", 2, 50), 0, 5));
        deck.addLast(new Animal("Bear", "Omnivore", "", new Product("Bearmeat", "Product", "", 12, 500), 0, 25));

        // plants = new ArrayList<>() ;
        deck.addLast(new Plants("CornSeed", "Plant", "", new Product("Corn", "Product", "", 3, 150), 3));
        deck.addLast(new Plants("PumpkinSeed", "Plant", "", new Product("Pumpkin", "Product", "", 10, 500), 5));
        deck.addLast(new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4));



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

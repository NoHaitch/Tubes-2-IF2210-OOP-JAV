package javva.tubes2.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Card;
import javva.tubes2.Card.Item;
import javva.tubes2.Card.Plants;
import javva.tubes2.CardConfig ;

public class Deck {
    private int capacity ;
    private List<Card> deck ;
    private List<Animal> animal ;
    private List<Plants> plants ;
    private List<Item> item ;


    public Deck(int capacity) {
        CardConfig cardConfig = CardConfig.getInstance() ;
        animal = cardConfig.getAnimalConfig().stream().toList() ;
        plants = cardConfig.getPlantConfig().stream().toList() ;
        item = cardConfig.getItemConfig().stream().toList() ;

        deck = new ArrayList<>() ;
        deck.addAll(animal) ;
        deck.addAll(plants) ;
        deck.addAll(item) ;
        this.capacity = capacity;

        for (int i = 0 ; i < deck.size() ; i++) {
            if (deck.get(i).getName().equals("Bear")) {
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
            boolean found = false ;
            for (Animal addAnimal : this.animal) {
                if (addAnimal.getName().equalsIgnoreCase(this.deck.get(i).getName())) {
                    result.addLast(new Animal(addAnimal));
                    found = true ;
                    break ;
                }   
            }
            if (found) {
                continue ;
            }
            for (Plants addPlants : this.plants) {
                if (addPlants.getName().equalsIgnoreCase(this.deck.get(i).getName())) {
                    result.addLast(new Plants(addPlants));
                    found = true ;
                    break ;
                }   
            }       
            if (found) {
                continue ;
            }
            for (Item addItem : this.item) {
                if (addItem.getName().equalsIgnoreCase(this.deck.get(i).getName())) {
                    result.addLast(new Item(addItem));
                    found = true ;
                    break ;
                }   
            }                    
        }
        // System.out.printlcfn(result.get(0).getName()) ;
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

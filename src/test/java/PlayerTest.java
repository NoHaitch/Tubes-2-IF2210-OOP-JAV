import javva.tubes2.Card.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import javva.tubes2.Player.Player;
import javva.tubes2.Player.Field;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testConstructorAndGetters() {
        Field field = player.getField();
        List<Card> activeDeck = player.getActiveDeck();
        assertNotNull(field);
        assertEquals(40, player.getCapacity());
        assertEquals(0, player.getGulden());
        assertEquals(6, activeDeck.size());
        assertTrue(activeDeck.stream().allMatch(card -> card.getName()=="null"));
    }

//    @Test
//    public void testAddToActiveDeck() {
//        Card card = new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100), 0, 10);
//        try {
//            player.addToActiveDeck(card, 0);
//        } catch (Throwable e) {
//            fail("Unexpected exception: " + e.getMessage());
//        }
//        List<Card> activeDeck = player.getActiveDeck();
//        System.out.println(activeDeck);
//        assertEquals(card, activeDeck.get(0));
//    }

    @Test
    public void testDrawToActiveDeck() {
        List<Card> drawnCards = new ArrayList<>();
        drawnCards.add(new Animal("LandShark", "Carnivore", "/javva/tubes2/images/Hewan/hiu_darat.png", new Product("SharkFin", "Meat", "/javva/tubes2/images/Produk/shark-fin.png", 12, 500), 0, 20));
        drawnCards.add(new Plants("CornSeed", "Plant", "/javva/tubes2/images/Tanaman/corn seeds.png", new Product("Corn", "Vegetable", "/javva/tubes2/images/Produk/corn.png", 3, 150), 3));
        drawnCards.add(new Item("Destroy", "Item", "/javva/tubes2/images/Item/Destroy.png"));
        drawnCards.add(new NullCard());
        try {
            player.drawToActiveDeck(drawnCards);
        } catch (Throwable e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        List<Card> activeDeck = player.getActiveDeck();
        assertEquals(6, activeDeck.size());
        for (int i=0; i<4; i++){
            assertEquals(drawnCards.get(i), activeDeck.get(i));
        }
        // assertEquals(drawnCards, activeDeck);
    }

//    @Test
//    public void testTakeCards() {
//        player.drawToActiveDeck(new ArrayList<>());
//        try {
//            player.takeCards(5);
//            fail("Expected DeckIsEmpty exception was not thrown");
//        } catch (DeckIsEmpty e) {
//            assertEquals("Deck is empty!", e.getMessage());
//        } catch (Throwable e) {
//            fail("Unexpected exception: " + e.getMessage());
//        }
//        List<Card> drawnCards = new ArrayList<>();
//        drawnCards.add(new Animal("Lion", "Mammal", "", null, 150, 300));
//        drawnCards.add(new Plants("Sunflower", "Flower", "", 50, 100));
//        drawnCards.add(new Item("Shovel", "Tool", ""));
//        drawnCards.add(new NullCard());
//        player.drawToActiveDeck(drawnCards);
//        try {
//            player.takeCards(3);
//        } catch (Throwable e) {
//            fail("Unexpected exception: " + e.getMessage());
//        }
//
//        // Assert
//        assertEquals(1, player.getCapacity()); // Deck capacity should be reduced to 1
//    }

//    @Test
//    public void testHarvest() {
//        Field field = new Field();
//        player.setField(field);
//        Animal animal = new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100), 0, 10);
//        try {
//            field.addElement(animal, 0);
//        } catch (Throwable e) {
//            fail("Unexpected exception: " + e.getMessage());
//        }
//        player.harvest(0);
//        List<Card> activeDeck = player.getActiveDeck();
//        assertEquals(animal.getProduct(), activeDeck.get(0));
//    }
}
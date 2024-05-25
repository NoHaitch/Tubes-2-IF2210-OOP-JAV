import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void testHarvestReady() {
        Animal animal = new Animal("Cow", "Herbivore", "", new Product("Milk", "Product", "", 4, 100), 0, 10);

        assertFalse(animal.isHarvestReady(), "Cow must not be Harvestable yet");
        animal.setWeight(20);
        assertTrue(animal.isHarvestReady(), "Cow is already Harvestable");
    }

    @Test
    public void testFeed() {
        try{
            Animal cow = new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100), 0, 10);
            assertEquals(0, cow.getWeight(), "LambMeat must not be consumable by cow");
            Product Pumpkin = new Product("Pumpkin", "Vegetable", "", 10, 500);
            cow.feed(Pumpkin);
            assertEquals(10, cow.getWeight(), "Pumpkin must be able to be consumed by Cow");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

//    @Test(expected = NotReadyToHarvest.class)
//    public void testHarvest() throws NotReadyToHarvest {
//        Animal animal = new Animal("Cow", "Herbivore", "img.jpg", new Product("Hay", "Vegetable", 10), 300, 200);
//        animal.harvest();
//    }
}
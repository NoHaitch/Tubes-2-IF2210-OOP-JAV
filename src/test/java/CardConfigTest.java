import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Item;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Product;
import javva.tubes2.CardConfig;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardConfigTest {

    @Test
    public void testSingletonInstance() {
        CardConfig instance1 = CardConfig.getInstance();
        CardConfig instance2 = CardConfig.getInstance();

        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    public void testGetCardConfigs(){
        Set<String> expectedProductConfig = new HashSet<>();
        expectedProductConfig.add("SharkFin");
        expectedProductConfig.add("Milk");
        expectedProductConfig.add("LambMeat");
        expectedProductConfig.add("HorseMeat");
        expectedProductConfig.add("Egg");
        expectedProductConfig.add("Bearmeat");
        expectedProductConfig.add("Corn");
        expectedProductConfig.add("Pumpkin");
        expectedProductConfig.add("Strawberry");

        Set<String> expectedAnimalConfig = new HashSet<>();
        expectedAnimalConfig.add("LandShark");
        expectedAnimalConfig.add("Cow");
        expectedAnimalConfig.add("Lamb");
        expectedAnimalConfig.add("Horse");
        expectedAnimalConfig.add("Chicken");
        expectedAnimalConfig.add("Bear");

        Set<String> expectedPlantConfig = new HashSet<>();
        expectedPlantConfig.add("CornSeed");
        expectedPlantConfig.add("PumpkinSeed");
        expectedPlantConfig.add("StrawberrySeed");

        Set<String> expectedItemConfig = new HashSet<>();
        expectedItemConfig.add("Accelerate");
        expectedItemConfig.add("Delay");
        expectedItemConfig.add("InstantHarvest");
        expectedItemConfig.add("Destroy");
        expectedItemConfig.add("Protect");
        expectedItemConfig.add("Trap");

        for (Product element : CardConfig.getInstance().getProductConfig()) {
            assertTrue(expectedProductConfig.contains(element.getName()), "Element expected to be present");
        }

        for (Animal element : CardConfig.getInstance().getAnimalConfig()) {
            assertTrue(expectedAnimalConfig.contains(element.getName()), "Element expected to be present");
        }

        for (Plants element : CardConfig.getInstance().getPlantConfig()) {
            assertTrue(expectedPlantConfig.contains(element.getName()), "Element expected to be present");
        }

        for (Item element : CardConfig.getInstance().getItemConfig()) {
            assertTrue(expectedItemConfig.contains(element.getName()), "Element expected to be present");
        }
    }
}

import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Product;
import org.junit.Test;
import static org.junit.Assert.*;
import javva.tubes2.Card.Harvestable;

public class HarvestableTest {

    @Test
    public void testIsHarvestReady_NotReady() {
        Harvestable harvestable = new Harvestable("Chicken", "Omnivore");
        assertFalse(harvestable.isHarvestReady());
    }

//    @Test
//    public void testHarvest_NotReady() {
//        Harvestable harvestable = new Harvestable("Chicken", "Omnivore");
//        try {
//            harvestable.harvest();
//            fail("NotReadyToHarvest exception should be thrown when harvest is not ready");
//        } catch (NotReadyToHarvest e) {
//            // Assert
//            assertEquals("Object not ready for harvest", e.getMessage(), "NotReadyToHarvest message should indicate object is not ready for harvest");
//        }
//    }

    @Test
    public void testProtectionStatus() {
        Harvestable harvestable = new Harvestable("Chicken", "Omnivore");
        harvestable.setProtect(true);
        assertTrue("Protection status should be true after setting to true", harvestable.getProtect());
        harvestable.setProtect(false);
        assertFalse("Protection status should be false after setting to false", harvestable.getProtect());
    }

    @Test
    public void testTrapStatus() {
        Harvestable harvestable = new Harvestable("Chicken", "Omnivore");
        harvestable.setTrap(true);
        assertTrue("Trap status should be true after setting to true", harvestable.getTrap());
        harvestable.setTrap(false);
        assertFalse("Trap status should be false after setting to false", harvestable.getTrap());
    }
}
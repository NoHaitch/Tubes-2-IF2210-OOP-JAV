import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javva.tubes2.Card.Product;
import javva.tubes2.Card.Plants;

public class PlantsTest {

    @Test
    public void testIsHarvestReady_NotReady() {
        Plants strawberry = new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4);
        assertFalse(strawberry.isHarvestReady(), "Harvest should not be ready yet");
    }

    @Test
    public void testIsHarvestReady_Ready() {
        Plants strawberry = new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4);
        strawberry.setProgress(5);
        assertTrue(strawberry.isHarvestReady(), "Harvest should be ready");
    }

    @Test
    public void testHarvest_Ready() {
        Plants strawberry = new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4);
        strawberry.setProgress(5);
        assertDoesNotThrow(() -> {
            Product harvestedProduct = strawberry.harvest();
            assertNotNull(harvestedProduct, "Harvested product should not be null");
            assertEquals(strawberry.getProduct(), harvestedProduct, "Harvested product should be the same as the return product");
        }, "Harvest should not throw an exception");
    }

//    @Test
//    public void testHarvest_NotReady() {
//        Plants strawberry = new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4);
//        NotReadyToHarvest exception = assertThrows(NotReadyToHarvest.class, plants::harvest);
//        assertEquals("Harvest is not ready", exception.getMessage(), "NotReadyToHarvest message should indicate harvest is not ready");
//    }

    @Test
    public void testGrow() {
        Plants strawberry = new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4);
        strawberry.grow();
        assertEquals(1, strawberry.getProgress(), "Progress should increase by 1 after growing");
    }
}

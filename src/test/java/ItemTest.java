import org.junit.Test;
import static org.junit.Assert.*;
import javva.tubes2.Card.Item;

public class ItemTest {
    @Test
    public void testConstructorWithNameTypeImgPath() {
        Item item = new Item("InstantHarvest", "Item", "");
        assertEquals("InstantHarvest", item.getName());
        assertEquals("Item", item.getType());
        assertEquals("", item.getPath());
    }

    @Test
    public void testCopyConstructor() {
        Item sourceItem = new Item("InstantHarvest", "Item", "");
        Item newItem = new Item(sourceItem);
        assertEquals(newItem.getName(), sourceItem.getName());
        assertEquals(newItem.getType(), sourceItem.getType());
        assertEquals(newItem.getPath(), sourceItem.getPath());
    }
}

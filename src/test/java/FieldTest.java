import javva.tubes2.Card.NullCard;
import javva.tubes2.Card.Product;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javva.tubes2.Player.Field;
import javva.tubes2.Card.Harvestable;
import javva.tubes2.Card.Animal;
import javva.tubes2.Card.NullCard;
import java.util.ArrayList;
import java.util.List;

public class FieldTest {

    private Field field;

    @Before
    public void setUp() {
        field = new Field(30);
    }

    @Test
    public void testConstructorAndGetCapacity() {
        int expectedCapacity = 30;
        int actualCapacity = field.content.size();
        assertEquals(expectedCapacity, actualCapacity);
    }

    @Test
    public void testAddElement() {
        Harvestable animal = new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100), 0, 10);
        int index = 3;
        try {
            field.addElement(animal, index);
        } catch (Throwable e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertEquals(animal, field.getElement(index));
        } catch (Throwable e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}

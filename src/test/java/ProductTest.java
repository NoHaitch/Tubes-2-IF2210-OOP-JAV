import org.junit.Test;
import static org.junit.Assert.*;
import javva.tubes2.Card.Product;

public class ProductTest {

    @Test
    public void testGettersAndSetters() {
        Product product = new Product("Corn", "Vegetable", "", 3, 150);

        assertEquals("Corn", product.getName());
        assertEquals("Vegetable", product.getType());
        assertEquals("", product.getPath());
        assertEquals(Integer.valueOf(3), product.getAddedWeight());
        assertEquals(Integer.valueOf(150), product.getAddedMoney());

        product.setAddedWeight(15);
        assertEquals(Integer.valueOf(15), product.getAddedWeight());

        product.setAddedMoney(25);
        assertEquals(Integer.valueOf(25), product.getAddedMoney());
    }

    @Test
    public void testCopyConstructor() {
        Product product = new Product("Corn", "Vegetable", "", 3, 150);
        Product copy = new Product(product);

        assertEquals(product.getName(), copy.getName());
        assertEquals(product.getType(), copy.getType());
        assertEquals(product.getPath(), copy.getPath());
        assertEquals(product.getAddedWeight(), copy.getAddedWeight());
        assertEquals(product.getAddedMoney(), copy.getAddedMoney());
    }
}

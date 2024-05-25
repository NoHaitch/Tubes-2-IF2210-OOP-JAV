import org.junit.Test;
import static org.junit.Assert.*;
import javva.tubes2.Card.Card;

public class CardTest {

    @Test
    public void testConstructorWithNameTypeImgPath() {
        Card sharkFinCard = new Card("SharkFin", "Meat", "");
        assertEquals("SharkFin", sharkFinCard.getName());
        assertEquals("Meat", sharkFinCard.getType());
        assertEquals("", sharkFinCard.getPath());
    }

    @Test
    public void testConstructorWithNameType() {
        Card sharkFinCard = new Card("SharkFin", "Meat");
        assertEquals("SharkFin", sharkFinCard.getName());
        assertEquals("Meat", sharkFinCard.getType());
        assertEquals("", sharkFinCard.getPath());
    }

    @Test
    public void testSetNameAndGetName() {
        Card sharkFinCard = new Card("SharkFin", "Meat", "");
        sharkFinCard.setName("Merlin");
        assertEquals("Merlin", sharkFinCard.getName());
    }

    @Test
    public void testSetTypeAndGetType() {
        Card sharkFinCard = new Card("SharkFin", "Meat", "");
        sharkFinCard.setType("WeirdObjectWhyWouldYouEvenEatThat");
        assertEquals("WeirdObjectWhyWouldYouEvenEatThat", sharkFinCard.getType());
    }

    @Test
    public void testSetPathAndGetPath() {
        Card sharkFinCard = new Card("SharkFin", "Meat", "");
        sharkFinCard.setPath("Merlin.jpg");
        assertEquals("Merlin.jpg", sharkFinCard.getPath());
    }
}


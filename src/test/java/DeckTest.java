import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javva.tubes2.Card.Card;
import javva.tubes2.Player.Deck;
import java.util.List;

public class DeckTest {

    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck(40);
    }

    @Test
    public void testConstructorAndGetCapacity() {
        int expectedCapacity = 40;
        int actualCapacity = deck.getCapacity();
        assertEquals(expectedCapacity, actualCapacity);
    }

    @Test
    public void testDrawCards() {
        int drawSize = 6;
        List<Card> drawnCards = deck.drawCards(drawSize);
        assertNotNull(drawnCards);
        assertEquals(drawSize, drawnCards.size());
        for (Card card : drawnCards) {
            assertNotNull(card);
        }
    }
}

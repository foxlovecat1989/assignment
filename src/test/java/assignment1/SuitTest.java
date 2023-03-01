package assignment1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuitTest {

    @Test
    public void testGetValues() {
        // GIVEN
        Suit clubs = Suit.CLUBS;
        Suit diamonds = Suit.DIAMONDS;
        Suit hearts = Suit.HEARTS;
        Suit spades = Suit.SPADES;
        // WHEN
        // THEN
        assertEquals(Integer.valueOf(1), clubs.getValue());
        assertEquals(Integer.valueOf(2), diamonds.getValue());
        assertEquals(Integer.valueOf(3), hearts.getValue());
        assertEquals(Integer.valueOf(4), spades.getValue());
    }

    @Test
    public void testToString() {
        // GIVEN
        Suit clubs = Suit.CLUBS;
        Suit diamonds = Suit.DIAMONDS;
        Suit hearts = Suit.HEARTS;
        Suit spades = Suit.SPADES;
        // WHEN
        // THEN
        assertEquals("CLUBS", clubs.toString());
        assertEquals("DIAMONDS", diamonds.toString());
        assertEquals("HEARTS", hearts.toString());
        assertEquals("SPADES", spades.toString());
    }
}



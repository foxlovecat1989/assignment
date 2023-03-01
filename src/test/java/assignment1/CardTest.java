package assignment1;


import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

public class CardTest {
    private Card underTest;

    @Test
    public void testPojo() {
        assertPojoMethodsForAll(Card.class)
                .quickly()
                .testing(Method.CONSTRUCTOR)
                .testing(Method.EQUALS)
                .testing(Method.HASH_CODE)
                .testing(Method.GETTER)
                .areWellImplemented();
    }

    @Test
    public void testBuilder() {
        // GIVEN
        // WHEN
        underTest = Card.builder()
                .suit(Suit.CLUBS)
                .rank(Rank.TWO)
                .build();
        // THEN
        assertEquals(Rank.TWO.getValue().intValue(), underTest.getRank().getValue().intValue());
        assertEquals(Suit.CLUBS.getValue().intValue(), underTest.getSuit().getValue().intValue());
        assertEquals(Integer.valueOf(Rank.TWO.getValue() + "" + Suit.CLUBS.getValue()), underTest.getValue());
    }

    @Test
    public void testGetValue() {
        // GIVEN
        // WHEN
        underTest = new Card(Rank.TWO, Suit.CLUBS);
        // THEN
        assertEquals(Rank.TWO.getValue().intValue(), underTest.getRank().getValue().intValue());
        assertEquals(Suit.CLUBS.getValue().intValue(), underTest.getSuit().getValue().intValue());
        assertEquals(Integer.valueOf(Rank.TWO.getValue() + "" + Suit.CLUBS.getValue()), underTest.getValue());
    }

    @Test
    public void testToString() {
        // GIVEN
        underTest = new Card(Rank.TWO, Suit.CLUBS);
        // WHEN
        // THEN
        assertEquals("TWO of CLUBS", underTest.toString());
    }
}



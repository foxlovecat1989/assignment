package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

public class DealerTest {
    private Dealer underTest;

    @BeforeEach()
    public void setUp() {
        underTest = new Dealer();
    }

    @Test
    public void testPojo() {
        assertPojoMethodsForAll(Dealer.class)
                .quickly()
                .testing(Method.GETTER)
                .areWellImplemented();
    }

    @Test
    public void testDrawCard() {
        // GIVEN
        Card card1 = Card.builder()
                .rank(Rank.TWO)
                .suit(Suit.CLUBS)
                .build();
        underTest.receiveCard(List.of(card1));
        // WHEN
        Card drawnCard = underTest.drawCard();
        // THEN
        assertEquals(card1.getValue(), drawnCard.getValue());
    }

    @Test
    public void testDrawCardWhenEmpty() {
        // WHEN
        // THEN
        Assertions.assertThrows(IllegalStateException.class, underTest::drawCard, "Dealer 手上已無牌");
    }
}


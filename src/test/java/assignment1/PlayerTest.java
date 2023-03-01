package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player underTest;
    private UUID playerId;
    private String name;

    @BeforeEach
    public void setUp() {
        // GIVEN
        playerId = UUID.randomUUID();
        name = "Player 1";
        underTest = new Player(name, playerId);
    }

    @Test
    public void testConstructorAndGetter() {
        // THEN
        assertEquals(name, underTest.getName());
        assertEquals(playerId, underTest.getPlayerId());
        assertEquals(0, underTest.getHand().size());
        assertEquals(0, underTest.getPoints());
        Assertions.assertNotNull(underTest.getPlayerId());
    }

    @Test
    public void testReceiveCard() {
        // GIVEN
        Card card1 = Card.builder()
                .rank(Rank.TWO)
                .suit(Suit.CLUBS)
                .build();
        Card card2 = Card.builder()
                .rank(Rank.TEN)
                .suit(Suit.CLUBS)
                .build();
        List<Card> cards = List.of(card1, card2);
        // WHEN
        underTest.receiveCard(card1);
        underTest.receiveCard(card2);
        // THEN
        assertEquals(cards.size(), underTest.getHand().size());
        assertTrue(underTest.getHand().contains(card1));
        assertTrue(underTest.getHand().contains(card2));
    }

    @Test
    public void testPlayCard() {
        // GIVEN
        Card card1 = Card.builder()
                .rank(Rank.TWO)
                .suit(Suit.CLUBS)
                .build();
        Card card2 = Card.builder()
                .rank(Rank.TEN)
                .suit(Suit.CLUBS)
                .build();
        underTest.receiveCard(card1);
        underTest.receiveCard(card2);
        // WHEN
        Card playedCard = underTest.playCard();
        // THEN
        assertEquals(1, underTest.getHand().size());
        assertEquals(card1, playedCard);
    }

    @Test
    public void testPlayCardNoCardsInHandThrowsIllegalStateException() {
        // WHEN
        // THEN
        assertThrows(IllegalStateException.class, underTest::playCard, "手上已無牌");
    }

    @Test
    public void testAddPoint() {
        // WHEN
        underTest.addPoint();
        // THEN
        assertEquals(1, underTest.getPoints());
    }

    @Test
    void testEqualsSamePlayerReturnsTrue() {
        // WHEN
        // THEN
        assertEquals(underTest, underTest);
        assertEquals(underTest.getPlayerId(), underTest.getPlayerId());
    }

    @Test
    void equalsDifferentPlayerReturnsFalse() {
        // GIVEN
        UUID player1Id = UUID.randomUUID();
        UUID player2Id = UUID.randomUUID();
        Player underTest = new Player("Player 1", player1Id);
        Player underTestDifferentPlayer = new Player("Player 2", player2Id);
        // WHEN
        // THEN
        assertNotEquals(underTest, underTestDifferentPlayer);
    }

    @Test
    void testHashCode() {
        // GIVEN
        UUID player1Id = UUID.randomUUID();
        UUID player2Id = UUID.randomUUID();
        Player underTest = new Player("Player 1", player1Id);
        Player underTestDifferentPlayer = new Player("Player 2", player2Id);
        // WHEN
        // THEN
        assertNotEquals(underTest.hashCode(), underTestDifferentPlayer.hashCode());
    }
}



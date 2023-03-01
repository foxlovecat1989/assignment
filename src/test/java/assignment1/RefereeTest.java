package assignment1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RefereeTest {
    private Referee underTest;
    @Mock
    private Player mockPlayer1;
    @Mock
    private Player mockPlayer2;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest = new Referee();
    }

    @Test
    public void testGetWinner() {
        // GIVEN
        Player player1 = new Player("Player 1", UUID.randomUUID());
        Player player2 = new Player("Player 1", UUID.randomUUID());

        Card card1 = Card.builder()
                .rank(Rank.TWO)
                .suit(Suit.CLUBS)
                .build();
        Card card2 = Card.builder()
                .rank(Rank.TEN)
                .suit(Suit.CLUBS)
                .build();

        RoundRecord.Record recordFromPlayer1 = RoundRecord.Record.builder()
                .player(player1)
                .card(card1)
                .build();
        RoundRecord.Record recordFromPlayer2 = RoundRecord.Record.builder()
                .player(player2)
                .card(card2)
                .build();
        List<RoundRecord.Record> records = List.of(recordFromPlayer1, recordFromPlayer2);

        // WHEN
        Player winner = underTest.getWinner(records);

        // THEN
        assertEquals(player2, winner);
    }

    @Test
    public void testGetWinnerWithNoRecords() {
        // GIVEN
        List<RoundRecord.Record> emptyRecords = new ArrayList<>();
        // WHEN
        // THEN
        assertThrows(IllegalStateException.class, () -> underTest.getWinner(emptyRecords), "無法找到最大值");
    }

    @Test
    void testSummaryPoints() {
        // GIVEN
        List<Player> players = List.of(mockPlayer1, mockPlayer2);

        Card card1 = Card.builder()
                .rank(Rank.TWO)
                .suit(Suit.CLUBS)
                .build();
        Card card2 = Card.builder()
                .rank(Rank.TEN)
                .suit(Suit.CLUBS)
                .build();

        RoundRecord.Record record1FromPlayer1 = RoundRecord.Record.builder()
                .player(mockPlayer1)
                .card(card1)
                .build();
        RoundRecord.Record record1FromPlayer2 = RoundRecord.Record.builder()
                .player(mockPlayer2)
                .card(card2)
                .build();

        RoundRecord recordOfRound1 = RoundRecord.builder()
                .round(1)
                .winner(mockPlayer2)
                .numberOfPlayers(players.size())
                .records(List.of(record1FromPlayer1, record1FromPlayer2))
                .build();


        Card card3 = Card.builder()
                .rank(Rank.THREE)
                .suit(Suit.CLUBS)
                .build();
        Card card4 = Card.builder()
                .rank(Rank.NINE)
                .suit(Suit.CLUBS)
                .build();

        RoundRecord.Record record2FromPlayer1 = RoundRecord.Record.builder()
                .player(mockPlayer1)
                .card(card3)
                .build();
        RoundRecord.Record record2FromPlayer2 = RoundRecord.Record.builder()
                .player(mockPlayer2)
                .card(card4)
                .build();

        RoundRecord recordOfRound2 = RoundRecord.builder()
                .round(2)
                .winner(mockPlayer2)
                .numberOfPlayers(players.size())
                .records(List.of(record2FromPlayer1, record2FromPlayer2))
                .build();

        List<RoundRecord> records = List.of(recordOfRound1, recordOfRound2);

        doNothing().when(mockPlayer1).addPoint();
        doNothing().when(mockPlayer2).addPoint();

        // WHEN
        underTest.summaryPoints(records);

        // THEN
        verify(mockPlayer1, times(0)).addPoint();
        verify(mockPlayer2, times(2)).addPoint();
    }
}


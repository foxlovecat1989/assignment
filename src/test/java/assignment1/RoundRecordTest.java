package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import java.util.List;
import java.util.UUID;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

public class RoundRecordTest {
    private RoundRecord underTest;
    @Test
    public void testDto() {
        assertPojoMethodsForAll(RoundRecord.class, RoundRecord.Record.class)
                .quickly()
                .testing(Method.CONSTRUCTOR)
                .testing(Method.GETTER)
                .areWellImplemented();
    }

    @Test
    public void testBuilder() {
        // GIVEN
        int round = 1;
        Player player1 = new Player("PLayer 1", UUID.randomUUID());
        Player player2 = new Player("PLayer 2", UUID.randomUUID());
        int numberOfPlayers = List.of(player1, player2).size();

        // WHEN
        RoundRecord.Record record1 = RoundRecord.Record.builder()
                .card(Card.builder()
                        .rank(Rank.TWO)
                        .suit(Suit.CLUBS)
                        .build())
                .player(player1)
                .build();

        RoundRecord.Record record2 = RoundRecord.Record.builder()
                .card(Card.builder()
                        .rank(Rank.TEN)
                        .suit(Suit.CLUBS)
                        .build())
                .player(player2)
                .build();

        List<RoundRecord.Record> records = List.of(record1, record2);

        underTest = RoundRecord.builder()
                .winner(player2)
                .records(records)
                .round(round)
                .numberOfPlayers(numberOfPlayers)
                .build();
        // THEN
        Assertions.assertEquals(player2, underTest.getWinner());
        Assertions.assertEquals(numberOfPlayers, underTest.getNumberOfPlayers());
        Assertions.assertEquals(records, underTest.getRecords());
        Assertions.assertEquals(round, underTest.getRound());
    }
}


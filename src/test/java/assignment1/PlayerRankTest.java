package assignment1;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

public class PlayerRankTest {
    private PlayerRank underTest;

    @Test
    public void testDto() {
        assertPojoMethodsForAll(Card.class)
                .quickly()
                .testing(Method.CONSTRUCTOR)
                .testing(Method.GETTER)
                .areWellImplemented();
    }
    @Test
    public void testBuilder() {
        int points = 2;
        List<Player> players = List.of(new Player("PLayer 1", UUID.randomUUID()));
        underTest = PlayerRank.builder()
                .points(points)
                .players(players)
                .build();
        // THEN
        assertEquals(points, underTest.getPoints());
        assertEquals(players, underTest.getPlayers());
    }
}


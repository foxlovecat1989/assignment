package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    void testGetValue() {
        // GIVEN
        // WHEN
        // THEN
        Assertions.assertEquals(14, Rank.ACE.getValue());
        Assertions.assertEquals(13, Rank.KING.getValue());
        Assertions.assertEquals(12, Rank.QUEEN.getValue());
        Assertions.assertEquals(11, Rank.JACK.getValue());
        Assertions.assertEquals(10, Rank.TEN.getValue());
        Assertions.assertEquals(9, Rank.NINE.getValue());
        Assertions.assertEquals(8, Rank.EIGHT.getValue());
        Assertions.assertEquals(7, Rank.SEVEN.getValue());
        Assertions.assertEquals(6, Rank.SIX.getValue());
        Assertions.assertEquals(5, Rank.FIVE.getValue());
        Assertions.assertEquals(4, Rank.FOUR.getValue());
        Assertions.assertEquals(3, Rank.THREE.getValue());
        Assertions.assertEquals(2, Rank.TWO.getValue());
    }
}


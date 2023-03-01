package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.UUID;

class ReporterTest {
    private Reporter underTest;
    @Test
    void testPrintResult() {
        // GIVEN
        Player player1 = new Player("Player 1", UUID.randomUUID());
        Player player2 = new Player("Player 2", UUID.randomUUID());
        Player player3 = new Player("Player 3", UUID.randomUUID());

        List<Player> playersOfPlayerRank1 = List.of(player1, player2);
        List<Player> playersOfPlayerRank2 = List.of(player3);

        PlayerRank playerRankOfPlayer1 = PlayerRank.builder()
                .players(playersOfPlayerRank1)
                .points(1)
                .build();
        PlayerRank playerRankOfPlayer2 = PlayerRank.builder()
                .players(playersOfPlayerRank2)
                .points(2)
                .build();

        underTest = new Reporter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<PlayerRank> playerRanks = List.of(playerRankOfPlayer1, playerRankOfPlayer2);

        // WHEN
        underTest.printResult(playerRanks);
        // THEN
        Assertions.assertTrue(outputStream.toString().contains("Result ===================================="));
        Assertions.assertTrue(outputStream.toString().contains("第1名(Points: 2) - Player 3"));
        Assertions.assertTrue(outputStream.toString().contains("第2名(Points: 1) - Player 1 , Player 2"));
    }

}


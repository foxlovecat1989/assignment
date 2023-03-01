package assignment1;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Reporter {
    void printResult(List<PlayerRank> playerRanks) {
        AtomicInteger rank = new AtomicInteger(1);
        System.out.println("Result ====================================");
        playerRanks.stream()
                .sorted(Comparator.comparing(PlayerRank::getPoints).reversed())
                .forEach(playerRank -> {
                    String names = playerRank.getPlayers().stream()
                            .map(Player::getName)
                            .collect(Collectors.joining(" , "));
                    String msg = String.format(
                            "第%s名(Points: %s) - %s",
                            rank.getAndIncrement(),
                            playerRank.getPoints(),
                            names);
                    System.out.println(msg);
                });
    }
}

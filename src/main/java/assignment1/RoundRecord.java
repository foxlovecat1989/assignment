package assignment1;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class RoundRecord {
    private Player winner;
    private List<Record> records;
    private Integer round;
    private Integer numberOfPlayers;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Record {
        private Card card;
        private Player player;
    }
}

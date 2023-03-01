package assignment1;

import java.util.Comparator;
import java.util.List;


public class Referee {

    public void summaryPoints(List<RoundRecord> records) {
        records.stream()
                .map(RoundRecord::getWinner)
                        .forEach(Player::addPoint);
    }

    Player getWinner(List<RoundRecord.Record> records) {
        return records.stream()
                .max(Comparator.comparing(record -> record.getCard().getValue()))
                .orElseThrow(() -> new IllegalStateException("無法找到最大值"))
                .getPlayer();
    }
}

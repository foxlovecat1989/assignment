package assignment1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Getter
class Player {
    private final UUID playerId;
    private final String name;
    private final List<Card> hand;
    private Integer points;

    public Player(String name, UUID playerId) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.points = 0;
        this.playerId = playerId;
    }

    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    public Card playCard() {
        if (this.hand.isEmpty())
            throw new IllegalStateException("手上已無牌");

        return this.hand.remove(0);
    }

    public void addPoint() {
        ++this.points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;

        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}



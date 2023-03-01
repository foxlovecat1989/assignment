package assignment1;

import lombok.*;

import java.util.Objects;


@AllArgsConstructor
@Builder
@Getter
class Card {
    private Rank rank;
    private Suit suit;

    public int getValue() {
        return Integer.parseInt(this.rank.getValue() + "" + this.suit.getValue());
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;

        return rank.getValue().intValue() == card.rank.getValue().intValue()
                && suit.getValue().intValue() == card.suit.getValue().intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank.getValue(), suit.getValue());
    }
}






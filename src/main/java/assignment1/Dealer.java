package assignment1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public void receiveCard(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty())
            throw new IllegalStateException("Dealer 手上已無牌");

        return cards.remove(0);
    }
}


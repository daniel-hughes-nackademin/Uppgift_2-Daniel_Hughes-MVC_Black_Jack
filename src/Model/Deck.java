package Model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> cards = new ArrayList<>();

    public Deck() {

        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }


    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return this.cards.toString();
    }
}

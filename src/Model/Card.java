package Model;

public class Card {

    private Suit suit;
    private Value value;
    private int points;


    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;

        switch (value){
            case ACE:
            case JACK:
            case QUEEN:
            case KING:
                this.points = 10;
                break;
            default:
                this.points = Integer.parseInt(String.valueOf(value));
                break;
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.valueOf(this.suit) + this.value;
    }
}

package Model;

public enum Suit{
    SPADES('\u2660'),
    HEARTS('\u2665'),
    DIAMONDS('\u2666'),
    CLUBS('\u2663');

    private char symbol;

    Suit(char symbol){
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}

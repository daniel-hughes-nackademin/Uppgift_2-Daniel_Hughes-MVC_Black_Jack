package Model;

import java.util.List;

public class Game {

    private List<Card> cardPile;
    private Player player = new Player();
    private Player dealer = new Player();
    private int numberOfRounds;
    private int blackJackScore;

    public List<Card> getCardPile() {
        return cardPile;
    }

    public void setCardPile(List<Card> cardPile) {
        this.cardPile = cardPile;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public int getBlackJackScore() {
        return blackJackScore;
    }

    public void setBlackJackScore(int blackJackScore) {
        this.blackJackScore = blackJackScore;
    }
}

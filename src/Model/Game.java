package Model;

import java.util.List;

public class Game {

    private List<Card> cardPile;
    private Player player = new Player();
    private Player dealer = new Player();
    private int numberOfRounds;




    public List<Card> getCardPile() {
        return cardPile;
    }

    public void setCardPile(List<Card> cardPile) {
        this.cardPile = cardPile;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}

package Model;

import Interfaces.Observer;
import Interfaces.Subject;
import Utility.GameUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Subject {
    private List<Observer> observers = new ArrayList<>();

    private List<Card> hand = new ArrayList<>();
    private int points = 0;
    private int roundsWon = 0;
    private boolean isDealer = false;


    public void drawCard(List<Card> cardPile){
        if(cardPile.isEmpty())
            throw new IllegalArgumentException("Card Pile is empty! Can't Draw Cards!");

        Random random = new Random();
        int randomIndex = random.nextInt(cardPile.size());
        Card drawnCard = cardPile.get(randomIndex);
        this.hand.add(drawnCard);
        cardPile.remove(drawnCard);

        if(!isDealer){
            //update hand to player
        }


    }

    public void changeAceValue(){
        for (Card card : this.hand) {
            if(card.getValue().equals(Value.ACE) && card.getPoints() == 10){
                card.setPoints(1);
                break;
            }
        }
        System.out.println("There were no 10 point aces");
    }

    public void emptyHand(){
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setPoints(int points) {
        this.points = points;
        if(!isDealer)
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.PLAYER_SCORE, this.getPoints()));
    }

    public int getPoints() {
        return points;
    }

    public void winRound() {
        this.roundsWon++;
        if(isDealer)
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.DEALER_ROUNDS, this.roundsWon));
        else
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.PLAYER_ROUNDS, this.roundsWon));
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    @Override
    public void setObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyUpdate(GameUpdate gameUpdate) {
        observers.forEach(observer -> observer.update(gameUpdate));
    }
}

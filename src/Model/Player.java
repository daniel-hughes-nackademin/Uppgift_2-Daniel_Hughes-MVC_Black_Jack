package Model;

import Interfaces.Observer;
import Interfaces.Subject;
import Utility.PlayerUpdate;

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
            notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.PLAYER_CARD, drawnCard.toString()));
        }

        setPoints(this.points + drawnCard.getPoints());
    }

    public void changeAceValue(){
        for (Card card : this.hand) {
            if(card.getValue().equals(Value.ACE) && card.getPoints() == 10){
                card.setPoints(1);
                setPoints(this.points - 9);
                break;
            }
        }
    }

    public void emptyHand(){
        this.hand = new ArrayList<>();
        this.points = 0;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setPoints(int points) {
        this.points = points;
        if(!isDealer)
            notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.PLAYER_SCORE, this.getPoints()));
    }

    public int getPoints() {
        return points;
    }

    public void revealDealerHand(){
        if(this.isDealer){
            notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.DEALER_SCORE, this.points));
            StringBuilder handString = new StringBuilder();
            this.getHand().forEach(card -> handString.append(card.toString() + " "));
            notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.DEALER_HAND, handString.toString()));
        }
        else
            throw new IllegalCallerException("Only dealer can reveal hand!");
    }

    public void winRound() {
        this.roundsWon++;
        if(isDealer)
            notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.DEALER_ROUNDS, this.roundsWon));
        else
            notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.PLAYER_ROUNDS, this.roundsWon));
    }

    public int getRoundsWon() {
        return roundsWon;
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
    public void notifyUpdate(Object playerUpdate) {
        observers.forEach(observer -> observer.update(playerUpdate));
    }
}

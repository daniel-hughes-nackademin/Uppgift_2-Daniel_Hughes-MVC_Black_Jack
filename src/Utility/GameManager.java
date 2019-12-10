package Utility;

import Interfaces.Observer;
import Interfaces.Subject;
import Model.Deck;
import Model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements Subject { //Facade class

    private List<Observer> observers = new ArrayList<>();
    private Game game;

    public void startGame(GameListener gameListener){
        setObserver(gameListener);

        game = new Game();
        game.getPlayer().setObserver(gameListener);
        game.getDealer().setObserver(gameListener);
        game.getDealer().setDealer(true);

        game.setNumberOfRounds(SettingsManager.readNumberOfRoundsFromProperties());
        game.setBlackJackScore(SettingsManager.readBlackJackScoreFromProperties());

        int numberOfDecks = SettingsManager.readNumberOfDecksFromProperties();
        game.setCardPile(new ArrayList<>());
        for (int i = 0; i < numberOfDecks; i++) {
            game.getCardPile().addAll(new Deck().getCards());
        }
        System.out.println(game.getCardPile().toString());
        System.out.println(game.getCardPile().size());
    }

    public void playDealerRound(){
        int safetyMargin = 4;
        int minimumAcceptableScore = game.getBlackJackScore() - safetyMargin;
        while (game.getDealer().getPoints() < minimumAcceptableScore){ //while points < 17 as standard
            game.getDealer().drawCard(game.getCardPile());

            if(game.getDealer().getPoints() > game.getBlackJackScore()) //If dealer is going bust
                    game.getDealer().changeAceValue(); //Change an ace value from 10 to 1 if there are any aces

        }

        game.getDealer().notifyUpdate(new PlayerUpdate(PlayerUpdate.UpdateType.DEALER_NR_CARDS, game.getDealer().getHand().size())); //Notify listener number of cards drawn
    }

    public void drawPlayerCard() {
        game.getPlayer().drawCard(game.getCardPile());
        if(game.getPlayer().getPoints() > game.getBlackJackScore()) //If player is going bust
            game.getPlayer().changeAceValue(); //Change an ace value from 10 to 1 if there are any aces

        if(game.getPlayer().getPoints() > game.getBlackJackScore()){
            endPlayerRound();
        }
    }

    public void endPlayerRound() {
        game.getDealer().revealDealerHand();

        if(game.getPlayer().getPoints() > game.getBlackJackScore()){ //Player went bust
            game.getDealer().winRound();
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.ROUND_END, "Player Went Bust! Dealer Wins Round!", "Round Win", "Went Bust"));
        }
        else if(game.getDealer().getPoints() > game.getBlackJackScore()){ //Dealer went bust
            game.getPlayer().winRound();
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.ROUND_END, "Dealer Went Bust! Player Wins Round!", "Went Bust", "Round Win"));
        }
        else if(game.getPlayer().getPoints() > game.getDealer().getPoints()){ //Player wins by points
            game.getPlayer().winRound();
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.ROUND_END, "Player Wins Round!", "Round Loss", "Round Win"));
        }
        else if (game.getPlayer().getPoints() == game.getDealer().getPoints()){ //Round draw
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.ROUND_END, "Round Draw!", "Normal", "Normal"));
        }
        else{                                                                   //Dealer wins by points
            game.getDealer().winRound();
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.ROUND_END, "Dealer Wins Round!", "Round Win", "Round Loss"));
        }

        game.getPlayer().emptyHand();
        game.getDealer().emptyHand();

        checkGameOver();

    }

    private void checkGameOver() {
        if(game.getPlayer().getRoundsWon() == game.getNumberOfRounds())
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.GAME_END, "You Won the Game! Congratulations!", "Game Loss", "Game Win"));
        else if (game.getDealer().getRoundsWon() == game.getNumberOfRounds())
            notifyUpdate(new GameUpdate(GameUpdate.UpdateType.GAME_END, "You Lost! Better Get Revenge!", "Game Win", "Game Loss"));
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
    public void notifyUpdate(Object object) {
        observers.forEach(observer -> observer.update(object));
    }
}

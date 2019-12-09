package Utility;

import Model.Deck;
import Model.Game;

import java.util.ArrayList;

public class GameManager { //Facade class

    private Game game;

    public void startGame(GameListener gameListener){
        game = new Game();
        game.getPlayer().setObserver(gameListener);
        game.getDealer().setObserver(gameListener);

        game.setNumberOfRounds(SettingsManager.readNumberOfRoundsFromProperties());

        int numberOfDecks = SettingsManager.readNumberOfDecksFromProperties();
        game.setCardPile(new ArrayList<>());
        for (int i = 0; i < numberOfDecks; i++) {
            game.getCardPile().addAll(new Deck().getCards());
        }
        System.out.println(game.getCardPile().toString());
        System.out.println(game.getCardPile().size());
        playGame();
    }

    private void playGame(){

    }

}

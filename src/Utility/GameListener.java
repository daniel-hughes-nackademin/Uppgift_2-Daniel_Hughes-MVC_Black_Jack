package Utility;

import Controller.GameController;
import Interfaces.Observer;

public class GameListener implements Observer {

    GameController gameController;

    public GameListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void update(GameUpdate gameUpdate) {
        final String valueString = String.valueOf(gameUpdate.getValue());
        switch (gameUpdate.getUpdateType()){
            case PLAYER_SCORE:
                gameController.playerPointsLabel.setText(valueString);
                break;
            case DEALER_SCORE:
                gameController.dealerPointsLabel.setText(valueString);
                break;
            case PLAYER_ROUNDS:
                gameController.playerRoundsLabel.setText(valueString);
                break;
            case DEALER_ROUNDS:
                gameController.dealerRoundsLabel.setText(valueString);
                break;
            case PLAYER_HAND:
                break;
            case DEALER_HAND:
                break;
            case DEALER_NR_CARDS:
                gameController.dealerTextArea.setText("Dealer drew " + valueString + " cards!");
                gameController.playerTextArea.setText("Your Turn!");
                break;
        }
    }
}

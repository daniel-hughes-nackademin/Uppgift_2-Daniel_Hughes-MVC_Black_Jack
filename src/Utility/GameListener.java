package Utility;

import Controller.GameController;
import Interfaces.Observer;
import javafx.application.Platform;

public class GameListener implements Observer {

    private GameController gameController;


    public GameListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void update(Object object) {

        if (object instanceof PlayerUpdate) {
            PlayerUpdate playerUpdate = (PlayerUpdate) object;
            final String valueString = String.valueOf(playerUpdate.getValue());
            switch (playerUpdate.getUpdateType()) {
                case PLAYER_SCORE:
                    gameController.playerPointsLabel.setText(valueString);
                    break;
                case DEALER_SCORE:
                    Platform.runLater(() -> gameController.dealerPointsLabel.setText(valueString));
                    break;
                case PLAYER_ROUNDS:
                    gameController.playerRoundsLabel.setText(valueString);
                    break;
                case DEALER_ROUNDS:
                    gameController.dealerRoundsLabel.setText(valueString);
                    break;
                case PLAYER_CARD:
                    gameController.playerTextArea.appendText(playerUpdate.getMessage() + " ");
                    break;
                case DEALER_HAND:
                    gameController.dealerTextArea.setText("Dealer's Hand:\n" + playerUpdate.getMessage());
                    break;
                case DEALER_NR_CARDS:
                    gameController.dealerTextArea.setText("Dealer drew " + valueString + " cards!");
                    gameController.playerTextArea.setText("Your Turn!\n");
                    gameController.takeCardButton.setVisible(true);
                    break;
            }
        } else if (object instanceof GameUpdate) {
            GameUpdate gameUpdate = (GameUpdate) object;

            switch (gameUpdate.getUpdateType()) {
                case ROUND_END:
                    gameController.eventLabel.setText(gameUpdate.getMessage());
                    performEndOfRoundEvents();
                    gameController.changeDealerImage(gameUpdate.getDealerEvent());
                    gameController.changePlayerImage(gameUpdate.getPlayerEvent());
                    break;
                case GAME_END:
                    setGameLabelsVisible();
                    if (gameUpdate.getPlayerEvent().equals("Game Win")) {

                        int playerGamesWon = Integer.parseInt(gameController.playerGamesWonLabel.getText()) + 1;
                        Platform.runLater(() -> {
                            gameController.playerGamesWonLabel.setText(String.valueOf(playerGamesWon));
                        });
                    } else {
                        int dealerGamesWon = Integer.parseInt(gameController.dealerGamesWonLabel.getText()) + 1;
                        Platform.runLater(() -> gameController.dealerGamesWonLabel.setText(String.valueOf(dealerGamesWon)));
                    }

                    gameController.eventLabel.setText(gameUpdate.getMessage());
                    gameController.holdButton.setVisible(false);
                    gameController.takeCardButton.setVisible(false);
                    gameController.changeDealerImage(gameUpdate.getDealerEvent());
                    gameController.changePlayerImage(gameUpdate.getPlayerEvent());
                    break;
            }
        }
    }

    private void setGameLabelsVisible() {
        gameController.gamesWonLabel1.setVisible(true);
        gameController.gamesWonLabel2.setVisible(true);
        gameController.gamesWonLabel3.setVisible(true);
        gameController.dealerGamesWonLabel.setVisible(true);
        gameController.playerGamesWonLabel.setVisible(true);
    }

    public void performEndOfRoundEvents() {
        gameController.holdButton.setVisible(false);
        gameController.takeCardButton.setText("Next Round");
        gameController.dealerPointsLabel.setText("?");
    }
}

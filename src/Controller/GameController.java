package Controller;

import Utility.GameListener;
import Utility.GameManager;
import Utility.SettingsManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GameController {


    public TextArea playerTextArea;
    public TextArea dealerTextArea;
    public Label playerPointsLabel;
    public Button takeCardButton;
    public Button holdButton;
    public Slider decksSlider;
    public Slider roundsSlider;
    public Label playerRoundsLabel;
    public Label dealerRoundsLabel;
    public Label dealerPointsLabel;
    public Label eventLabel;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public ImageView dealerImage;
    public ImageView playerImage;
    public Label gamesWonLabel1;
    public Label gamesWonLabel2;
    public Label gamesWonLabel3;
    public Label playerGamesWonLabel;
    public Label dealerGamesWonLabel;

    private GameManager gameManager = new GameManager(); //Facade class to manage the game and separate game logic to its own class

    private boolean firstGame = true;

    public void initialize(){
        decksSlider.setValue(SettingsManager.readNumberOfDecksFromProperties());
        roundsSlider.setValue(SettingsManager.readNumberOfRoundsFromProperties());
    }

    public void takeCard(ActionEvent actionEvent) {
        if(takeCardButton.getText().equals("Start Round") || takeCardButton.getText().equals("Next Round")){
            takeCardButton.setText("Take Card");
            eventLabel.setText("Rounds Won:");
            holdButton.setVisible(true);
            gameManager.playDealerRound();
            playerPointsLabel.setText("0");
            dealerPointsLabel.setText("?");
            changeDealerImage("Normal");
            changePlayerImage("Normal");
        }
        else if(takeCardButton.getText().equals("Take Card")){
            if(playerTextArea.getText().equals("Your Turn!")){
                playerTextArea.setText("Your Hand:\n");
            }
            gameManager.drawPlayerCard();
        }
    }

    public void startGame(ActionEvent actionEvent) {
        playerRoundsLabel.setText("0");
        dealerRoundsLabel.setText("0");
        playerPointsLabel.setText("0");
        dealerPointsLabel.setText("?");
        eventLabel.setText("Rounds Won:");
        playerTextArea.setText("");
        dealerTextArea.setText("");
        holdButton.setVisible(false);
        takeCardButton.setText("Start Round");
        takeCardButton.setVisible(true);
        changeDealerImage("Normal");
        changePlayerImage("Normal");

        if(firstGame){
            firstGame = false;
            label1.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            playerPointsLabel.setVisible(true);
            playerRoundsLabel.setVisible(true);
            dealerPointsLabel.setVisible(true);
            dealerRoundsLabel.setVisible(true);
        }

        gameManager.startGame(new GameListener(this));
    }

    public void changeDealerImage(String dealerEvent) {
        String filePath = "src/Resources/Male Avatar Expressions/" + dealerEvent + ".png";
        try {
            dealerImage.setImage(new Image(new FileInputStream(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePlayerImage(String playerEvent) {
        String filePath = "src/Resources/Female Avatar Expressions/" + playerEvent + ".jpg";
        try {
            playerImage.setImage(new Image(new FileInputStream(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hold(ActionEvent actionEvent) {
        gameManager.endPlayerRound();
    }

    public void setNumberOfDecks(MouseEvent dragEvent) {
        SettingsManager.writeNumberOfDecksToProperties((int) decksSlider.getValue());
    }

    public void setNumberOfRounds(MouseEvent dragEvent) {
        SettingsManager.writeNumberOfRoundsToProperties((int) roundsSlider.getValue());
    }
}

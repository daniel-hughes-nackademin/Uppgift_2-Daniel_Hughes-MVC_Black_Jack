package Controller;

import Utility.GameListener;
import Utility.GameManager;
import Utility.SettingsManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

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

    private GameManager gameManager = new GameManager(); //Facade class to manage the game and separate game logic to its own class


    public void initialize(){
        decksSlider.setValue(SettingsManager.readNumberOfDecksFromProperties());
        roundsSlider.setValue(SettingsManager.readNumberOfRoundsFromProperties());
    }

    public void takeCard(ActionEvent actionEvent) {
    }

    public void startGame(ActionEvent actionEvent) {
        playerRoundsLabel.setText("0");
        dealerRoundsLabel.setText("0");
        playerPointsLabel.setText("0");
        dealerPointsLabel.setText("0");

        gameManager.startGame(new GameListener(this));
    }

    public void hold(ActionEvent actionEvent) {
    }

    public void setNumberOfDecks(MouseEvent dragEvent) {
        SettingsManager.writeNumberOfDecksToProperties((int) decksSlider.getValue());
    }

    public void setNumberOfRounds(MouseEvent dragEvent) {
        SettingsManager.writeNumberOfRoundsToProperties((int) roundsSlider.getValue());
    }
}

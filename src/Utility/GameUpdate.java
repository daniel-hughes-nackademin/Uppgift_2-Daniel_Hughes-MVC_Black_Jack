package Utility;

public class GameUpdate {

    public enum UpdateType{
        PLAYER_SCORE,
        DEALER_SCORE,
        PLAYER_ROUNDS,
        DEALER_ROUNDS,
        PLAYER_HAND,
        DEALER_HAND,
        DEALER_NR_CARDS
    }


    private UpdateType updateType;
    private String message;
    private int value;

    public GameUpdate(UpdateType updateType, String message) {
        this.updateType = updateType;
        this.message = message;
    }

    public GameUpdate(UpdateType updateType, int value) {
        this.updateType = updateType;
        this.value = value;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}

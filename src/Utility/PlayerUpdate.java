package Utility;

public class PlayerUpdate {

    public enum UpdateType{
        PLAYER_SCORE,
        DEALER_SCORE,
        PLAYER_ROUNDS,
        DEALER_ROUNDS,
        PLAYER_CARD,
        DEALER_HAND,
        DEALER_NR_CARDS
    }


    private UpdateType updateType;
    private String message;
    private int value;

    public PlayerUpdate(UpdateType updateType, String message) {
        this.updateType = updateType;
        this.message = message;
    }

    public PlayerUpdate(UpdateType updateType, int value) {
        this.updateType = updateType;
        this.value = value;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }



}

package Utility;

public class GameUpdate {

    public enum UpdateType{
        ROUND_END,
        GAME_END
    }

    private UpdateType updateType;
    private String message;
    private String dealerEvent;
    private String playerEvent;

    public GameUpdate(UpdateType updateType, String message, String dealerEvent, String playerEvent) {
        this.updateType = updateType;
        this.message = message;
        this.dealerEvent = dealerEvent;
        this.playerEvent = playerEvent;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public String getMessage() {
        return message;
    }

    public String getDealerEvent() {
        return dealerEvent;
    }

    public String getPlayerEvent() {
        return playerEvent;
    }
}

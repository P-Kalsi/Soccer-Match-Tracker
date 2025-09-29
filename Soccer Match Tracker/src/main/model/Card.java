// Makes a card given to a player with timestamp and card type (yellow,red)

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Card implements Writable {
    private Player player;
    private int minute;
    private String cardType;

    // REQUIRES: minutes is between 0 and 120 (Overtime and extra time)
    // and card is either yellow or red
    public Card(Player player, int minute, String cardType) {
        this.player = player;
        this.minute = minute;
        this.cardType = cardType;
    }

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(Player player) {

        this.player = player;
    }

    public int getMinute() {

        return minute;
    }

    // REQUIRES: minutes is between 0 and 120 (Overtime and extra time)
    public void setMinute(int minute) {

        this.minute = minute;
    }

    public String getCardType() {

        return cardType;
    }

    // REQUIRES: card is either yellow or red
    // MODIFIES: this
    // EFFECTS: can turn cardType from yellow to red (Depending on the severity of foul
    // it can be changed later
    public void setCardType(String cardType) {

        this.cardType = cardType;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Player", player.toJson());
        json.put("Minute", minute);
        json.put("cardType", cardType);
        return json;
    }
}
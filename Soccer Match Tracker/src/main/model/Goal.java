// Makes a goal scored by a player with timestamp

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Goal implements Writable {
    private Player player;
    private int minute;

    // REQUIRES: minutes is between 0 and 120 (Overtime and extra time)
    public Goal(Player player, int minute) {
        this.player = player;
        this.minute = minute;
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
    // MODIFIES: this

    public void setMinute(int minute) {

        this.minute = minute;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Player", player.toJson());
        json.put("Minute", minute);
        return json;
    }
}

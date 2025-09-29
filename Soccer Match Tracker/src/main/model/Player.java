// Player class for assigning player to team

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Player implements Writable {
    private String name;
    private Team team;


    public Player(String name, Team team) {
        this.name = name;
        this.team = team;


    }


    public String getName() {

        return name;
    }

    // REQUIRES: name is letters not numerical
    // MODIFIES: this
    // EFFECTS: sets the name given name
    public void setName(String name) {

        this.name = name;
    }

    public Team getTeam() {

        return team;
    }

    public void setTeam(Team team) {

        this.team = team;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Team", team.toJson());
        return json;
    }
}
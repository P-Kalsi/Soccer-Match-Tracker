// Creates a team

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Team implements Writable {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        return json;
    }
}
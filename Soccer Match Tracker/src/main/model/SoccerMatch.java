// SoccerMatch makes a SoccerMatch with home and away teams

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

public class SoccerMatch implements Writable {
    private String homeTeam;
    private String awayTeam;
    private String date;
    private MatchDetails matchDetails;

    // REQUIRES: Date in yyyy-mm-dd format
    public SoccerMatch(String homeTeam, String awayTeam, String date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.matchDetails = new MatchDetails();
    }

    public String getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public MatchDetails getMatchDetails() {

        return matchDetails;
    }

    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Home Team", homeTeam);
        json.put("Away Team", awayTeam);
        json.put("Date", date);
        json.put("Match Details", matchDetails.toJson());
        return json;
    }



}
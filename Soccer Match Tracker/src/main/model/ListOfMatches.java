// List of matches to track

package model;


import model.SoccerMatch;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;



public class ListOfMatches implements Writable {

    private ArrayList<SoccerMatch> listOfSoccerMatch;



    public ListOfMatches() {
        listOfSoccerMatch = new ArrayList<SoccerMatch>();
    }

    // modifies: this
    // effects: adds match to list
    // if already in list don't add
    public void addMatch(SoccerMatch match) {
        boolean inIt = false;
        for (SoccerMatch m : listOfSoccerMatch) {
            if (m == match) {
                inIt = true;
                break;
            } else {
                inIt = false;
            }
        }
        if (!inIt) {
            listOfSoccerMatch.add(0, match);
            EventLog.getInstance().logEvent(new Event("Added your SoccerMatch: " + match.getHomeTeam() + " vs "
                    + match.getAwayTeam() + " to the Soccer Match List"));
        }
    }

    // requires: match in listOfMatches
    // modifies: this
    // effects: remove match from list
    public void removeMatch(SoccerMatch match) {
        listOfSoccerMatch.remove(match);
        EventLog.getInstance().logEvent(new Event("Removed your SoccerMatch: " + match.getHomeTeam() + " vs "
                + match.getAwayTeam() + " from the Soccer Match List"));
    }

    // requires listOfMatches has something in it
    // effects: returns number of matches
    public int getNumMatches() {
        return listOfSoccerMatch.size();
    }

    // requires: list is not empty, and index must be valid
    // effects: returns match at given index
    public SoccerMatch getMatchAtIndex(int i) {
        return listOfSoccerMatch.get(i);
    }


    // EFFECTS: returns the list of matches
    public ArrayList<SoccerMatch> getMatches() {
        return listOfSoccerMatch;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Soccer Matches", soccerMatchesToJson());
        return json;
    }

    // EFFECTS: returns soccer matches in this match list as a JSON array
    private JSONArray soccerMatchesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SoccerMatch sm : listOfSoccerMatch) {
            jsonArray.put(sm.toJson());
        }

        return jsonArray;
    }


}
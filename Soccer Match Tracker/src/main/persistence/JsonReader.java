// Credits to JsonDemo

package persistence;

import model.ListOfMatches;
import model.MatchDetails;
import model.SoccerMatch;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    // EFFECTS: creates reader that reads data from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfMatches from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfMatches readListOfMatches() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfMatches(jsonObject);
    }

    // EFFECTS: parses ListOfMatches from JSON object and returns it
    private ListOfMatches parseListOfMatches(JSONObject jsonObject) {
        ListOfMatches loms = new ListOfMatches();
        addSoccerMatches(loms, jsonObject);
        return loms;
    }

    // MODIFIES: loms
    // EFFECTS: parses Matches from JSON object and adds them to ListOfMatches
    private void addSoccerMatches(ListOfMatches loms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Soccer Matches");
        for (Object json : jsonArray) {
            JSONObject nextMatch = (JSONObject) json;
            addSoccerMatch(loms, nextMatch);
        }
    }

    // MODIFIES: loms
    // EFFECTS: parses match from JSON object and adds it to ListOfMatches
    private void addSoccerMatch(ListOfMatches loms, JSONObject jsonObject) {
        String homeTeam = jsonObject.getString("Home Team");
        String awayTeam = jsonObject.getString("Away Team");
        String date = jsonObject.getString("Date");
        MatchDetails matchDetails = parseMatchDetails(jsonObject.getJSONObject("Match Details"));
        SoccerMatch match = new SoccerMatch(homeTeam, awayTeam, date);
        match.setMatchDetails(matchDetails);
        loms.addMatch(match);
    }

    // EFFECTS: parses MatchDetails from JSON object and returns it
    private MatchDetails parseMatchDetails(JSONObject jsonObject) {
        MatchDetails matchDetails = new MatchDetails();
        JSONArray goalsArray = jsonObject.getJSONArray("Goals");
        for (Object json : goalsArray) {
            JSONObject nextGoal = (JSONObject) json;
            matchDetails.addGoal(parsePlayer(nextGoal.getJSONObject("Player")), nextGoal.getInt("Minute"));
        }
        JSONArray assistsArray = jsonObject.getJSONArray("Assists");
        for (Object json : assistsArray) {
            JSONObject nextAssist = (JSONObject) json;
            matchDetails.addAssist(parsePlayer(nextAssist.getJSONObject("Player")), nextAssist.getInt("Minute"));
        }
        JSONArray cardsArray = jsonObject.getJSONArray("Cards");
        for (Object json : cardsArray) {
            JSONObject nextCard = (JSONObject) json;
            matchDetails.addCard(parsePlayer(nextCard.getJSONObject("Player")), nextCard.getInt("Minute"),
                    nextCard.getString("cardType"));
        }
        return matchDetails;
    }

    // EFFECTS: parses Player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");

        Team team = parseTeam(jsonObject.getJSONObject("Team"));

        if (jsonObject.has("Team") && !jsonObject.isNull("Team")) {
            if (jsonObject.get("Team") instanceof JSONObject) {
                team = parseTeam(jsonObject.getJSONObject("Team"));
            } else {
                // Handle the case where "Team" is not a JSONObject
            }
        }
        return new Player(name, team);
    }

    // EFFECTS: parses Team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        return new Team(name);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

}

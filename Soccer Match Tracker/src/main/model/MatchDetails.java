// MatchDetails stores information of given data for a certain match
// through lists (goals, assists cards)

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


public class MatchDetails implements Writable {
    private List<Goal> goals;
    private List<Assist> assists;
    private List<Card> cards;


    public MatchDetails() {
        this.goals = new ArrayList<>();
        this.assists = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public List<Goal> getGoals() {

        return goals;
    }

    // REQUIRES: minutes is between 0 and 120 (Overtime and extra time)
    // MODIFIES: this
    // EFFECTS: adds new goal to goals list by given player at certain time in match
    public void addGoal(Player player, int minute) {

        goals.add(new Goal(player, minute));
        EventLog.getInstance().logEvent(new Event("Added a goal for " + player.getName()
                + " at minute " + minute));
    }

    public List<Assist> getAssists() {

        return assists;
    }

    // REQUIRES: minutes is between 0 and 120 (Overtime and extra time)
    // MODIFIES: this
    // EFFECTS: adds new assist to assists list by given player at certain time in match
    public void addAssist(Player player, int minute) {

        assists.add(new Assist(player, minute));
        EventLog.getInstance().logEvent(new Event("Added an assist for " + player.getName()
                + " at minute " + minute));
    }

    public List<Card> getCards() {

        return cards;
    }

    // REQUIRES: minutes is between 0 and 120 (Overtime and extra time)
    // MODIFIES: this
    // EFFECTS: adds new card to cards list by given player at certain time in match
    // if there's 2 yellows given out, it also adds a red to list for that player
    public void addCard(Player player, int minute, String cardType) {
        boolean hasYellow = false;
        for (Card card : cards) {
            if (card.getPlayer().equals(player) && card.getCardType().equalsIgnoreCase("yellow")) {
                hasYellow = true;
                break;

            }
        }

        cards.add(new Card(player, minute,cardType));
        EventLog.getInstance().logEvent(new Event("Added a " + cardType + " for " + player.getName()
                + " at minute " + minute));

        if (hasYellow && cardType.equalsIgnoreCase("yellow")) {
            cards.add(new Card(player, minute, "red"));

        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Goals", goalsToJson());
        json.put("Assists", assistsToJson());
        json.put("Cards", cardsToJson());
        return json;
    }

    // EFFECTS: returns goals in this goals list as a JSON array
    private JSONArray goalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal g : goals) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }

    private JSONArray assistsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Assist a : assists) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : cards) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
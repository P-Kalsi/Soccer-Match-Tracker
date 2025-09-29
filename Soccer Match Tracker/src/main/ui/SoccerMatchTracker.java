// Credits to TellerApp for general format

package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SoccerMatchTracker {
    private List<SoccerMatch> matches;
    private ListOfMatches listOfMatches = new ListOfMatches();
    private Scanner input;
    private JsonReader jsonReaderMatches;
    private JsonWriter jsonWriterMatches;
    private static final String JSON_STORE_MATCHES = "./data/listOfMatches.json";

    public SoccerMatchTracker() {
        matches = new ArrayList<>();
        input = new Scanner(System.in);
        jsonReaderMatches = new JsonReader(JSON_STORE_MATCHES);
        jsonWriterMatches = new JsonWriter(JSON_STORE_MATCHES);

    }

    // Boots up tracker
    public void runTracker() {
        boolean keepGoing = true;
        while (keepGoing) {
            displayMenu();
            String command = input.next().toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
        printEventsLogged();
    }


    private void printEventsLogged() {
        EventLog eventsLogged = EventLog.getInstance();
        for (Event e : eventsLogged) {
            System.out.println(e);
        }
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            viewMatchDetails();
        } else if (command.equals("2")) {
            addMatch();
        } else if (command.equals("3")) {
            viewMatchTracker();
        } else if (command.equals("4")) {
            removeMatch();
        } else if (command.equals("5")) {
            loadMatchList();
        } else if (command.equals("6")) {
            saveMatchList();
        } else if (command.equals("7")) {
            editMatch();
        } else {
            System.out.println("Invalid command.");
        }

    }

    // EFFECTS: loads the matches from file
    private void loadMatchList() {
        try {
            listOfMatches = jsonReaderMatches.readListOfMatches();
            System.out.println("List Of Matches has been loaded from " + JSON_STORE_MATCHES);
        } catch (IOException e) {
            System.out.println("Cannot read from file: " + JSON_STORE_MATCHES);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves current matches to file
    private void saveMatchList() {
        try {
            jsonWriterMatches.open();
            jsonWriterMatches.writeListOfMatches(listOfMatches);
            jsonWriterMatches.close();
            System.out.println("Saved List Of Matches to " + JSON_STORE_MATCHES);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_MATCHES);
        }
    }



    private void displayMenu() {
        System.out.println("\nSelect an option:");

        System.out.println("1 - View match details");
        System.out.println("2 - Add a match");
        System.out.println("3 - View tracked matches");
        System.out.println("4 - Remove a match");
        System.out.println("5 - load matches");
        System.out.println("6 - Save matches");
        System.out.println("7 - Edit matches");
        System.out.println("Q - Quit");
    }

    private void viewMatchDetails() {
        SoccerMatch match = getMatchByDate();
        displayMatchDetails(match);
    }

    private SoccerMatch getMatchByDate() {
        System.out.print("Enter match date (yyyy-mm-dd): ");
        String date = input.next();
        SoccerMatch match = findMatchByDate(date);

        if (match == null) {
            System.out.println("Match not found.");
        }
        return match;
    }

    private void displayMatchDetails(SoccerMatch match) {
        if (match != null) {
            System.out.println("Match Details:");
            System.out.println("Date: " + match.getDate());
            System.out.println("Home Team: " + match.getHomeTeam());
            System.out.println("Away Team: " + match.getAwayTeam());
            displayGoals(match);
            displayAssists(match);
            displayCards(match);
        }
    }

    private void displayGoals(SoccerMatch match) {
        System.out.println("Goals:");
        for (model.Goal goal : match.getMatchDetails().getGoals()) {
            System.out.println(goal.getPlayer().getName() + " - Minute: " + goal.getMinute());
        }
    }

    private void displayAssists(SoccerMatch match) {
        System.out.println("Assists:");
        for (model.Assist assist : match.getMatchDetails().getAssists()) {
            System.out.println(assist.getPlayer().getName() + " - Minute: " + assist.getMinute());
        }
    }

    private void displayCards(SoccerMatch match) {
        System.out.println("Cards:");
        for (model.Card card : match.getMatchDetails().getCards()) {
            System.out.println(card.getPlayer().getName() + " - Minute: " + card.getMinute() + " - "
                    + card.getCardType());
        }
        System.out.print("\nPress 'n' to continue to the main menu: ");
        while (!input.next().equalsIgnoreCase("n")) {
            System.out.print("Invalid input. Press 'n' to continue to the main menu: ");
        }
    }



    private SoccerMatch findMatchByDate(String date) {
        for (SoccerMatch match : listOfMatches.getMatches()) {
            if (match.getDate().equals(date)) {
                return match;
            }
        }
        return null;
    }

    // Used
    private int inputYear() {
        int year = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter match year: ");
            if (input.hasNextInt()) {
                year = input.nextInt();
                if (year >= 1900 && year <= 9999) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid year between 1900 and 9999.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the year.");
                input.next(); // Clear invalid input
            }
        }
        return year;
    }

    private int inputMonth() {
        int month = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter match month: ");
            if (input.hasNextInt()) {
                month = input.nextInt();
                if (month >= 1 && month <= 12) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid month between 1 and 12.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the month.");
                input.next(); // Clear invalid input
            }
        }
        return month;
    }

    private int inputDay() {
        int day = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter match day: ");
            if (input.hasNextInt()) {
                day = input.nextInt();
                if (day >= 1 && day <= 31) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid day between 1 and 31.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the day.");
                input.next(); // Clear invalid input
            }
        }
        return day;
    }

    private SoccerMatch createMatch(int year, int month, int day) {
        String date = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

        System.out.print("Enter home team: ");
        String homeTeam = input.next();
        System.out.print("Enter away team: ");
        String awayTeam = input.next();

        return new SoccerMatch(homeTeam, awayTeam, date);
    }

    private void addGoals(SoccerMatch match) {
        System.out.print("Enter number of goals: ");
        int numGoals = input.nextInt();
        for (int i = 0; i < numGoals; i++) {
            System.out.print("Enter scorer name: ");
            String scorerName = input.next();
            System.out.print("Enter minute: ");
            int minute = input.nextInt();
            match.getMatchDetails().addGoal(new Player(scorerName, new Team("")), minute);
        }
    }

    private void addAssists(SoccerMatch match) {
        System.out.print("Enter number of assists: ");
        int numAssists = input.nextInt();
        for (int i = 0; i < numAssists; i++) {
            System.out.print("Enter assister name: ");
            String assisterName = input.next();
            System.out.print("Enter minute: ");
            int minute = input.nextInt();
            match.getMatchDetails().addAssist(new Player(assisterName, new Team("")), minute);
        }
    }

    private void addCards(SoccerMatch match) {
        System.out.print("Enter number of cards: ");
        int numCards = input.nextInt();
        for (int i = 0; i < numCards; i++) {
            System.out.print("Enter player name: ");
            String playerName = input.next();
            System.out.print("Enter minute: ");
            int minute = input.nextInt();
            System.out.print("Enter card type (yellow/red): ");
            String cardType = input.next();
            match.getMatchDetails().addCard(new Player(playerName, new Team("")), minute, cardType);
        }
    }

    private void addMatch() {
        int year = inputYear();
        int month = inputMonth();
        int day = inputDay();

        SoccerMatch match = createMatch(year, month, day);
        addGoals(match);
        addAssists(match);
        addCards(match);

        listOfMatches.addMatch(match);
        System.out.println("Match added successfully.");
    }


    private void viewMatchTracker() {
        if (listOfMatches.getMatches().isEmpty()) {
            System.out.println("No matches in the tracker yet.");
        } else {
            System.out.println("Match Tracker:");
            for (SoccerMatch match : listOfMatches.getMatches()) {
                System.out.println("Date: " + match.getDate());
                System.out.println("Home Team: " + match.getHomeTeam());
                System.out.println("Away Team: " + match.getAwayTeam());
            }
        }

        System.out.print("\nPress 'n' to continue to the main menu: ");
        while (!input.next().equalsIgnoreCase("n")) {
            System.out.print("Invalid input. Press 'n' to continue to the main menu: ");
        }
    }


    private void removeMatch() {
        if (listOfMatches.getMatches().isEmpty()) {
            System.out.println("No matches in tracker yet.");
            return;
        }
        System.out.print("Enter match date (yyyy-mm-dd): ");
        String date = input.next();
        SoccerMatch matchToRemove = findMatchByDate(date);
        if (matchToRemove != null) {
            listOfMatches.removeMatch(matchToRemove);
            System.out.println("Match removed successfully.");
        } else {
            System.out.println("Match not found.");
        }

    }

    private void editMatch() {
        SoccerMatch match = getMatchByDate();
        if (match != null) {
            System.out.println("Editing match for: " + match.getDate());
            System.out.println("Select an option:");
            System.out.println("1 - Add goal");
            System.out.println("2 - Add assist");
            System.out.println("3 - Add card");

            String command = input.next().toLowerCase();
            switch (command) {
                case "1":
                case "2":
                case "3":
                    addEvent(match, command);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } else {
            System.out.println("Match not found.");
        }
    }

    private void addEvent(SoccerMatch match, String eventType) {
        System.out.print("Enter player name: ");
        String playerName = input.next();
        System.out.print("Enter minute: ");
        int minute = input.nextInt();

        switch (eventType) {
            case "1":
                match.getMatchDetails().addGoal(new Player(playerName, new Team("")), minute);
                break;
            case "2":
                match.getMatchDetails().addAssist(new Player(playerName, new Team("")), minute);
                break;
            case "3":
                System.out.print("Enter card type (yellow/red): ");
                String cardType = input.next();
                match.getMatchDetails().addCard(new Player(playerName, new Team("")), minute, cardType);
                break;
        }
    }




}
// GUI for soccer tracker

package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SoccerMatchTrackerGUI {
    private JFrame frame;
    private DefaultListModel<String> matchListModel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ListOfMatches listOfMatches;
    private static final String JSON_STORE = "./data/listOfMatchesGUI.json";




    public SoccerMatchTrackerGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        listOfMatches = new ListOfMatches();
    }

    // Effects: makes main frame of GUI with layout also displaying splash
    // screen
    public void initializeUI() {
        frame = new JFrame("Soccer Match Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventsLogged();
                frame.dispose();
            }
        });

        initializeButtons();
        initializeList();

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        showSplashScreen();
    }

    // EFFECTS: places buttons for loading, saving, adding, and viewing matches
    private void initializeButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));

        JButton loadButton = new JButton("Load Matches");
        loadButton.addActionListener(this::loadMatchList);
        buttonPanel.add(loadButton);

        JButton saveButton = new JButton("Save Matches");
        saveButton.addActionListener(this::saveMatchList);
        buttonPanel.add(saveButton);

        JButton addButton = new JButton("Add Match");
        addButton.addActionListener(this::addMatch);
        buttonPanel.add(addButton);

        JButton viewButton = new JButton("View Tracked Matches");
        viewButton.addActionListener(this::viewTrackedMatches);
        buttonPanel.add(viewButton);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            printEventsLogged();
            frame.dispose();
        });
        buttonPanel.add(closeButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    }

    // EFFECTS: shows model list that displays
    // the list of soccer matches
    private void initializeList() {
        matchListModel = new DefaultListModel<>();
        JList<String> matchList = new JList<>(matchListModel);
        frame.add(new JScrollPane(matchList), BorderLayout.CENTER);
    }

    // EFFECTS: display splash screen
    private void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        ImageIcon imageIcon = new ImageIcon("./data/splash-image.png"); // replace with your image path
        JLabel imageLabel = new JLabel(imageIcon);
        splashScreen.getContentPane().add(imageLabel);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);

        Timer timer = new Timer(3000, e -> splashScreen.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    private void loadMatchList(ActionEvent event) {
        try {
            listOfMatches = jsonReader.readListOfMatches();
            matchListModel.clear();
            for (SoccerMatch match : listOfMatches.getMatches()) {
                matchListModel.addElement(match.toString()); // assuming SoccerMatch has a proper toString() override
            }
            JOptionPane.showMessageDialog(frame, "Matches loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to load matches from file: " + JSON_STORE,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveMatchList(ActionEvent event) {
        try {
            jsonWriter.open();
            jsonWriter.writeListOfMatches(listOfMatches);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Matches saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to save matches to file: " + JSON_STORE,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMatch(ActionEvent event) {
        JTextField homeTeamField = new JTextField();
        JTextField awayTeamField = new JTextField();
        JTextField dateField = new JTextField();

        JPanel matchPanel = new JPanel(new GridLayout(0, 1));
        matchPanel.add(new JLabel("Home Team:"));
        matchPanel.add(homeTeamField);
        matchPanel.add(new JLabel("Away Team:"));
        matchPanel.add(awayTeamField);
        matchPanel.add(new JLabel("Date (yyyy-mm-dd):"));
        matchPanel.add(dateField);

        int result = JOptionPane.showConfirmDialog(frame, matchPanel, "Enter Match Details",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String homeTeam = homeTeamField.getText();
            String awayTeam = awayTeamField.getText();
            String date = dateField.getText();
            SoccerMatch newMatch = new SoccerMatch(homeTeam, awayTeam, date); // Assume SoccerMatch has this constructor
            addGoals(newMatch);
            addAssists(newMatch);
            addCards(newMatch);
            listOfMatches.addMatch(newMatch); // Assume ListOfMatches has this method
            matchListModel.addElement(newMatch.toString()); // Assume SoccerMatch has overridden toString()
        }
    }

    private void viewTrackedMatches(ActionEvent event) {
        JTextArea matchDetailsText = new JTextArea(10, 60);
        matchDetailsText.setEditable(false);

        // Append match details including goals, assists, and cards
        StringBuilder detailsBuilder = new StringBuilder();
        for (SoccerMatch match : listOfMatches.getMatches()) {
            appendMatchDetails(detailsBuilder, match);
            appendGoals(detailsBuilder, match);
            appendAssists(detailsBuilder, match);
            appendCards(detailsBuilder, match);
            appendDivider(detailsBuilder);
        }
        matchDetailsText.setText(detailsBuilder.toString());

        // Scroll pane for text area
        JScrollPane scrollPane = new JScrollPane(matchDetailsText);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Dialog with scrollable text area
        JOptionPane.showMessageDialog(frame, scrollPane, "Tracked Matches",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void appendMatchDetails(StringBuilder builder, SoccerMatch match) {
        builder.append("Date: ").append(match.getDate()).append("\n");
        builder.append("Teams: ").append(match.getHomeTeam()).append(" vs ").append(match.getAwayTeam()).append("\n");
    }

    private void appendGoals(StringBuilder builder, SoccerMatch match) {
        builder.append("\nGoals:\n");
        match.getMatchDetails().getGoals().forEach(goal ->
                builder.append(goal.getPlayer().getName()).append(" (")
                        .append(goal.getMinute()).append("'").append(")\n"));
    }

    private void appendAssists(StringBuilder builder, SoccerMatch match) {
        builder.append("\nAssists:\n");
        match.getMatchDetails().getAssists().forEach(assist ->
                builder.append(assist.getPlayer().getName()).append(" (")
                        .append(assist.getMinute()).append("'").append(")\n"));
    }

    private void appendCards(StringBuilder builder, SoccerMatch match) {
        builder.append("\nCards:\n");
        match.getMatchDetails().getCards().forEach(card ->
                builder.append(card.getPlayer().getName()).append(" (")
                        .append(card.getMinute()).append("'").append(", ")
                        .append(card.getCardType()).append(")\n"));
    }

    private void appendDivider(StringBuilder builder) {
        builder.append("\n-------------------\n");
    }


    private void addGoals(SoccerMatch match) {
        int numGoals = Integer.parseInt(JOptionPane.showInputDialog("Enter number of goals:"));
        for (int i = 0; i < numGoals; i++) {
            String scorerName = JOptionPane.showInputDialog("Enter scorer name:");
            int minute = Integer.parseInt(JOptionPane.showInputDialog("Enter minute:"));
            match.getMatchDetails().addGoal(new Player(scorerName, new Team("")), minute);
        }
    }

    private void addAssists(SoccerMatch match) {
        int numAssists = Integer.parseInt(JOptionPane.showInputDialog("Enter number of assists:"));
        for (int i = 0; i < numAssists; i++) {
            String assisterName = JOptionPane.showInputDialog("Enter assister name:");
            int minute = Integer.parseInt(JOptionPane.showInputDialog("Enter minute:"));
            match.getMatchDetails().addAssist(new Player(assisterName, new Team("")), minute);
        }
    }

    private void addCards(SoccerMatch match) {
        int numCards = Integer.parseInt(JOptionPane.showInputDialog("Enter number of cards:"));
        for (int i = 0; i < numCards; i++) {
            String playerName = JOptionPane.showInputDialog("Enter player name:");
            int minute = Integer.parseInt(JOptionPane.showInputDialog("Enter minute:"));
            String cardType = JOptionPane.showInputDialog("Enter card type (yellow/red):");
            match.getMatchDetails().addCard(new Player(playerName, new Team("")), minute, cardType);
        }
    }

    private void printEventsLogged() {
        EventLog eventsLogged = EventLog.getInstance();
        for (Event e : eventsLogged) {
            System.out.println(e);
        }
    }

}

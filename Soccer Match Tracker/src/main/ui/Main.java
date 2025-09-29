package ui;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SoccerMatchTracker soccerMatchTracker = new SoccerMatchTracker();
        System.out.println("1 - UI");
        System.out.println("2 - GUI");
        Scanner input = new Scanner(System.in);
        String choice = input.next().toLowerCase();


        if (choice.equals("1")) {
            soccerMatchTracker.runTracker();

        } else if (choice.equals("2")) {
            SwingUtilities.invokeLater(() -> new SoccerMatchTrackerGUI().initializeUI());

        }




    }
}

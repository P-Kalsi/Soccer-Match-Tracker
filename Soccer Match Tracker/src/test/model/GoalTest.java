package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {
    private Goal goal;
    private Goal goal2;
    private Player Bhaggi;
    private Player Kam;
    private Player Inder;
    private Team team;
    private Team team2;

    @BeforeEach
    public void setUp() {
        team = new Team("ManUnited");
        team2 = new Team("Chelsea");

        Bhaggi = new Player("Bhaggi", team);
        Kam = new Player("Kam", team);
        Inder = new Player("Inder", team2);


    }


    @Test
    public void testConstructor() {
        goal = new Goal(Bhaggi, 30);
        assertEquals(30, goal.getMinute());
        assertEquals(Bhaggi, goal.getPlayer());
    }

    @Test
    public void testSetPlayer() {
        goal = new Goal(Bhaggi, 30);
        goal.setPlayer(Bhaggi);
        assertEquals(Bhaggi, goal.getPlayer());

        goal2 = new Goal(Bhaggi, 40);
        goal2.setPlayer(Kam);
        assertEquals(Kam, goal2.getPlayer());
    }

    @Test
    public void testGetPlayer() {
        goal = new Goal(Bhaggi, 30);
        assertEquals(Bhaggi, goal.getPlayer());

        goal2 = new Goal(Bhaggi, 40);
        assertEquals(Bhaggi, goal2.getPlayer());
    }

    @Test
    public void testGetMinute() {
        goal = new Goal(Bhaggi, 30);
        assertEquals(30, goal.getMinute());

        goal.setPlayer(Kam);
        assertEquals(30, goal.getMinute());
        goal.setMinute(20);
        assertEquals(20, goal.getMinute());

        goal2 = new Goal(Bhaggi, 40);
        assertEquals(40, goal2.getMinute());

        goal2.setMinute(32);
        assertEquals(32, goal2.getMinute());
    }

    @Test
    public void testSetMinute() {
        goal = new Goal(Bhaggi, 30);
        assertEquals(30, goal.getMinute());

        goal.setMinute(122);
        assertEquals(122, goal.getMinute());
        goal.setMinute(20);
        assertEquals(20, goal.getMinute());

        goal2 = new Goal(Bhaggi, 40);
        assertEquals(40, goal2.getMinute());

        goal2.setMinute(32);
        assertEquals(32, goal2.getMinute());
    }

}



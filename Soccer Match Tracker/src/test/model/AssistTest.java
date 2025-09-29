package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssistTest {
    private Assist assist;
    private Assist assist2;
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
        assist = new Assist(Bhaggi, 30);
        assertEquals(30, assist.getMinute());
        assertEquals(Bhaggi, assist.getPlayer());
    }

    @Test
    public void testSetPlayer() {
        assist = new Assist(Bhaggi, 30);
        assist.setPlayer(Bhaggi);
        assertEquals(Bhaggi, assist.getPlayer());

        assist2 = new Assist(Bhaggi, 40);
        assist2.setPlayer(Kam);
        assertEquals(Kam, assist2.getPlayer());
    }

    @Test
    public void testGetPlayer() {
        assist = new Assist(Bhaggi, 30);
        assertEquals(Bhaggi, assist.getPlayer());

        assist2 = new Assist(Bhaggi, 40);
        assertEquals(Bhaggi, assist2.getPlayer());
    }

    @Test
    public void testGetMinute() {
        assist = new Assist(Bhaggi, 30);
        assertEquals(30, assist.getMinute());

        assist.setPlayer(Kam);
        assertEquals(30, assist.getMinute());
        assist.setMinute(20);
        assertEquals(20, assist.getMinute());

        assist2 = new Assist(Bhaggi, 40);
        assertEquals(40, assist2.getMinute());

        assist2.setMinute(32);
        assertEquals(32, assist2.getMinute());
    }

    @Test
    public void testSetMinute() {
        assist = new Assist(Bhaggi, 30);
        assertEquals(30, assist.getMinute());

        assist.setMinute(122);
        assertEquals(122, assist.getMinute());
        assist.setMinute(20);
        assertEquals(20, assist.getMinute());

        assist2 = new Assist(Bhaggi, 40);
        assertEquals(40, assist2.getMinute());

        assist2.setMinute(32);
        assertEquals(32, assist2.getMinute());
    }

}

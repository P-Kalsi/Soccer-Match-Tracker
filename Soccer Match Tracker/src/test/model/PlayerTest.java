package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

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
        assertEquals(team, Bhaggi.getTeam());
        assertEquals("Bhaggi", Bhaggi.getName());




    }

    @Test
    public void testGetName() {


        assertEquals("Bhaggi", Bhaggi.getName());



        assertEquals("Kam", Kam.getName());
    }

    @Test
    public void testSetName() {

        Bhaggi.setName("Bilo");
        assertEquals("Bilo", Bhaggi.getName());


        Bhaggi.setName("Gomez");
        assertEquals("Gomez", Bhaggi.getName());
    }

    @Test
    public void testGetTeam() {


        assertEquals(team, Bhaggi.getTeam());



        assertEquals(team2, Inder.getTeam());
    }

    @Test
    public void testSetTeam() {


        assertEquals(team, Bhaggi.getTeam());
        Bhaggi.setTeam(team2);
        assertEquals(team2, Bhaggi.getTeam());
    }





}



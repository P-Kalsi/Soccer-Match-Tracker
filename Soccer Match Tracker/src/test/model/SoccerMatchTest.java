package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoccerMatchTest {

    private SoccerMatch soccerMatch;

    @BeforeEach
    public void setUp() {
        soccerMatch = new SoccerMatch("Monke","Gorilla","2022-02-23");

    }

    @Test
    public void testConstructor() {
        assertEquals("Gorilla", soccerMatch.getAwayTeam());
        assertEquals("Monke", soccerMatch.getHomeTeam());
        assertEquals("2022-02-23", soccerMatch.getDate());
        assertEquals(0, soccerMatch.getMatchDetails().getCards().size());
        assertEquals(0, soccerMatch.getMatchDetails().getGoals().size());
        assertEquals(0, soccerMatch.getMatchDetails().getAssists().size());


    }

    @Test
    public void testGetHomeTeam() {
        assertEquals("Monke", soccerMatch.getHomeTeam());
    }

    @Test
    public void testSetHomeTeam() {
        assertEquals("Monke", soccerMatch.getHomeTeam());
        soccerMatch.setHomeTeam("Bilo");
        assertEquals("Bilo", soccerMatch.getHomeTeam());


        soccerMatch.setHomeTeam("Juju");
        assertEquals("Juju", soccerMatch.getHomeTeam());
    }

    @Test
    public void testGetAwayTeam() {
        assertEquals("Gorilla", soccerMatch.getAwayTeam());
    }

    @Test
    public void testSetAwayTeam() {
        assertEquals("Gorilla", soccerMatch.getAwayTeam());
        soccerMatch.setAwayTeam("Bilo");
        assertEquals("Bilo", soccerMatch.getAwayTeam());


        soccerMatch.setAwayTeam("Juju");
        assertEquals("Juju", soccerMatch.getAwayTeam());
    }

    @Test
    public void testGetDate() {
        assertEquals("2022-02-23", soccerMatch.getDate());
    }

    @Test
    public void testSetDate() {
        assertEquals("2022-02-23", soccerMatch.getDate());
        soccerMatch.setDate("2002-01-03");
        assertEquals("2002-01-03", soccerMatch.getDate());


        soccerMatch.setDate("1946-01-03");
        assertEquals("1946-01-03", soccerMatch.getDate());
    }

}

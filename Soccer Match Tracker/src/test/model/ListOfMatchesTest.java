package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfMatchesTest {
    private ListOfMatches loms;
    private SoccerMatch match1;
    private SoccerMatch match2;

    @BeforeEach
    void setup() {
        loms = new ListOfMatches();
        match1 = new SoccerMatch("ManU", "Chelsea", "2021-01-01");
        match2 = new SoccerMatch("Bhaggi", "Manav", "2020-02-01");
    }

    @Test
    void addMatchesTest() {
        assertEquals(0,loms.getMatches().size());
        loms.addMatch(match1);
        assertEquals(1,loms.getNumMatches());
        loms.addMatch(match1);
        assertEquals(1,loms.getNumMatches());
        loms.addMatch(match2);
        assertEquals(2,loms.getNumMatches());

        loms.addMatch(match2);
        assertEquals(2,loms.getNumMatches());


    }

    @Test
    void removeMatchesTest() {
        assertEquals(0,loms.getMatches().size());
        loms.addMatch(match1);
        assertEquals(1,loms.getNumMatches());
        loms.addMatch(match2);
        assertEquals(2,loms.getNumMatches());
        loms.removeMatch(match1);
        assertEquals(1,loms.getNumMatches());
        loms.removeMatch(match2);
        assertEquals(0,loms.getNumMatches());

    }

    @Test
    void getMatchesIndex() {
        loms.addMatch(match1);
        assertEquals(match1, loms.getMatchAtIndex(0));
        loms.addMatch(match2);
        assertEquals(match2, loms.getMatchAtIndex(0));
        assertEquals(match1, loms.getMatchAtIndex(1));
    }

    @Test
    void getNumMatchesTest() {
        assertEquals(0, loms.getNumMatches());
        loms.addMatch(match1);
        loms.addMatch(match2);
        assertEquals(2, loms.getNumMatches());

    }

    @Test
    void getMatchesTest() {

        loms.addMatch(match1);
        loms.addMatch(match2);
        assertEquals(2, loms.getNumMatches());
        ArrayList<SoccerMatch> los = new ArrayList<>();
        los.add(match2);
        los.add(match1);
        assertEquals(2, los.size());

        assertEquals(los,loms.getMatches());



    }


}

package persistence;

import model.SoccerMatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class jsonTest {

    protected void matchChecker(String homeTeam, String awayTeam, String date, SoccerMatch match) {
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(date, match.getDate());
    }
}

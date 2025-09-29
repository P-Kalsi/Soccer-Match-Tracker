package persistence;

import model.ListOfMatches;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class jsonReaderTest extends jsonTest {

    @Test
    void noFileTest() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            ListOfMatches lom = reader.readListOfMatches();
            fail("Exception expected");
        } catch (IOException e) {
            // good



        }
    }

    @Test
    void readerEmptyListTest() {
        JsonReader reader = new JsonReader("./data/emptyListTest.json");
        try {
            ListOfMatches lom = reader.readListOfMatches();
            assertEquals(0, lom.getNumMatches());
            assertTrue(lom.getMatches().isEmpty());
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }

    @Test
    void readerListOfMatchesTest() {
        JsonReader reader = new JsonReader("./data/listOfMatchesTest2.json");
        try {
            ListOfMatches lom = reader.readListOfMatches();
            assertEquals("1",lom.getMatches().get(0).getHomeTeam());
            assertEquals("2",lom.getMatches().get(0).getAwayTeam());
            assertEquals("2000-01-01",lom.getMatches().get(0).getDate());
            assertEquals(1,lom.getMatchAtIndex(0).getMatchDetails().getAssists().size());
            assertEquals(1,lom.getMatchAtIndex(0).getMatchDetails().getCards().size());
            assertEquals(1,lom.getMatchAtIndex(0).getMatchDetails().getGoals().size());
        } catch (IOException e) {
            fail("Shouldn't have thrown exception");
        }
    }

}

package persistence;

import model.ListOfMatches;
import model.SoccerMatch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class jsonWriterTest extends jsonTest {

    @Test
    void writerErrorTest() {
        try {
            ListOfMatches lom = new ListOfMatches();
            JsonWriter writer = new JsonWriter("./data/noF\0ile.json");
            writer.open();
            fail("IOException expected");

        } catch (IOException e) {
            // nice
        }
    }

    @Test
    void writerEmptyListTest() {
        try {
            ListOfMatches lom = new ListOfMatches();
            JsonWriter writer = new JsonWriter("./data/emptyWriterListTest.json");
            writer.open();
            writer.writeListOfMatches(lom);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyWriterListTest.json");
            lom = reader.readListOfMatches();
            assertEquals(0, lom.getMatches().size());
        } catch (IOException e) {
            fail("Shouldnt be thrown");
        }

    }

    @Test
    void testWriterTest() {
        try {
            ListOfMatches lom = new ListOfMatches();
            SoccerMatch soccerMatch = new SoccerMatch("ManU", "Chelsea", "2022-05-03");

            lom.addMatch(soccerMatch);
            JsonWriter writer = new JsonWriter("./data/writerListTest");
            writer.open();
            writer.writeListOfMatches(lom);
            writer.close();

            JsonReader reader = new JsonReader("./data/writerListTest");
            lom = reader.readListOfMatches();
            assertEquals(1, lom.getNumMatches());
            matchChecker("ManU", "Chelsea", "2022-05-03", soccerMatch);

        } catch (IOException e) {
            fail("Nothing should be thrown");
        }
    }










}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchDetailsTest {
    private Card card;
    private Card card2;
    private Player Bhaggi;
    private Player Kam;
    private Player Inder;
    private Goal goal;
    private Goal goal2;
    private Team team;
    private Team team2;
    private List<Card> cardList;
    private List<Goal> goalList;
    private List<Assist> assistList;
    private MatchDetails matchDetails;
    private MatchDetails matchDetails2;
    private MatchDetails matchDetails3;
    private MatchDetails matchDetails4;


    @BeforeEach
    public void setUp() {
        team = new Team("ManUnited");
        team2 = new Team("Chelsea");

        goal = new Goal(Bhaggi, 30);
        goal2 = new Goal(Kam, 22);


        Bhaggi = new Player("Bhaggi", team);
        Kam = new Player("Kam", team);
        Inder = new Player("Inder", team2);
        card = new Card(Bhaggi, 1, "red");
        card2 = new Card(Inder, 90, "yellow");

        cardList = new ArrayList<>();
        goalList = new ArrayList<>();
        assistList = new ArrayList<>();

        matchDetails = new MatchDetails();
        matchDetails2 = new MatchDetails();
        matchDetails3 = new MatchDetails();
        matchDetails4 = new MatchDetails();

    }

    @Test
    public void testConstructor() {
        assertEquals(cardList,matchDetails.getCards());
        assertEquals(goalList,matchDetails.getGoals());
        assertEquals(assistList,matchDetails.getAssists());
    }

    @Test
    public void testGetGoals() {
        assertEquals(goalList,matchDetails.getGoals());
        assertTrue(matchDetails.getGoals().isEmpty());


    }

    @Test
    public void testAddGoal() {
        assertEquals(goalList,matchDetails.getGoals());
        assertTrue(matchDetails.getGoals().isEmpty());
        assertEquals(0,matchDetails.getGoals().size());

        matchDetails.addGoal(Bhaggi,22 );
        assertFalse(matchDetails.getGoals().isEmpty());
        assertEquals(1,matchDetails.getGoals().size());

        matchDetails.addGoal(Bhaggi,23 );
        assertFalse(matchDetails.getGoals().isEmpty());
        assertEquals(2,matchDetails.getGoals().size());




    }

    @Test
    public void testGetAssists() {
        assertEquals(assistList,matchDetails.getAssists());
        assertTrue(matchDetails.getAssists().isEmpty());


    }

    @Test
    public void testAddAssist() {
        assertEquals(assistList,matchDetails.getAssists());
        assertTrue(matchDetails.getAssists().isEmpty());
        assertEquals(0,matchDetails.getAssists().size());

        matchDetails.addAssist(Bhaggi,22 );
        assertFalse(matchDetails.getAssists().isEmpty());
        assertEquals(1,matchDetails.getAssists().size());

        matchDetails.addAssist(Bhaggi,23 );
        assertFalse(matchDetails.getAssists().isEmpty());
        assertEquals(2,matchDetails.getAssists().size());




    }

    @Test
    public void testGetCards() {
        assertEquals(cardList,matchDetails.getCards());
        assertTrue(matchDetails.getCards().isEmpty());


    }

    @Test
    public void testAddCards() {
        assertEquals(cardList,matchDetails.getCards());
        assertTrue(matchDetails.getCards().isEmpty());
        assertEquals(0,matchDetails.getCards().size());

        matchDetails.addCard(Bhaggi,22 , "yellow");
        assertFalse(matchDetails.getCards().isEmpty());
        assertEquals(1,matchDetails.getCards().size());

        // since 2 yellows in soccer makes a red
        matchDetails.addCard(Bhaggi,23 , "yellow");
        assertFalse(matchDetails.getCards().isEmpty());
        assertEquals(3,matchDetails.getCards().size());
        assertEquals("red",matchDetails.getCards().get(2).getCardType());

        matchDetails2.addCard(Bhaggi, 1, "red");
        assertFalse(matchDetails2.getCards().isEmpty());
        assertEquals(1,matchDetails2.getCards().size());
        matchDetails2.addCard(Bhaggi,23 , "yellow");
        assertFalse(matchDetails2.getCards().isEmpty());
        assertEquals(2,matchDetails2.getCards().size());
        assertEquals("yellow",matchDetails2.getCards().get(1).getCardType());

        matchDetails3.addCard(Bhaggi, 1, "yellow");
        assertFalse(matchDetails.getCards().isEmpty());
        assertEquals(1,matchDetails3.getCards().size());
        matchDetails3.addCard(Bhaggi,23 , "red");
        assertFalse(matchDetails3.getCards().isEmpty());
        assertEquals(2,matchDetails3.getCards().size());
        matchDetails3.addCard(Kam,23 , "yellow");
        assertFalse(matchDetails3.getCards().isEmpty());
        assertEquals(3,matchDetails3.getCards().size());

    }



}
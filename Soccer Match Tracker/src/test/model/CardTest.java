package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class CardTest {

    private Card card;
    private Card card2;
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
        card = new Card(Bhaggi, 1, "red");
        card2 = new Card(Inder, 90, "yellow");


    }


    @Test
    public void testConstructor() {
        assertEquals(1, card.getMinute());
        assertEquals(Bhaggi, card.getPlayer());
        assertEquals("red", card.getCardType());



    }

    @Test
    public void testSetPlayer() {

        card.setPlayer(Bhaggi);
        assertEquals(Bhaggi, card.getPlayer());


        card2.setPlayer(Kam);
        assertEquals(Kam, card2.getPlayer());
    }

    @Test
    public void testGetPlayer() {

        assertEquals(Bhaggi, card.getPlayer());

        assertEquals(Inder, card2.getPlayer());
    }

    @Test
    public void testGetMinute() {

        assertEquals(1, card.getMinute());

        card.setPlayer(Kam);
        assertEquals(1, card.getMinute());
        card.setMinute(20);
        assertEquals(20, card.getMinute());


        assertEquals(90, card2.getMinute());

        card2.setMinute(100);
        assertEquals(100, card2.getMinute());
    }

    @Test
    public void testSetMinute() {

        assertEquals(1, card.getMinute());

        card.setMinute(122);
        assertEquals(122, card.getMinute());
        card.setMinute(20);
        assertEquals(20, card.getMinute());


        assertEquals(90, card2.getMinute());

        card2.setMinute(32);
        assertEquals(32, card2.getMinute());
    }

    @Test
    public void testGetCardType() {
        assertEquals("red", card.getCardType());
        card.setCardType("red");
        assertEquals("red", card.getCardType());

        card.setCardType("yellow");
        assertEquals("yellow", card.getCardType());

    }

    @Test
    public void testSetCardType() {
        assertEquals("red", card.getCardType());
        card.setCardType("red");
        assertEquals("red", card.getCardType());

        card.setCardType("yellow");
        assertEquals("yellow", card.getCardType());
        card.setCardType("red");
        assertEquals("red", card.getCardType());

    }

}

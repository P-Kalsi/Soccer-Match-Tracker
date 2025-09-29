package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {
    private Team team;
    private Team team2;

    @BeforeEach
    public void setUp() {
        team = new Team("ManUnited");
        team2 = new Team("Chelsea");





    }


    @Test
    public void testConstructor() {
        assertEquals("ManUnited", team.getName());





    }

    @Test
    public void testGetName() {


        assertEquals("ManUnited", team.getName());



        assertEquals("Chelsea", team2.getName());
    }

    @Test
    public void testSetName() {

        assertEquals("ManUnited", team.getName());

        team.setName("Monkeys");
        assertEquals("Monkeys", team.getName());


        team2.setName("Gomez");
        assertEquals("Gomez", team2.getName());
    }
}

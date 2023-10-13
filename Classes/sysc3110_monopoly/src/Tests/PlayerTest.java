package Tests;

import Models.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private static Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player("testPlayer");
    }

    @Test
    public void isBankrupt() {
        assertFalse(p.isBankrupt());
    }

    @Test
    public void getName() {
        assertEquals("testPlayer", p.getName());
    }

    @Test
    public void setName() {
        assertEquals("playerTest", p.setName("playerTest"));
    }

    @Test
    public void getWealth() {
        assertEquals(1500.0, p.getWealth(), 0.1);
    }

    @Test
    public void setWealth() {
        assertEquals(1500.0, p.setWealth(1500.0), 0.1);
    }

    @Test
    public void addWealth() {
        assertEquals(3000, p.setWealth(3000), 0.1);
    }

    @Test
    public void getPosition() {
        assertEquals(0, p.getPosition());
    }

    @Test
    public void setPosition() {
        assertEquals(5, p.setPosition(5));
    }

    @Test
    public void isInJail() {
        assertFalse(p.isInJail());
    }

    @Test
    public void setInJail() {
        assertEquals(true, p.setInJail(true));
    }
}
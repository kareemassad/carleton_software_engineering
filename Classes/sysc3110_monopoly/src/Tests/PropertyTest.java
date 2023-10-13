package Tests;

import Models.Player;
import Models.Property;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {

    private static Player p;
    private static Property prop;

    @Before
    public void setUp() throws Exception {
        prop = new Property("testPlayer",1,500.0, p);
    }

    @Test
    public void removeOwner() {
        assertTrue(prop.removeOwner(p));
    }

    @Test
    public void getOwner() {
        assertEquals(p, prop.getOwner());
    }

    @Test
    public void setOwner() {
        assertTrue(prop.setOwner(p));
    }

    @Test
    public void getName() {
        assertEquals("testPlayer", prop.getName());
    }

    @Test
    public void getCost() {
        assertEquals(500.0, prop.getCost(), 0.1);
    }

    @Test
    public void getRentCost() {
        assertEquals(75.0, prop.getRentCost(), 0.1);
    }

    @Test
    public void getPosition() {
        assertEquals(1, prop.getPosition());
    }
}
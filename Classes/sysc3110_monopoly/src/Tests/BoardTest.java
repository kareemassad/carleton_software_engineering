package Tests;

import Models.Board;
import Models.Player;
import Models.Property;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;
    private Player p;
    private Property prop;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        p= new Player("testPlayer");
        prop = new Property("testPlayer",1,500.0, p);
    }

    @Test
    public void getProperties() {

    }

    @Test
    public void getPlayerProperties() {
        String actual = board.getPlayerProperties(p).toString();
        assertEquals("[]", actual);
    }

    @Test
    public void printPlayerProperties() {
        assertEquals("[]", board.printPlayerProperties(p));
    }

    @Test
    public void getPropertyOwner() {
        assertEquals(null, board.getPropertyOwner(1));
    }

    @Test
    public void setPropertyOwner() {
        assertTrue(board.setPropertyOwner(p, prop));
    }

    @Test
    public void removePropertyOwner() {
    }

    @Test
    public void addHouse() {
        assertFalse(board.addHouse(prop));
    }

    @Test
    public void addHotel() {
        assertFalse(board.addHotel(prop));
    }

    @Test
    public void getMaxPosition() {
        assertEquals(39, board.getMaxPosition());
    }

    @Test
    public void getProperty() {
        String actual = board.getProperty(1).toString();
        assertEquals("Property{name='OLD KENT ROAD'}", actual);
    }

    @Test
    public void getGoPosition() {
        assertEquals(0, board.getGoPosition());
    }

    @Override
    public String toString(){
        return "[]" + board.getPlayerProperties(p);
    }
}
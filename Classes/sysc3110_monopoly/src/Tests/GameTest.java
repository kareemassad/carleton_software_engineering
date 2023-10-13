package Tests;

import Models.Board;
import Models.Game;
import Models.Player;
import Models.Property;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private static Game game;
    private static Player p;
    private static Player p2;
    private static Board board;
    private static Property prop;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        p = new Player("testPlayer");
        p2 = new Player("testPlayer2");
        board = new Board();
        prop = new Property("testPlayer",1,500.0, p);

        game.addPlayer(p);
        game.addPlayer(p2);
    }

    @Test
    public void passTurn() {
    }

    @Test
    public void addPlayer() {
        assertTrue(game.addPlayer(p));
    }

    @Test
    public void removePlayer() {
        boolean expected = game.addPlayer(p);
        assertEquals(expected, game.removePlayer(p));
    }

    @Test
    public void getPlayer() {
        assertEquals(p, game.getPlayer(p));
    }

    @Test
    public void getProperty() {
        String actual = game.getProperty(0).toString();
        assertEquals("Property{name='Go'}", actual);
    }

    @Test
    public void setPropertyOwner() {
        assertTrue(game.setPropertyOwner(p, prop));
    }

    @Test
    public void removePropertyOwner() {
    }

    @Test
    public void addHouse() {
        assertFalse(game.addHouse(prop));
    }

    @Test
    public void printPlayerProperties() {
        assertEquals("[]", board.printPlayerProperties(p));
    }

    @Test
    public void incrementTurnCount() {
        assertEquals(1, game.incrementTurnCount());
    }

    @Test
    public void getPlayerList() {
        String actual = game.getPlayerList().toString();
        assertEquals("[Player{name='testPlayer', wealth=1500.0, position=0}, Player{name='testPlayer2', wealth=1500.0, position=0}]", actual);
    }

    @Test
    public void getCurrentPlayer() {
        assertEquals(null, game.getCurrentPlayer());
    }

    @Test
    public void getTurnCount() {
        assertEquals(0, game.getTurnCount());
    }

    @Override
    public String toString(){
        return " ";
    }
}
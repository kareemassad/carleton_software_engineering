import org.junit.Assert;
import org.junit.Test;

import Controller.SaveGameState;
import Model.Player;

import java.io.File;

import static org.junit.Assert.*;

public class SaveGameStateTest {
    private SaveGameState g;
    private Player p;

    public SaveGameStateTest(Player p, SaveGameState g) {
        this.p = p;
        this.g = g;
    }

    @Test
    public void getPlayerScore() {
        //NOTE THESE TESTS WILL ALWAYS FAIL AS THE METHODS THEMSELVES ARE NOT INTEGRATED (Was a diff members responsibily)
        // test that the score is correct
        g.getPlayerScore(p);
        // should be 0
        Assert.assertEquals(0, g.getPlayerScore(p));

        p.setScore(10);
        Assert.assertEquals(10, g.getPlayerScore(p));
    }

    @Test
    public void getPlayerID() {
        //NOTE THESE TESTS WILL ALWAYS FAIL AS THE METHODS THEMSELVES ARE NOT INTEGRATED (Was a diff members responsibily)
        // test that the ID is correct
        g.getPlayerID(p);
        // should be 0
        Assert.assertEquals(0, g.getPlayerID(p));

        p.setID(10);
        Assert.assertEquals(10, g.getPlayerID(p));
    }

    @Test
    public void getNumPlayers() {
        //NOTE THESE TESTS WILL ALWAYS FAIL AS THE METHODS THEMSELVES ARE NOT INTEGRATED (Was a diff members responsibily)
        g.getNumPlayers();
        // should be 0
        Assert.assertEquals(0, g.getNumPlayers());
    }

    @Test
    public void getTurn() {
        //NOTE THESE TESTS WILL ALWAYS FAIL AS THE METHODS THEMSELVES ARE NOT INTEGRATED (Was a diff members responsibily)
        g.getTurn();
        // should be 0
        Assert.assertEquals(0, g.getTurn());

        // set the turn
        g.setTurn(10);
        Assert.assertEquals(10, g.getTurn());
    }

    @Test
    public void saveGame() {
        //NOTE THESE TESTS WILL ALWAYS FAIL AS THE METHODS THEMSELVES ARE NOT INTEGRATED (Was a diff members responsibily)
        g.saveGame();
        // assert that the game.ser file exists

        // make sure that game.ser exists in Saves/game.ser
        File f = new File("Saves/game.ser");
        assertTrue(f.exists());

    }

    @Test
    public void loadGame() {
        // TODO load the game
    }
}
package Tests;

import Model.Bag;
import Model.Letter;
import Model.Player;
import Model.Word;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * This is testing the Player Class
 * @author Laurence Lamarche-Cliche 101173070
 * @version 3.0
 */

public class PlayerTest {
    @Test
    public void testPlayerConstructor(){
        Bag bag = new Bag();
        Player p1 = new Player(1);
        assertNotNull(p1);
        assertNotNull(p1.getHandLetters());
        assertEquals(7, p1.getHandLetters().size()); // Initializes the hand with 7 letters
        assertEquals(0, p1.getScore()); //initializes score to 0
    }

    @Test
    // this test fails
    public void testAddScore(){
        Bag bag = new Bag();
        Player p = new Player(0);
        Letter H = new Letter('H');
        Letter I = new Letter('I');
        //I.setPremium("DL");
        ArrayList<Letter> hiLetters = new ArrayList<Letter>();
        Collections.addAll(hiLetters, H, I);
        Word hi = new Word(hiLetters);
        p.addScore(hi);
        assertEquals(5, p.getScore());

        Letter H2 = new Letter('H');
        Letter E = new Letter('E');
        Letter L = new Letter('L');
        Letter L2 = new Letter('L');
        Letter O = new Letter('O');
        ArrayList<Letter> helloLetters = new ArrayList<>();
        Collections.addAll(helloLetters, H2, E, L, L2, O);

        Word hello = new Word(helloLetters);
        p.addScore(hello);
        assertEquals(13, p.getScore());
    }

    @Test
    public void testGetScore(){
        Bag bag = new Bag();
        Player p1 = new Player(1);
        assertEquals(0, p1.getScore()); //initializes score to 0
    }

    @Test
    public void testSetHand(){
        Bag b = new Bag();
        Player p = new Player(0);
        assertEquals(7, p.getHandLetters().size()); // assert that the initial hand has 7 letters
    }

    @Test
    public void testGetHand(){
        Bag b = new Bag();
        Player p = new Player(0);
        assertEquals(7, p.getHandLetters().size());
        ArrayList<Letter> letters = new ArrayList<Letter>();
        assertEquals(letters.getClass(), p.getHandLetters().getClass());
    }

    @Test
    public void testRemoveFromHand(){
        Bag b = new Bag();
        Player p = new Player(0);
        assertEquals(7, p.getHandLetters().size());
        p.removeFromHand(p.getHandLetters().get(0).toString()); // remove the first letter
        assertEquals(6, p.getHandLetters().size());
        p.removeFromHand(p.getHandLetters().get(0).toString());
        assertEquals(5, p.getHandLetters().size());
    }

    @Test
    public void addToHand(){
        Bag b = new Bag();
        Player p = new Player(0);
        p.removeFromHand(p.getHandLetters().get(0).toString()); // remove three letters at random
        p.removeFromHand(p.getHandLetters().get(0).toString()); // remove three letters at random
        p.removeFromHand(p.getHandLetters().get(0).toString()); // remove three letters at random
        assertEquals(4, p.getHandLetters().size());
        p.addToHand(2);
        assertEquals(6, p.getHandLetters().size());
        p.addToHand(1);
        assertEquals(7, p.getHandLetters().size());
        // trying to add more letters than permitted to a player's hand
        p.addToHand(2);
        // For now, these tests do not pass because we are permitting the player to draw more letters
        assertNotEquals(9, p.getHandLetters().size());
        assertEquals(7, p.getHandLetters().size()); // it will not add the letters. This should never happen
    }
}

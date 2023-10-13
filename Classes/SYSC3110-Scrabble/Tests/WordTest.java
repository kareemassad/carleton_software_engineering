package Tests;

/**
 * This is testing the Word Class
 * @author Laurence Lamarche-Cliche 101173070
 * @version 3.0
 */

import Model.Letter;
import Model.Word;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

public class WordTest {

    @Test
    public void testConstructorLetters(){
        Letter H = new Letter('H');
        Letter I = new Letter('I');
        ArrayList<Letter> wordLetters = new ArrayList<Letter>();
        wordLetters.add(H);
        wordLetters.add(I);
        Word word = new Word(wordLetters);
        assertNotNull(word);
        assertEquals(2, word.getLetters().size());
        ArrayList<Letter> expectedLetters = new ArrayList<Letter>();
        expectedLetters.add(H);
        expectedLetters.add(I);
        assertEquals(expectedLetters, word.getLetters()); //getLetters() have to be tested
    }

    @Test
    public void addLetter() {
        Letter H = new Letter('H');
        Letter O = new Letter('O');
        Letter W = new Letter('W');
        Letter L = new Letter('L');
        ArrayList<Letter> wordLetters = new ArrayList<Letter>();
        Collections.addAll(wordLetters, H, O, W, L);
        Word howl = new Word(wordLetters);
        howl.addLetter(howl.getLetters().size(), new Letter('S'));
        assertNotEquals("HOWL", howl.toString());
        assertEquals("HOWLS", howl.toString());
    }
    @Test
    public void getLetters() {
        Letter C = new Letter('C');
        Letter O = new Letter('O');
        Letter U = new Letter('U');
        Letter R = new Letter('R');
        Letter S = new Letter('S');
        Letter E = new Letter('E');
        ArrayList<Letter> expectedLetters = new ArrayList<Letter>();
        Collections.addAll(expectedLetters, C, O, U, R, S, E);
        ArrayList<Letter> wordLetters = new ArrayList<Letter>();
        Collections.addAll(wordLetters, C, O, U, R, S, E);
        Word course = new Word(wordLetters);
        int i = 0;
        for (Letter letter: expectedLetters){
            assertEquals(letter, course.getLetters().get(i));
            i++;
        }
        assertEquals(expectedLetters, course.getLetters());
    }

    @Test
    public void getScore() {
        // for now, the word does not have a score setter method. That should be tested in Player
    }

    @Test
    public void testSetDirection(){
        Letter H = new Letter('H');
        Letter I = new Letter('I');
        H.setCoordinates(0,0);
        I.setCoordinates(0,1);
        ArrayList<Letter> hiLetters = new ArrayList<>();
        Collections.addAll(hiLetters, H, I);
        Word hi = new Word(hiLetters);

        assertEquals(0, hi.getDirection());

        Letter H2 = new Letter('H');
        Letter E = new Letter('E');
        Letter Y = new Letter('Y');
        H2.setCoordinates(0,0);
        E.setCoordinates(1,0);
        Y.setCoordinates(2,0);
        ArrayList<Letter> heyLetters = new ArrayList<>();
        Collections.addAll(heyLetters, H2, E, Y);
        Word hey = new Word(heyLetters);

        assertEquals(1, hey.getDirection());
    }

    @Test
    public void testSortLetters(){
        Letter H = new Letter('H');
        Letter E = new Letter('E');
        Letter Y = new Letter('Y');
        H.setCoordinates(0,0);
        E.setCoordinates(1,0);
        Y.setCoordinates(2,0);

        ArrayList<Letter> heyBackwardsLetters = new ArrayList<>();
        heyBackwardsLetters.add(Y);
        heyBackwardsLetters.add(E);
        heyBackwardsLetters.add(H);

        assertEquals("[Y, E, H]", heyBackwardsLetters.toString()); // confirm the letters are backwards

        Word hey = new Word(heyBackwardsLetters);

        assertEquals("HEY", hey.toString()); // confirm that the word reorders the letters based on their position
    }

    @Test
    public void testToString() {
        Letter C = new Letter('C');
        Letter O = new Letter('O');
        Letter U = new Letter('U');
        Letter R = new Letter('R');
        Letter S = new Letter('S');
        Letter E = new Letter('E');
        ArrayList<Letter> wordLetters = new ArrayList<Letter>();
        Collections.addAll(wordLetters, C, O, U, R, S, E);
        Word course = new Word(wordLetters);
        assertEquals("COURSE", course.toString());


        Letter T = new Letter('T');
        Letter U2 = new Letter('U');
        Letter M = new Letter('M');
        Letter B = new Letter('B');
        Letter L = new Letter('L');
        Letter E2 = new Letter('E');
        ArrayList<Letter> word2Letters = new ArrayList<Letter>();
        Collections.addAll(word2Letters, T, U2, M, B, L, E2);
        Word tumble = new Word(word2Letters);
        assertEquals("TUMBLE", tumble.toString());
    }
}
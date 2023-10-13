package Tests;

import static org.junit.Assert.*;

import Model.Letter;
import Model.Score;
import Model.Word;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is testing the Score Class
 * @author Laurence Lamarche-Cliche 101173070
 * @version 3.0
 */

public class ScoreTest {
    @Test
    public void testConstructor(){
        Letter H = new Letter('H');
        Letter E = new Letter('E');
        Letter L = new Letter('L');
        Letter L2 = new Letter('L');
        Letter O = new Letter('O');
        ArrayList<Letter> helloLetters = new ArrayList<>();
        Collections.addAll(helloLetters, H, E, L, L2, O);

        Word hello = new Word(helloLetters);
        Score score = new Score(hello);
        assertNotNull(score);
    }

    @Test
    public void testGetWordScore() {

        Letter H = new Letter('H');
        Letter E = new Letter('E');
        Letter L = new Letter('L');
        Letter L2 = new Letter('L');
        Letter O = new Letter('O');
        ArrayList<Letter> helloLetters = new ArrayList<>();
        Collections.addAll(helloLetters, H, E, L, L2, O);

        Word hello = new Word(helloLetters);
        Score helloScore = new Score(hello);
        assertNotNull(helloScore.getWordScore());
        assertEquals(8, helloScore.getWordScore());
    }
    @Test
    public void testCalculateWord(){
        //Word hi = new Word("");
        Letter H = new Letter('H');
        Letter I = new Letter('I');
        //I.setPremium("DL");
        ArrayList<Letter> hiLetters = new ArrayList<Letter>();
        Collections.addAll(hiLetters, H, I);
        Word hi = new Word(hiLetters);
        Score hiScore = new Score(hi);
        assertEquals(5, hiScore.getWordScore());
        //test with premium implementation
        I.setPremium("DL");
        ArrayList<Letter> hiDoubleILetters = new ArrayList<Letter>();
        Collections.addAll(hiDoubleILetters, H, I);
        Word hiDoubleI = new Word(hiDoubleILetters);
        Score hiDoubleIScore = new Score(hiDoubleI);
        assertEquals(6, hiDoubleIScore.getWordScore());
    }
}
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class WordleTest {
    Wordle model;

    @Before
    public void setUp() throws IOException {
        model = new Wordle();
        model.setSecretWord("apple");
    }

    @Test
    public void testCorrectGuess(){
        String[] feedback = model.checkGuess("apple");
        assertArrayEquals(new String[]{"green", "green", "green", "green", "green"}, feedback);
    }

    @Test
    public void testWrongGuess(){
        assertFalse(model.isGuessCorrect("wrong"));
    }

    @Test
    public void testGameOver(){
        for (int i = 0; i < Wordle.MAX_ATTEMPTS; i++) {
            model.checkGuess("wrong");
        }
        assertTrue(model.isGameOver());
    }
}


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GuessItTest {
    GuessIt model;

    @Before
    public void setUp() throws IOException {
        model = new GuessIt();
        model.setSecretWord("apple");
    }

    @Test
    public void testCorrectGuess() {
        String[] feedback = model.checkGuess("apple");
        assertArrayEquals(new String[] { "green", "green", "green", "green", "green" }, feedback);
        // test floating list
        assertArrayEquals(new String[] { "a", "p", "p", "l", "e" }, model.getFloatingList());
    }

    @Test
    public void testGameOver() {
        for (int i = 0; i < GuessIt.MAX_ATTEMPTS; i++) {
            model.checkGuess("wrong");
        }
        assertTrue(model.isGameOver());
    }

    @Test
    public void getSecretWord() {
        assertEquals("apple", model.getSecretWord());
    }

    @Test
    public void setSecretWord() {
        model.setSecretWord("banana");
        assertEquals("banana", model.getSecretWord());
    }
}

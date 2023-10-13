package Tests;

import Model.Letter;
import Model.ScrabbleGame;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is testing the Letter Class
 * @author Laurence Lamarche-Cliche 101173070
 * @version 3.0
 */
public class LetterTest {

    @Test
    public void testConstructors() {
        Letter A = new Letter(Letter.Character.A);
        assertNotNull(A);

        Letter B = new Letter('B');
        assertNotNull(B);

        Letter A1 = new Letter('A');
        assertEquals(Letter.class, A1.getClass());
        assertEquals(A1.getClass(), A.getClass());
    }

    @Test
    public void testGetCharacterFromChar() {
        assertEquals(Letter.Character.class, Letter.getCharacterFromChar('L').getClass());
        assertEquals(Letter.Character.class, Letter.getCharacterFromChar('l').getClass());
    }

    @Test
    public void testGetValue() {
        Letter L = new Letter('L');
        assertEquals(1, L.getValue());

        Letter D = new Letter('D');
        Letter G = new Letter('G');
        assertEquals(2, D.getValue());
        assertEquals(D.getValue(), G.getValue());

        Letter B = new Letter('B');
        assertEquals(3, B.getValue());
    }

    @Test
    public void testSetAndGetPremium() {
        Letter B = new Letter('B');
        assertEquals("NONE", B.getPremium()); // default premium set to null
    }

    @Test
    public void testSetAndGetCoordinates(){
        Letter B = new Letter('B');
        assertEquals(-1, B.getRow()); // initialized to -1 by default before it is placed
        assertEquals(-1, B.getCol()); // initialized to -1 by default before it is placed

        B.setCoordinates(0, 1);
        assertEquals(0, B.getRow());
        assertEquals(1, B.getCol());

        B.setCoordinates(14, 14);
        assertEquals(14, B.getRow());
        assertEquals(14, B.getCol());

        Letter A = new Letter("A");
        A.setCoordinates(15, 2); // this should never happen with GUI because the location does not exist...
        assertEquals(-1, A.getRow()); // invalid index (15), should remain the default coordinates
        assertEquals(-1, A.getCol());
    }

    @Test
    public void testToString() {
        Letter A = new Letter('A');
        Letter B = new Letter('B');
        assertEquals("A", A.toString());
        assertEquals("B", B.toString());
        Letter None = new Letter(Letter.Character.NONE);
        assertEquals(" ", None.toString());
        Letter blankTile = new Letter(Letter.Character.BLANKTILE);
        assertEquals("_", blankTile.toString());
    }
}
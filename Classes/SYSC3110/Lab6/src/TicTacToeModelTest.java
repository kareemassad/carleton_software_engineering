import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeModelTest {

    private TicTacToeModel ttt;

    @Before
    public void setUp(){
        ttt = new TicTacToeModel();
    }

    @Test
    public void testGetStatus(){
        assertEquals(TicTacToeModel.Status.UNDECIDED, ttt.getStatus());
    }

    @Test
    public void testPlayForRows(){
        ttt.play(0,0);
        ttt.play(1,0);
        ttt.play(0,1);
        ttt.play(1,1);
        ttt.play(0,2);
        ttt.play(2,1);

        assertEquals(TicTacToeModel.Status.X_WON, ttt.getStatus());
    }

    @Test
    public void testPlayForColumns(){
        ttt.play(0,0);
        ttt.play(0,1);
        ttt.play(1,0);
        ttt.play(1,1);
        ttt.play(2,0);
        ttt.play(2,1);

        assertEquals(TicTacToeModel.Status.O_WON, ttt.getStatus());
    }
}
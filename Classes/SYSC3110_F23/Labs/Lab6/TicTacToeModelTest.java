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
    public void getStatus() {
        assertEquals(TicTacToeModel.Status.UNDECIDED, ttt.getStatus());
    }

    @Test
    public void testRowWin(){
        ttt.play(0,0);
        ttt.play(1,0);
        ttt.play(0,1);
        ttt.play(1,1);
        ttt.play(0,2);
        ttt.play(2,0);

        assertEquals(TicTacToeModel.Status.X_WON, ttt.getStatus());
    }

    @Test
    public void testColWin(){
        ttt.play(0,0);
        ttt.play(0,1);
        ttt.play(1,0);
        ttt.play(1,1);
        ttt.play(2,0);
        ttt.play(2,1);

        assertEquals(TicTacToeModel.Status.O_WON, ttt.getStatus());
    }

    @Test
    public void testDiagWin1(){
        ttt.play(0,0);
        ttt.play(0,1);
        ttt.play(1,1);
        ttt.play(1,2);
        ttt.play(2,2);
        assertEquals(TicTacToeModel.Status.X_WON, ttt.getStatus());
    }
    @Test
    public void testDiagWin2(){
        ttt.play(0,2);
        ttt.play(0,1);
        ttt.play(1,1);
        ttt.play(1,2);
        ttt.play(2,0);
        assertEquals(TicTacToeModel.Status.X_WON, ttt.getStatus());
    }

    @Test
    public void testDraw(){
        ttt.play(0,0);
        ttt.play(0,1);
        ttt.play(0,2);
        ttt.play(1,0);
        ttt.play(1,1);
        ttt.play(1,2);
        ttt.play(2,0);
        ttt.play(2,1);
        ttt.play(2,2);

        assertEquals(TicTacToeModel.Status.TIE, ttt.getStatus());
    }
}
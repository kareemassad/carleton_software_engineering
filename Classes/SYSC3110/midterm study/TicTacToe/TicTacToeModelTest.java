import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeModelTest {

    private TicTacToeModel model;

@Before
public void setUp() throws Exception {
model = new Tic TacToeModel();
}

    @Test
    public void getStatus() {
        assertEquals(model.getStatus(), TicTacToeModel.Status.UNDECIDED);
    }

    @Test
    public void testPlayForRows() {
        model.play(0, 0);
        model.play(1, 0);
        model.play(0, 1);
        model.play(1, 1);
        model.play(0, 2);
        model.play(2, 1);

        assertEquals(TicTacToeModel.Status.X_WON, model.getStatus());
    }

    public void testPlayForColumns() {
        model.play(0, 0);
        model.play(0, 1);
        model.play(1, 0);
        model.play(1, 1);
        model.play(2, 0);
        model.play(2, 1);

        assertEquals(TicTacToeModel.Status.O_WON, model.getStatus());
    }
}
import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    public enum Status {
        X_WON, O_WON, TIE, UNDECIDED
    }

    public static final int SIZE = 3;
    public static final boolean X = true;

    public static final boolean O = false;;

    private char[][] grid;
    private boolean turn;
    private Status status;
    private List<TicTacToeView> views;

    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;
        views = new ArrayList<TicTacToeView>();

    }

    public void addTicTacToeView(TicTacToeView view) {
        views.add(view);
    }

    public void removeTicTacToeView(TicTacToeView view) {
        views.remove(view);
    }

    public Status getStatus() {
        return status;
    }

    public void updateStatus() {
        // Update status based on status of current board
        // X wins -> X_WON
        // O wins -> O_won
        // draw -> TIE
        // else UNDECIDED

        // check row
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X') {
                status = Status.X_WON;
            }
            if (grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O') {
                status = Status.O_WON;
            }
        }

        // check col
        for (int j = 0; j < SIZE; j++) {
            if (grid[0][j] == 'X' && grid[1][j] == 'X' && grid[2][j] == 'X') {
                status = Status.X_WON;
            }
            if (grid[0][j] == 'O' && grid[1][j] == 'O' && grid[2][j] == 'O') {
                status = Status.O_WON;
            }
        }

        // check diag
        if (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') {
            status = Status.X_WON;
        }
        if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {
            status = Status.O_WON;
        }
        if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X') {
            status = Status.X_WON;
        }
        if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
            status = Status.O_WON;
        }

        // draw?
        // if board is full and no one has won, it's a draw
        boolean draw = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == ' ') {
                    draw = false;
                }
            }
        }
        if (draw) {
            status = Status.TIE;
        }

    }

    public boolean getTurn() {
        return turn;
    }

    public void play(int x, int y) {
        if (grid[x][y] != ' ')
            return;
        grid[x][y] = turn ? 'X' : 'O';
        updateStatus();
        for (TicTacToeView view : views) {
            view.update(new TicTacToeEvent(this, status, x, y));

        }
        changeTurn();
    }

    private void changeTurn() {
        turn = !turn;
    }

}

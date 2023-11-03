import java.util.*;

public class TicTacToeModel {


    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;
    private List<TicTacModelView> views;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

    private char[][] grid;
    private boolean turn;
    private Status status;


    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;
        this.views = new ArrayList<TicTacModelView>();

    }
    public void addTicTacToeView(TicTacModelView view){
        this.views.add(view);
    }

    public void removeTicTacToeView(TicTacModelView view){
        this.views.remove(view);
    }

    private void changeTurn() {

        turn = !turn;
    }

    public Status getStatus() {
        return status;}

    private void updateStatus() {
        return; //TODO
    }

    public boolean getTurn() {
        return turn;
    }

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        updateStatus();
        for (TicTacModelView view: views){
            view.handleTicTacToeStatusUpdate(new TicTacToeEvent(this,status, x, y));
        }
        changeTurn();
    }
}


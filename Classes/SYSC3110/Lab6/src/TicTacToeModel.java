import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    public static int SIZE = 3;
    public static boolean X = true;
    public static boolean O = false;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

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
        views = new ArrayList<>();
    }

    private void changeTurn() {
        turn = !turn;
    }

    public void addTicTacToeView(TicTacToeView view){
        views.add(view);
    }

    public void removeTicTacToeView(TicTacToeView view){
        views.remove(view);
    }

    public Status getStatus() {return status;}

    public void updateStatus() {

        //Check Row
        for(int i = 0; i < SIZE; i++){
            if((grid[i][0] == 'X') && (grid[i][1] == 'X') && (grid[i][2] == 'X')){
                status = Status.X_WON;
            }

            if((grid[i][0] == 'O') && (grid[i][1] == 'O') && (grid[i][2] == 'O')){
                status = Status.O_WON;
            }
        }

        //Check Column
        for(int j = 0; j < SIZE; j++) {
            if((grid[0][j] == 'X') && (grid[1][j] == 'X') && (grid[2][j] == 'X')){
                status = Status.X_WON;
            }
            if((grid[0][j] == 'O') && (grid[1][j] == 'O') && (grid[2][j] == 'O')){
                status = Status.O_WON;
            }
        }

        //Check Diagonals
        if((grid[0][0] == 'X') && (grid[1][1] == 'X') && (grid[2][2] == 'X')){
            status = Status.X_WON;
        }
        if((grid[0][0] == 'O') && (grid[1][1] == 'O') && (grid[2][2] == 'O')){
            status = Status.O_WON;
        }
        if((grid[0][2] == 'X') && (grid[1][1] == 'X') && (grid[2][0] == 'X')){
            status = Status.X_WON;
        }
        if((grid[0][2] == 'O') && (grid[1][1] == 'O') && (grid[2][0] == 'O')){
            status = Status.O_WON;
        }

        //Check Draw
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                //status = Status.TIE;
            }
        }
    }

    public boolean getTurn() {return turn;}

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        updateStatus();
        for(TicTacToeView view : views){
            view.handleTicTacToeStatusUpdate(this, status, x, y);
        }
        changeTurn();
    }
}


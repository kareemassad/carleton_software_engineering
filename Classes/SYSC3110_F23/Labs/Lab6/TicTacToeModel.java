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
        //check row win
        for (int i = 0; i < SIZE; i++) {
            if(grid[i][0]=='X' && grid[i][1]=='X' && grid[i][2] == 'X'){
                status = Status.X_WON;
            }

            if(grid[i][0]=='O' && grid[i][1]=='O' && grid[i][2] == 'O'){
                status = Status.O_WON;
            }

        }
        //check col win
        for (int j = 0; j < SIZE; j++) {
            if(grid[0][j]=='X' && grid[1][j]=='X' && grid[2][j] == 'X'){
                status = Status.X_WON;
            }

            if(grid[0][j]=='O' && grid[1][j]=='O' && grid[2][j] == 'O'){
                status = Status.O_WON;
            }
        }

        //check diag win
        if(grid[0][0]=='X' && grid[1][1]=='X' && grid[2][2] == 'X'){
            status = Status.X_WON;
        }
        if(grid[0][0]=='O' && grid[1][1]=='O' && grid[2][2] == 'O'){
            status = Status.O_WON;
        }
        if(grid[0][2]=='X' && grid[1][1]=='X' && grid[2][0] == 'X'){
            status = Status.X_WON;
        }
        if(grid[0][2]=='O' && grid[1][1]=='O' && grid[2][0] == 'O'){
            status = Status.O_WON;
        }

        //check tie
        int count = 0;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++) {
                if(grid[i][j] == ' '){
                    //count empty tiles
                    count++;
                }
            }
        }
        //if no empty tiles
        if(count == 0){
            status = Status.TIE;
        }
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


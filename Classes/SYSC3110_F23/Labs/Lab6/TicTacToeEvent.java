import java.util.EventObject;

public class TicTacToeEvent extends EventObject {
    TicTacToeModel.Status status;
    int x;
    int y;

    public TicTacToeEvent(TicTacToeModel model, TicTacToeModel.Status status, int x, int y){
        super(model);
        this.status = TicTacToeModel.Status.UNDECIDED;
        this.x = x;
        this.y = y;
    }

    public int getX(){ return x;}
    public int getY(){ return y;}

}

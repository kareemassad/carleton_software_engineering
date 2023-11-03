import javax.swing.*;
import java.awt.*;

public class TicTacModelViewFrame extends JFrame implements TicTacModelView {

    JButton[][] buttons;
    TicTacToeModel model;

    public TicTacModelViewFrame(){
        super("Tic Toc Toe!");
        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));
        model = new TicTacToeModel();
        model.addTicTacToeView(this);
        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,300);
        TicTacToeController  tttc = new TicTacToeController(model) ;
        for(int i = 0; i < TicTacToeModel.SIZE; i++){
            for (int j = 0; j < TicTacToeModel.SIZE; j++){
                JButton button = new JButton(" ");
                button.setActionCommand(i + " " + j);
                button.addActionListener(tttc);
                this.buttons[i][j] = button;
                this.add(button);
            }
        }

        this.setVisible(true);
    }

    public void handleTicTacToeStatusUpdate(TicTacToeEvent e){
        String label = model.getTurn()? "X":"O";
        this.buttons[e.getX()][e.getY()].setText(label);
    }

    public static void main(String[] args) {
        new TicTacModelViewFrame();
    }
}

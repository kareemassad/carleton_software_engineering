import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame implements TicTacToeView{

    private JButton[][] buttons;

    public TicTacToeFrame(){
        super("Tic Tac Toe!");

        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        TicTacToeModel model = new TicTacToeModel();

        model.addTicTacToeView(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,300);

        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];

        TicTacToeController tttc = new TicTacToeController(model);

        for (int i = 0; i < TicTacToeModel.SIZE; i++) {
            for (int j = 0; j < TicTacToeModel.SIZE; j++) {
                JButton button = new JButton(" ");
                button.setActionCommand(i + " " + j);
                buttons[i][j] = button;
                model.updateStatus();
                button.addActionListener(tttc);
                this.add(button);
            }
        }



        this.setVisible(true);
    }

    @Override
    public void handleTicTacToeStatusUpdate(TicTacToeModel ticTacToeModel, TicTacToeModel.Status status, int x, int y) {
        String label = ticTacToeModel.getTurn() ? "X" : "O";
        buttons[x][y].setText(label);
        if(status == TicTacToeModel.Status.X_WON){JOptionPane.showMessageDialog(null, "X Wins");}
        if(status == TicTacToeModel.Status.O_WON){JOptionPane.showMessageDialog(null, "O Wins");}

    }

    public static void main(String[] args){
        new TicTacToeFrame();
    }
}

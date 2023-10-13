import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame implements TicTacToeView{

    private JButton[][] buttons;

    public TicTacToeFrame(){
        super("Tic Tac Toe!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        TicTacToeModel model = new TicTacToeModel();

        model.addTicTacToeView(this);

        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];

        TicTacToeController tttc = new TicTacToeController(model);

        for (int i = 0; i < TicTacToeModel.SIZE; i++) {
            for (int j = 0; j < TicTacToeModel.SIZE; j++) {
                JButton b = new JButton(" ");
                b.setActionCommand(i + " " + j);
                buttons[i][j] = b;
                int x = i;
                int y = j;
                //b.addActionListener(e -> model.play(x,y));
                b.addActionListener(tttc);
                this.add(b);
            }
        }

        this.setSize(300,300);
        this.setVisible(true);
    }


    @Override

    public void update(TicTacToeEvent event) {
        String label = event.isTurn();
        buttons[event.getX()][event.getY()].setText(label);
    }

    public static void main(String[] args){
        TicTacToeFrame frame = new TicTacToeFrame();
    }
}

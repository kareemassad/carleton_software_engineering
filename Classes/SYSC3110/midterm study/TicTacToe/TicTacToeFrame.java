import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.*;

public class TicTacToeFrame extends JFrame implements TicTacToeView {

    private JButton[][] buttons;
    private JTextField inputBox;

    public TicTacToeFrame() {
        super("Tic Tac Toe!");

        // Container contentPane = getContentPane();
        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        // JPanel panel = new JPanel();
        inputBox = new JTextField(0);
        inputBox.setEditable(true);
        inputBox.setVisible(true);

        // panel.add(inputBox);
        // this.add(panel);

        TicTacToeModel model = new TicTacToeModel();

        model.addTicTacToeView(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);

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

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(inputBox, BorderLayout.SOUTH);

        // contentPane.add(mainPanel);

        // add jtextfield as a new panel at the bottom of the frame to receive user
        // input
        // get user input when they hit enter
        // use the input to update the model

        this.setVisible(true);
    }

    @Override
    public void update(TicTacToeEvent event) {
        TicTacToeModel ticTacToeModel = (TicTacToeModel) event.getSource();
        String label = ticTacToeModel.getTurn() ? "X" : "O";
        buttons[event.getX()][event.getY()].setText(label);
        if (ticTacToeModel.getStatus() == TicTacToeModel.Status.X_WON) {
            JOptionPane.showMessageDialog(null, "X wins");
        }
        if (ticTacToeModel.getStatus() == TicTacToeModel.Status.O_WON) {
            JOptionPane.showMessageDialog(null, "O wins");
        }

    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }

}

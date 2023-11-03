import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {
    TicTacToeModel model;
    public TicTacToeController(TicTacToeModel model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] position = e.getActionCommand().split(" ");
        model.play(Integer.parseInt(position[0]), Integer.parseInt(position[1]));

    }
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {

    TicTacToeModel model;

    public TicTacToeController(TicTacToeModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] coordinates = e.getActionCommand().split(" ");
        model.play(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }
}

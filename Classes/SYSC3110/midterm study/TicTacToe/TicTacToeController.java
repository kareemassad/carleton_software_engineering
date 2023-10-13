import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {
    TicTacToeModel model;

    public TicTacToeController(TicTacToeModel model) {
        this.model = model;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // extracting from "x y"
        String[] coords = e.getActionCommand().split(" ");
        model.play(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
    }

}

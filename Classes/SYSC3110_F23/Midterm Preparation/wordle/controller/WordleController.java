import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WordleController {
    private Wordle model;
    private WordleView view;
    private int currentAttempt;

    public WordleController() throws IOException {
        model = new Wordle();
        view = new WordleView();
        view.setGuessButtonListener(new GuessButtonListener());
        view.setVisible(true);
        currentAttempt = 0;
    }

    class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(currentAttempt < 6){
                String guess = view.getGuess();
                if(guess.length() != model.getSecretWord().length()){
                    view.updateMessage("Guess must be " + model.getSecretWord().length() + " characters long!");
                    return;
                }
                String[] feedback = model.checkGuess(guess);
                view.updateLetterButtons(guess, feedback, currentAttempt);
                currentAttempt++;
                view.clearGuess();

                if(model.isGuessCorrect(guess)){
                    view.updateMessage("Correct! The word was " + model.getSecretWord() + ".");
                    view.disableInput();
                } else if (model.isGameOver()){
                    view.updateMessage("Game Over! The word was " + model.getSecretWord() + ".");
                } else {
                    view.updateMessage("Try again!");
                }
                view.clearGuess();
            }
            }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                try {
                    new WordleController();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}

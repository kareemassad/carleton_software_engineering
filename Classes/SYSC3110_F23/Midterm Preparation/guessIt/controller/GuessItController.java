import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuessItController {
    private GuessIt model;
    private GuessItView view;
    private int currentAttempt;
    private boolean flagWordSet;

    public GuessItController() throws IOException {
        model = new GuessIt();
        view = new GuessItView();
        view.setGuessButtonListener(new GuessButtonListener());
        view.setVisible(true);
        currentAttempt = 0;
        flagWordSet = false;
    }

    class GuessButtonListener implements ActionListener {
//        private void setSecretWord() {
//            String secretWord = JOptionPane.showInputDialog(view,
//                    "Enter a word to be guessed. Word must be 5 characters long");
//            if (secretWord.length() != model.getSecretWord().length()) {
//                // pop up a message dialog
//                JOptionPane.showMessageDialog(view,
//                        "Word must be " + model.getSecretWord().length() + " characters long!");
//                // view.updateMessage("Guess must be " + model.getSecretWord().length() + "
//                // characters long!");
//                return;
//            }
//            model.setSecretWord(secretWord);
//            flagWordSet = true;
//        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            if (!flagWordSet) {
//                setSecretWord();
//            }
            if (currentAttempt < GuessIt.MAX_ATTEMPTS) {
                String guess = view.getGuess();
                if (guess.length() != model.getSecretWord().length()) {
                    // pop up a message dialog
                    JOptionPane.showMessageDialog(view,
                            "Guess must be " + model.getSecretWord().length() + " characters long!");
                    // view.updateMessage("Guess must be " + model.getSecretWord().length() + "
                    // characters long!");
                    return;
                }

                String[] feedback = model.checkGuess(guess);
                view.updateLetterButtons(guess, feedback, currentAttempt);
                view.updateFloatingListButtons(model.getFloatingList());
                currentAttempt++;
                view.clearGuess();

                if (model.isGuessCorrect(guess)) {
                    JOptionPane.showMessageDialog(view, "Correct! The word was " + model.getSecretWord() + ".");
                    // view.updateMessage("Correct! The word was " + model.getSecretWord() + ".");
                    view.disableInput();
                } else if (model.isGameOver()) {
                    JOptionPane.showMessageDialog(view,
                            "Game Over! The word was " + model.getSecretWord() + ".");
                    // view.updateMessage("Game Over! The word was " + model.getSecretWord() + ".");
                } // else {

                // JOptionPane.showMessageDialog(view, "Try again!");
                // view.updateMessage("Try again!");
                // }
                view.clearGuess();
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GuessItController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

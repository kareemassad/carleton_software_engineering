import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class GuessItView extends JFrame {
    private final int wordLength = 5;
    private final int maxAttempts = 5;
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel messageLabel;
    private JButton[][] letterButtons;
    private JButton[] correctGuessPanel;

    public GuessItView() {
        initializeComponents();
    }

    private void initializeComponents() {
        guessInput = new JTextField(5);
        guessButton = new JButton("Guess");
        // messageLabel = new JLabel("Enter a word and press Guess.");
        letterButtons = new JButton[maxAttempts][wordLength];
        correctGuessPanel = new JButton[wordLength];
        setLayout(new BorderLayout());
        // add(messageLabel, BorderLayout.NORTH);

        JPanel correctGuessesPanel = new JPanel(new GridLayout(1, wordLength, 2, 2));
        for (int i = 0; i < wordLength; i++) {
            JButton correctGuessesButton = new JButton();
            correctGuessesButton.setEnabled(false);
            correctGuessesPanel.add(correctGuessesButton);
            correctGuessPanel[i] = correctGuessesButton;
        }

        add(correctGuessesPanel, BorderLayout.NORTH);

        JPanel letterPanel = new JPanel(new GridLayout(maxAttempts, wordLength, 2, 2));

        for (int i = 0; i < maxAttempts; i++) {
            for (int j = 0; j < wordLength; j++) {
                JButton letterButton = new JButton();
                letterButton.setEnabled(false);
                letterPanel.add(letterButton);
                letterButtons[i][j] = letterButton;
            }

        }

        add(letterPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(guessInput);
        inputPanel.add(guessButton);
        add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public String getGuess() {
        return guessInput.getText().toLowerCase();
    }

    public void clearGuess() {
        guessInput.setText("");
    }

    public void setGuessButtonListener(ActionListener actionListener) {
        guessButton.addActionListener(actionListener);
    }

    public void updateMessage(String message) {
        messageLabel.setText(message);
    }

    public void updateLetterButtons(String guess, String[] feedback, int attempt) {
        for (int i = 0; i < wordLength; i++) {
            JButton letterButton = letterButtons[attempt][i];
            letterButton.setText(String.valueOf(guess.charAt(i)).toUpperCase());
            letterButton.setBackground(getColor(feedback[i]));
            letterButton.setOpaque(true);
            letterButton.setBorderPainted(false);
        }
    }

    public void updateFloatingListButtons(String[] floatingList) {
        for (int i = 0; i < wordLength; i++) {
            // update the buttons meant to keep track of the correct guesses,
            // correctGuessesButton and correctGuessesPanel
            if (!Objects.equals(floatingList[i], "")) {
                JButton correctGuessesButton = correctGuessPanel[i];
                correctGuessesButton.setText(floatingList[i].toUpperCase());
                correctGuessesButton.setBackground(Color.GREEN);
                correctGuessesButton.setOpaque(true);
                correctGuessesButton.setBorderPainted(false);
            }

        }
    }

    private Color getColor(String color) {
        switch (color) {
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            default:
                return Color.LIGHT_GRAY;
        }
    }

    public void disableInput() {
        guessInput.setEnabled(false);
        guessButton.setEnabled(false);
    }

}

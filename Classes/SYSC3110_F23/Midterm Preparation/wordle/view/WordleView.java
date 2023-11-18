import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WordleView extends JFrame {
    private final int wordLength =5;
    private  final int maxAttempts = 6;
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel messageLabel;
    private JButton[][] letterButtons;

    public WordleView(){
        initializeComponents();
    }

    private void initializeComponents() {
        guessInput = new JTextField(5);
        guessButton = new JButton("Guess");
        messageLabel = new JLabel("Enter a word and press Guess.");
        letterButtons = new JButton[maxAttempts][wordLength];

        setLayout(new BorderLayout());
        add(messageLabel, BorderLayout.NORTH);

        JPanel letterPanel = new JPanel(new GridLayout(maxAttempts, wordLength, 2, 2));

        for (int i = 0; i < maxAttempts ; i++) {
            for(int j =0; j<wordLength; j++){
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

    public String getGuess(){
        return guessInput.getText().toLowerCase();
    }

    public void clearGuess(){
        guessInput.setText("");
    }

    public void setGuessButtonListener(ActionListener actionListener){
        guessButton.addActionListener(actionListener);
    }

    public void updateMessage(String message) {
        messageLabel.setText(message);
    }

    public void updateLetterButtons(String guess, String[] feedback, int attempt){
        for(int i =0; i < wordLength; i++){
            JButton letterButton = letterButtons[attempt][i];
            letterButton.setText(String.valueOf(guess.charAt(i)).toUpperCase());
            letterButton.setBackground(getColor(feedback[i]));
            letterButton.setOpaque(true);
            letterButton.setBorderPainted(false);
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
    public void disableInput(){
        guessInput.setEnabled(false);
        guessButton.setEnabled(false);
    }

}

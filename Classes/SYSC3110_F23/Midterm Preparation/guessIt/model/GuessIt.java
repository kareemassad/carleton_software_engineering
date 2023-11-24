import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GuessIt {
    private String secretWord;
    private int attempts;
    public static final int MAX_ATTEMPTS = 5;
    private List<String> wordList;
    private String[] floatingList;
    private int WORD_LENGTH = 5;

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public GuessIt() throws IOException {
        wordList = Files.readAllLines(Paths.get("words.txt"));
        secretWord = wordList.get(new Random().nextInt(wordList.size()));
        attempts = 0;
        // floatingList = new ArrayList<String>(secretWord.length());
        floatingList = new String[WORD_LENGTH];
        fillListWithEmptyStrings(floatingList);
    }

    private void fillListWithEmptyStrings(String[] floatingList2) {
        for (int i = 0; i < WORD_LENGTH; i++) {
            floatingList[i] = "";
        }
    }

    public String[] checkGuess(String guess) {
        attempts++;
        String[] feedback = new String[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            char c = guess.charAt(i);
            if (c == secretWord.charAt(i)) {
                feedback[i] = "green";
                replaceInFloatingList(String.valueOf(secretWord.charAt(i)), i);
            } else if (secretWord.indexOf(c) >= 0) {
                feedback[i] = "yellow";
            } else {
                feedback[i] = "gray";
            }
        }
        return feedback;
    }

    private void replaceInFloatingList(String c, int index) {
        floatingList[index] = c;
        System.out.println("Added to floatList " + Arrays.toString(floatingList));
    }

    public boolean isGameOver() {
        return attempts >= MAX_ATTEMPTS;
    }

    public boolean isGuessCorrect(String guess) {
        return guess.equalsIgnoreCase(secretWord);
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String newSecretWord) {
        this.secretWord = newSecretWord;
    }

    public String[] getFloatingList() {
        return floatingList;
    }
}
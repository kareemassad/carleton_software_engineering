import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Wordle {
    private String secretWord;
    private int attempts;
    public static final int MAX_ATTEMPTS = 6;
    private List<String> wordList;

    public Wordle() throws IOException {
        wordList = Files.readAllLines(Paths.get("words.txt"));
        secretWord = wordList.get(new Random().nextInt(wordList.size()));
        attempts=0;
    }

    public String[] checkGuess(String guess){
        attempts++;
        String[] feedback = new String[secretWord.length()];
        for(int i=0; i<secretWord.length(); i++){
            char c = guess.charAt(i);
            if(c == secretWord.charAt(i)){
                feedback[i] = "green";
            } else if (secretWord.indexOf(c) >= 0) {
                feedback[i] = "yellow";
            } else {
                feedback[i] = "gray";
            }
        }
        return feedback;
    }

    public boolean isGameOver(){
        return attempts>=MAX_ATTEMPTS;
    }

    public boolean isGuessCorrect(String guess){
        return guess.equalsIgnoreCase(secretWord);
    }

    public String getSecretWord(){
        return secretWord;
    }

    public void setSecretWord(String newSecretWord) {
        this.secretWord = newSecretWord;
    }
}
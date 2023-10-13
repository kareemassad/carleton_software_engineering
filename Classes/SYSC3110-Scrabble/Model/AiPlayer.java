package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class AiPlayer extends Player implements Serializable {
    private ScrabbleGame game;
    private Word playedWord;
    private Word bestWord;
    private int placementDirection;

    public AiPlayer(int player_ID) {
        super(player_ID);

    }

    public void playLogic() {
        // get the board
        String[][] board = ScrabbleGame.getBoard();

        // This works by assumming that the last word played has no neighbors for the
        // last few characters

        // get last played word
        Word lastWord = game.getLastWordPlayed();

        // check if the last word played was vertical or horizontal
        // 0 = horizontal, 1= vertical
        if (lastWord.getDirection() == 0) {
            // if the last word played was horizontal, then the AI will attempt to play a
            // vertical word
            placementDirection = 1;
            playedWord = getBestWord();
        } else {
            // if the last word played was vertical, then the AI will attempt to play a
            // horizontal word
            placementDirection = 0;
            playedWord = getBestWord();
        }

        // get the position of the last character of the last word played
        ArrayList<Letter> lastWordLetters = lastWord.getLetters();
        int lastWordSize = lastWordLetters.size();
        // get the last letter of the last word played, ex: "cat" -> "t"
        Letter lastLetter = lastWordLetters.get(lastWordSize - 1);
        // get the position of the last letter of the last word played
        int lastLetterRow = lastLetter.getRow();
        int lastLetterCol = lastLetter.getCol();

        // play the word
        game.placeWord(playedWord.toString(), lastLetterRow, lastLetterCol, placementDirection);

    }

    private Word getBestWord() {
        // this method must return a word to play on the board
        // Must use a character from the last word played and the letters in the hand

        // get last played word
        Word lastWord = game.getLastWordPlayed();

        // get the last character of the last word played
        String lastWordLastChar = lastWord.toString().substring(lastWord.toString().length() - 1);

        // get the player hand
        ArrayList<Letter> hand = this.getHandLetters();
        // get legal words from Game
        HashSet<String> legalWords = game.getLegalWords();

        // word selected to play must be in the legal words list, and must contain the
        // last character of the last word played, and must made with the letters in the
        // hand
        // create a new list of words that meet the criteria
        ArrayList<Word> possibleWords = new ArrayList<Word>();
        for (String word : legalWords) {
            if (word.contains(lastWordLastChar)) {
                for (Letter letter : hand) {
                    if (word.contains(letter.toString())) {
                        possibleWords.add(new Word(word));
                    }
                }
            }
        }

        // select the word with the highest score
        int highestScore = 0;
        for (Word word : possibleWords) {
            if (word.getScore() > highestScore) {
                highestScore = word.getScore();
                bestWord = word;
            }
        }
        return bestWord;

    }

}

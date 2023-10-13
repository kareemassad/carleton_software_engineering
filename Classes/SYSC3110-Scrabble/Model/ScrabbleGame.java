package Model;

import View.ScrabbleFrame;
import View.ScrabbleView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Controller.SaveGameState;

/**
 * The main class of the game. This class is the model responsible to use the
 * other
 * classes to play the game.
 *
 * @author Kareem EL Assad 101107739 & Laurence Lamarche-Cliche 101173070
 * @version 3.0
 */

public class ScrabbleGame implements Serializable {
    public static final int SIZE = 15;
    public static ArrayList<Player> players;
    private int currentPlayerIndex;
    private List<ScrabbleView> views;
    private static String[][] board;
    private static int turn;
    private Status status;

    private HashSet<String> legalWords;
    ArrayList<Letter> lettersPlayed;

    public enum Status {
        PLACING_TILES, DONE, PASS, EXCHANGE
    };

    public enum Direction {
        RIGHT, DOWN
    };

    public ScrabbleGame() {
        HashSet<String> legalWords = new HashSet<String>();
        Bag gameBag = new Bag();
        board = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = " "; // fix that
            }
        }
        this.players = new ArrayList<Player>();
        currentPlayerIndex = 0;
        views = new ArrayList<>();

    }

    public void serialize(String fileName) {
        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGameContents(ScrabbleGame game) {
        this.board = game.board;
        this.players = game.players;
        this.currentPlayerIndex = game.currentPlayerIndex;
        this.views = game.views;
        this.legalWords = game.legalWords;
        this.lettersPlayed = game.lettersPlayed;
        this.turn = game.turn;
        this.status = game.status;
    }

    public void serializedSetGameContents(SaveGameState game) {
        // TODO FOR BECCA, INTEGRATE SAVEGAMESTATE INTO SCRABBLEGAME
        // NOTE TO TA, Becca Dropped the class so this is not implemented
    }

    // create a method to parse english_words.txt and store it in a set
    // return the set
    public HashSet<String> getLegalWords() {
        String filename = "Words/english_words.txt";
        Scanner scanner = new Scanner(filename);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            legalWords.add(line);
        }
        scanner.close();
        return legalWords;
    }

    public ScrabbleGame getGame() {
        return this;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayers(int selectionCode) {

        int numPlayers;
        if (selectionCode == 3) {
            numPlayers = 1;
        } else if (selectionCode == 2) {
            numPlayers = 2;
        } else if (selectionCode == 1) {
            numPlayers = 3;
        } else {
            numPlayers = 4;
        }

        // if selection is 1, get the AI player

        for (int i = 0; i < numPlayers; i++) {
            this.players.add(new Player(i));
        } // first player will always have index 0, the other ones, 1, 2 and 3 (if we have
          // that many players)
    }

    public void addBoardView(ScrabbleView v) {
        views.add(v);
    }

    public void changeTurn(int currentPlayerIndex) {
        if (currentPlayerIndex == (players.size() - 1)) {
            this.currentPlayerIndex = 0; // start back at the start
        } else {
            this.currentPlayerIndex++;
        }
        status = Status.PLACING_TILES; // the only possibility for now
        int missingLetters = 7 - getCurrentPlayer().getHandLetters().size();
        getCurrentPlayer().addToHand(missingLetters);
    }

    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return this.players.get(currentPlayerIndex);
    }

    public int getPlayerCount() {
        return this.players.size();
    }

    public static String[][] getBoard() {
        return board;
    }

    public static Letter getLetter(int row, int col) {
        if (board[row][col] == " ") {
            return new Letter(Letter.Character.NONE);
        } else {
            Letter newLetter = new Letter(board[row][col]); // using the string constructor
            newLetter.setCoordinates(row, col);
            return newLetter;
        }
    }

    public void updateStatus(Status status) {

        if ((status == Status.DONE) || (status == Status.PASS)) {
            changeTurn(currentPlayerIndex);
            // getCurrentPlayer().
        }
        if (status == Status.EXCHANGE) {
            getCurrentPlayer().exchangeTiles();
            changeTurn(currentPlayerIndex);
        }

        for (ScrabbleView v : views) {
            v.updateView(new ScrabbleEvent(this, "NONE", -1, -1, currentPlayerIndex));
            // we don't want to place any letter now
        }
    }

    public void placeLetter(String letterToPlace, int row, int col) {
        if (board[row][col] != " ")
            return;
        board[row][col] = letterToPlace;
        getCurrentPlayer().removeFromHand(letterToPlace);

        for (ScrabbleView v : views) {
            v.updateView(new ScrabbleEvent(this, letterToPlace, row, col, currentPlayerIndex));
        }
    }

    // this is to be called by the AI player :)
    public void placeWord(String word, int startingRow, int startingCol, int direction) {
        // this assumes that the word will not go out of bounds!
        for (int i = 0; i < word.length(); i++) {
            if (direction == 0) { // word is going right,
                placeLetter(String.valueOf(word.charAt(i)), startingRow, startingCol + i);
            } else { // direction is down
                placeLetter(String.valueOf(word.charAt(i)), startingRow + i, startingCol);
            }
        }
    }

    public ArrayList<Word> getHorizontalWords() {
        ArrayList<Word> horizontalWords = new ArrayList<Word>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != " ") {
                    Word newWord = new Word();
                    newWord.appendLetterToWord(getLetter(i, j));
                    int k = j + 1;
                    while ((k < SIZE) && (board[i][k] != " ")) {
                        newWord.appendLetterToWord(getLetter(i, k));
                        k++;
                    }
                    if (newWord.getLength() > 1) {
                        horizontalWords.add(newWord);
                    }
                }
            }
        }
        return horizontalWords;
    }

    public ArrayList<Word> getVerticalWords() {
        ArrayList<Word> verticalWords = new ArrayList<Word>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != " ") {
                    Word newWord = new Word();
                    newWord.appendLetterToWord(getLetter(i, j));
                    int k = i + 1;
                    while ((k < SIZE) && (board[k][j] != " ")) {
                        newWord.appendLetterToWord(getLetter(k, j));
                        k++;
                    }
                    if (newWord.getLength() > 1) {
                        verticalWords.add(newWord);
                    }
                }
            }
        }
        return verticalWords;
    }

    public ArrayList<Word> getAllWords() {
        ArrayList<Word> allWords = new ArrayList<Word>();
        allWords.addAll(getHorizontalWords());
        allWords.addAll(getVerticalWords());
        return allWords;
    }

    public Word getLastWordPlayed() {
        ArrayList<Word> allWords = getAllWords();
        return allWords.get(allWords.size() - 1);
    }

    public Word getSecondLastWordPlayed() {
        ArrayList<Word> allWords = getAllWords();
        return allWords.get(allWords.size() - 2);
    }

    public Word getLastVerticalWordPlayed() {
        ArrayList<Word> verticalWords = getVerticalWords();
        return verticalWords.get(verticalWords.size() - 1);
    }

    public Word getLastHorizontalWordPlayed() {
        ArrayList<Word> horizontalWords = getHorizontalWords();
        return horizontalWords.get(horizontalWords.size() - 1);
    }

    public static int getNumPlayers() {
        return players.size();
    }

    public static int getTurn() {
        return turn;
    }
}
package Model;

import java.io.Serializable;
import java.util.EventObject;

/**
 * This is the Event Object class
 * 
 * @author Laurence Lamarche-Cliche 101173070
 * @version 1.0
 */

public class ScrabbleEvent extends EventObject implements Serializable {
    private int row;
    private int col;
    private int currentPlayerIndex;
    private String letterToPlace;

    public ScrabbleEvent(ScrabbleGame scrabbleGame, String letterToPlace, int row, int col, int currentPlayerIndex) {
        super(scrabbleGame);
        this.letterToPlace = letterToPlace;
        this.row = row;
        this.col = col;
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public String getLetterToPlace() {
        return this.letterToPlace;
    }
}
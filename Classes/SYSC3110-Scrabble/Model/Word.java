package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A word can have up to 7 letters
 * 
 * @author Laurence Lamarche-Cliche 101173070 & Becca Young 101183297
 * @version 3.0
 */

public class Word implements Serializable {
    private ArrayList<Letter> letters;
    private int score;
    private int startingRow;
    private int startingCol;
    private int direction;
    private boolean missingLetters;

    private final int RIGHT = 0;
    private final int DOWN = 1;
    private final int INVALID = -1;

    public Word(ArrayList<Letter> letters) {
        this.letters = letters;
        missingLetters = true;
        // setDirection(letters);
        setDirection(letters);
        sortLetters(letters);
        setStartingCoordinates(); // I don't think we need this anymore
        if (missingLetters) {
            addAdjoiningLetter();
        }
        this.letters = new ArrayList<Letter>(letters);
    }

    public Word() {
        // used strictly for making empty words
        this.letters = new ArrayList<Letter>();
    }

    public Word(String word) {
        // Used to convert strings to words
        this.letters = new ArrayList<Letter>();
        for (int i = 0; i < word.length(); i++) {
            this.letters.add(new Letter(word.charAt(i)));
        }
    }

    private void sortLetters(ArrayList<Letter> letters) {
        ArrayList<Letter> newLetters;
        if (this.direction == RIGHT) {
            Collections.sort(letters, Comparator.comparing(Letter::getCol));
        } else if (this.direction == DOWN) {
            Collections.sort(letters, Comparator.comparing(Letter::getRow));
        }
    }

    private void setDirection(ArrayList<Letter> letters) {
        boolean rowChanging = false;
        boolean colChanging = false;

        if (letters.size() == 0) {
            throw new IndexOutOfBoundsException("You must place at least one letter, the word cannot be empty");
        }
        if (letters.size() == 1) {
            if ((letters.get(0).getCol() == 14)) { // am on the right, don't check right!
                // if there is some letter to the left,
                if ((ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() - 1)).toString() != " ") {
                    direction = RIGHT; // need to add letter left!
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() - 1);
                    newLetter.setCoordinates(letters.get(0).getRow(), letters.get(0).getCol() - 1);
                    addLetter(0, newLetter); // add letter at beginning
                    missingLetters = false;
                } else {
                    direction = DOWN;
                    if ((letters.get(0).getRow() == 0) ||
                            ((ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol()))
                                    .toString() != " ")) { // don't check up, the letter is down!
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        addLetter(1, newLetter); // add the letter at the end
                        missingLetters = false;
                    } else { // safe to add up!
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        addLetter(0, newLetter); // add the letter at the beginning
                        missingLetters = false;
                    }
                }
            }

            else if ((letters.get(0).getCol() == 0)) { // am on the left, don't check left!
                if ((ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() + 1)).toString() != " ") {
                    direction = RIGHT; // need to add letter right
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() + 1);
                    newLetter.setCoordinates(letters.get(0).getRow(), letters.get(0).getCol() + 1);
                    addLetter(1, newLetter); // add letter at the end
                    missingLetters = false;
                } else {
                    direction = DOWN;
                    if ((letters.get(0).getRow() == 0)) { // don't check up, the letter is down!
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        addLetter(1, newLetter); // add the letter at the end
                        missingLetters = false;
                    } else if ((letters.get(0).getRow() == 14)) { // don't check down, the letter is up!
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        addLetter(0, newLetter); // add the letter at the beginning
                        missingLetters = false;
                    } else if ((ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol()))
                            .toString() != " ") {
                        // the letter is down
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        addLetter(1, newLetter); // add the letter at the end
                        missingLetters = false;
                    } else { // safe to add up!
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        addLetter(0, newLetter); // add the letter at the beginning
                        missingLetters = false;
                    }
                }
            }

            else { // safe to check left or right, we are in columns 1 to 13. Start with right
                if ((ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() + 1)).toString() != " ") {
                    direction = RIGHT; // need to add letter right
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() + 1);
                    newLetter.setCoordinates(letters.get(0).getRow(), letters.get(0).getCol() + 1);
                    addLetter(1, newLetter); // add letter at the end
                    missingLetters = false;
                } else if ((ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() - 1))
                        .toString() != " ") {
                    direction = RIGHT; // need to add letter left
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow(), letters.get(0).getCol() - 1);
                    newLetter.setCoordinates(letters.get(0).getRow(), letters.get(0).getCol() - 1);
                    addLetter(0, newLetter); // add letter at the beginning
                    missingLetters = false;
                } else {
                    direction = DOWN;
                    if (letters.get(0).getRow() == 0) { // don't check up, the letter is down!
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        addLetter(1, newLetter); // add the letter at the end
                        missingLetters = false;
                    } else if (letters.get(0).getRow() == 14) { // don't check down, the letter is up
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        addLetter(0, newLetter); // add the letter at the beginning
                        missingLetters = false;
                    } else if ((ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol()))
                            .toString() != " ") { // letter is up
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                        addLetter(0, newLetter); // add the letter at the beginning
                        missingLetters = false;
                    } else { // safe to add down
                        Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        newLetter.setCoordinates(letters.get(0).getRow() + 1, letters.get(0).getCol());
                        addLetter(1, newLetter); // add the letter at the end
                        missingLetters = false;
                    }
                }
            }
        }

        else { // assuming size is at least 2
            for (int i = 0; i < 1; i++) {
                if (letters.get(i).getRow() != letters.get(i + 1).getRow()) {
                    rowChanging = true;
                }
                if (letters.get(i).getCol() != letters.get(i + 1).getCol()) {
                    colChanging = true;
                }
                if (rowChanging == colChanging) {
                    this.direction = INVALID; // invalid
                } else if (rowChanging) {
                    this.direction = DOWN;
                } else if (colChanging) {
                    this.direction = RIGHT;
                }
            }
        }
    }

    private ArrayList<Integer> checkForHole(int direction) {
        boolean isHole = false;
        ArrayList<Integer> holeToFill = new ArrayList<Integer>();
        if (direction == 0) {
            for (int i = 0; i < letters.size() - 1; i++) {
                if (letters.get(i).getCol() != letters.get(i + 1).getCol() + 1) {
                    holeToFill.add(0, i + 1); // the index in the WORD where a letter is missing
                    holeToFill.add(1, letters.get(i).getRow()); // the row with the letters
                    holeToFill.add(2, letters.get(i).getCol() + 1); // the column where there is a hole
                    // H A *B* S - B is not in word.
                    // H(0).getCol = 0, A(1).getCol = 1, S(2).getCol = 3
                    // I shall add at index 2
                }
            }
        } else { // direction is down
            for (int i = 0; i < letters.size() - 1; i++) {
                if (letters.get(i).getRow() != letters.get(i + 1).getRow() + 1) {
                    holeToFill.add(0, i + 1); // the index in the WORD where a letter is missing
                    holeToFill.add(1, letters.get(i).getRow() + 1); // the row where there is a hole
                    holeToFill.add(2, letters.get(i).getCol()); // the column with all the other letters
                }
            }
        }
        return holeToFill; // TODO verify that this is size 0 if it does not enter the if statements
    }

    // This method should only be called if the letter has not been added already by
    // the direction check :)
    // If a player placed more than one letter on the board, this function should be
    // called.
    public void addAdjoiningLetter() {
        ArrayList<Integer> holeToFill = checkForHole(direction); //
        if (holeToFill.size() > 0) { // there is a hole, fill it with the letter
            addLetter(holeToFill.get(0), ScrabbleGame.getLetter(holeToFill.get(1), holeToFill.get(2)));
            // add the letter (get it from the model) at the given index position
        } else { // there is no hole
            if (direction == RIGHT) { // need to add either right or left
                if (letters.get(0).getCol() == 0) {
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(letters.size() - 1).getRow(),
                            letters.get(letters.size() - 1).getCol() + 1); // add letter to the right of the last letter
                    newLetter.setCoordinates(letters.get(letters.size() - 1).getRow(),
                            letters.get(letters.size() - 1).getCol() + 1);
                    addLetter(letters.size(), newLetter); // add letter at the end
                } else if (letters.get(letters.size() - 1).getCol() == 14) { // we don't need to check right, add left
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow(),
                            letters.get(0).getCol() - 1); // add letter to the left of first letter
                    newLetter.setCoordinates(letters.get(0).getRow(), letters.get(0).getCol() - 1);
                    addLetter(0, newLetter); // add letter at beginning
                    this.startingCol = newLetter.getCol();
                } else if (ScrabbleGame.getLetter(letters.get(0).getRow() - 1, letters.get(0).getCol())
                        .toString() != " ") {
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow(),
                            letters.get(0).getCol() - 1); // add letter to the left of first letter
                    newLetter.setCoordinates(letters.get(0).getRow(), letters.get(0).getCol() - 1);
                    addLetter(0, newLetter); // add letter at beginning
                    this.startingCol = newLetter.getCol();
                } else { // add at the end
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(letters.size() - 1).getRow(),
                            letters.get(letters.size() - 1).getCol() + 1); // add letter to the right of the last letter
                    newLetter.setCoordinates(letters.get(letters.size() - 1).getRow(),
                            letters.get(letters.size() - 1).getCol() + 1);
                    addLetter(letters.size(), newLetter); // add letter at the end
                }
            } else { // direction is down
                if (startingRow == 0) { // add at the end for sure
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(letters.size() - 1).getRow() + 1,
                            letters.get(letters.size() - 1).getCol()); // add letter below the last letter
                    newLetter.setCoordinates(letters.get(letters.size() - 1).getRow() + 1,
                            letters.get(letters.size() - 1).getCol());
                    addLetter(letters.size(), newLetter); // add letter at the end
                } else if (letters.get(letters.size() - 1).getRow() == 14) { // add at beginning for sure
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1,
                            letters.get(0).getCol()); // add letter above the first letter
                    newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                    addLetter(0, newLetter); // add letter at beginning
                    this.startingRow = newLetter.getRow();
                }
                // check if anything down at the end
                else if (ScrabbleGame.getLetter(letters.get(letters.size() - 1).getRow() + 1, letters.get(0).getCol())
                        .toString() != " ") {
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(letters.size() - 1).getRow() + 1,
                            letters.get(letters.size() - 1).getCol()); // add letter below the last letter
                    newLetter.setCoordinates(letters.get(letters.size() - 1).getRow() + 1,
                            letters.get(letters.size() - 1).getCol());
                    addLetter(letters.size(), newLetter); // add letter at the end
                } else { // add at the beginning and update startingRow
                    Letter newLetter = ScrabbleGame.getLetter(letters.get(0).getRow() - 1,
                            letters.get(0).getCol()); // add letter above the first letter
                    newLetter.setCoordinates(letters.get(0).getRow() - 1, letters.get(0).getCol());
                    addLetter(0, newLetter); // add letter at beginning
                    this.startingRow = newLetter.getRow();
                }
            }
        }

    }

    /**
     * This method adds a given letter at a given position.
     * This can be used when a player adds a letter at the beginning or at the end
     * of an existing word
     */
    public void addLetter(int position, Letter letter) { // Can we take this out?
        this.letters.add(position, letter);
    }

    public void appendLetterToWord(Letter letter) {
        this.letters.add(letter);
    }

    public ArrayList<Letter> getLetters() {
        return this.letters;
    }

    public int getScore() {
        return score;
    }

    public void setStartingCoordinates() {
        this.startingRow = (letters.get(0)).getRow();
        this.startingCol = (letters.get(0)).getCol();
    }

    public int getStartingRow() {
        return this.startingRow;
    }

    public int getStartingCol() {
        return this.startingCol;
    }

    public int getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        StringBuilder word = null;
        word = new StringBuilder();
        for (Letter letter : this.letters) {
            word.append(letter.toString());
        }
        return word.toString();
    }

    public int getLength() {
        // returns length of Word
        return this.letters.size();
    }
}
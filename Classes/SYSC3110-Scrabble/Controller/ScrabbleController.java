package Controller;

import Model.*;
import Model.Legality2;
import Model.Score;
import View.ScrabbleFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScrabbleController implements ActionListener {

    private ScrabbleGame model;
    private ScrabbleFrame frame; // necessary so that I can get the source of the action events
    private Letter letterToPlace;
    private ArrayList<Letter> wordToScore;

    public ScrabbleController(ScrabbleGame model, ScrabbleFrame frame) {
        this.model = model;
        this.frame = frame;
        this.wordToScore = new ArrayList<Letter>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionCommandLength = e.getActionCommand().length();
        if (e.getActionCommand().length() == 1) { // A player clicked on a letter, save it here!
            // save the letter
            letterToPlace = new Letter(e.getActionCommand());
        } else {
            if (e.getActionCommand().length() == 3 && e.getActionCommand().length() <= 5) { // I have board coordinates,
                                                                                            // where to place the
                                                                                            // letter!
                String[] input = e.getActionCommand().split(" ");
                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);
                letterToPlace.setCoordinates(row, col);
                letterToPlace.setPremium("NONE"); // needs to be changed eventually for the premiums
                wordToScore.add(letterToPlace); // we add to the temporary arrayList of Letters
                model.placeLetter(letterToPlace.toString(), row, col);
            }
            if (e.getActionCommand() == "submit") { // a player is done placing its word! do the things!

                Word finalWord = new Word(wordToScore);
                // check legality
                Legality2 legal = new Legality2(finalWord, model);
                boolean legality = legal.checkLegality();

                if (legality) {
                    // score the word
                    String wordString = finalWord.toString(); // just testing for now
                    model.getCurrentPlayer().addScore(finalWord);
                    // update player's score
                    model.updateStatus(ScrabbleGame.Status.DONE); // we want to save the word and compute it
                    this.wordToScore = new ArrayList<>(); // clear the word so we don't keep adding to it
                    // delete the word from the controller's array. Will still be saved in the
                    // model.
                } else {
                    // letters back in hand
                }
            }
            if (e.getActionCommand() == "quitGame") {
                System.exit(0);
            } else if (e.getActionCommand() == "passTurn") {
                model.updateStatus(ScrabbleGame.Status.PASS); // we want to change the player's turn and move on
                // confirm this works
            } else if (e.getActionCommand() == "exchangeTiles") {
                model.updateStatus(ScrabbleGame.Status.EXCHANGE);
                // confirm this works
            }

        }
    }
}
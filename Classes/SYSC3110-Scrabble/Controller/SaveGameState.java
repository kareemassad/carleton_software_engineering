package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Model.Letter;
import Model.Player;
import Model.ScrabbleGame;

public class SaveGameState implements Serializable {

    private String[][] board;
    private int numPlayers;
    private ArrayList<Player> players;
    private int turn;

    public SaveGameState() {
        // TODO Auto-generated constructor stub
    }

    public int getPlayerScore(Player player) {
        return player.getScore();
    }

    public int getPlayerID(Player player) {
        return player.getID();
    }

    public String getPlayerHandString(Player player) {
        return player.getHandString();
    }

    public ArrayList<Letter> getPlayerHandLetters(Player player) {
        return player.getHandLetters();
    }

    // get the board in 2d array form
    public static String[][] getBoard() {
        return ScrabbleGame.getBoard();
    }

    // get number of players
    public static int getNumPlayers() {
        return ScrabbleGame.getNumPlayers();
    }

    // get turn
    public static int getTurn() {
        return ScrabbleGame.getTurn();
    }

    public Boolean saveGame() {
        // get the board
        String[][] board = getBoard();
        // get the number of players
        int numPlayers = getNumPlayers();
        // get player array
        ArrayList<Player> players = ScrabbleGame.getPlayers();
        // get the turn
        int turn = getTurn();

        // save all the data to a file

        try {
            // save game.ser to Saves/ folder
            FileOutputStream fileOut = new FileOutputStream("Saves/game.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(board);
            out.writeObject(numPlayers);
            out.writeObject(players);
            out.writeObject(turn);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in game.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            board = (String[][]) in.readObject();
            numPlayers = (int) in.readObject();
            players = (ArrayList<Player>) in.readObject();
            turn = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return false;
        }
        return true;
    }

    public void setTurn(int i) {
        turn = i;
    }

}

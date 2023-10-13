package View;

import Model.Player;
import Model.ScrabbleEvent;
import Model.ScrabbleGame;
import Controller.ScrabbleController;

import javax.swing.*;
import java.awt.*;

/**
 * Creates the board GUI
 * 
 * @author Keefer Belanger 101152085 & Laurence Lamarche-Cliche 101173070
 *         Version 3.0
 */

public class ScrabbleFrame extends JFrame implements ScrabbleView {

    // the panels
    public JPanel PlayerPanel;
    public JPanel currentPlayerHandPanel;
    public JTextArea currentPlayerText;
    public JPanel boardPanel;
    public JPanel scorePanel;
    public JTextArea scorePanelText;

    // The buttons
    public JButton[][] boardButtons;
    public JButton submitButton;
    public JButton passButton;
    public JButton exchangeTilesButton;

    private ScrabbleGame model;
    private ScrabbleController bc;

    public ScrabbleFrame() {
        super("Scrabble!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // set the ScrabbleGame model, and add this frame to the list of views
        model = new ScrabbleGame();
        model.addBoardView(this);
        bc = new ScrabbleController(model, this);

        // The current player's panel and options
        PlayerPanel = new JPanel(new GridLayout(12, 1));
        currentPlayerText = new JTextArea("Current player's rack will be displayed here");
        currentPlayerHandPanel = new JPanel(new GridLayout(1, 7));

        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(bc);

        passButton = new JButton("Pass");
        passButton.setActionCommand("passTurn");
        // passButton.setEnabled(false);
        passButton.addActionListener(bc);

        exchangeTilesButton = new JButton("Exchange tiles");
        exchangeTilesButton.setActionCommand("exchangeTiles");
        exchangeTilesButton.setEnabled(false);
        exchangeTilesButton.addActionListener(bc);

        PlayerPanel.add(currentPlayerText);
        PlayerPanel.add(currentPlayerHandPanel);
        PlayerPanel.add(submitButton);
        PlayerPanel.add(passButton);
        PlayerPanel.add(exchangeTilesButton);
        PlayerPanel.setPreferredSize(new Dimension(300, 30));

        // The score panel
        scorePanel = new JPanel(new BorderLayout());
        scorePanelText = new JTextArea("Score");
        scorePanel.add(scorePanelText);
        scorePanel.setPreferredSize(new Dimension(300, 0));

        // Menu initialization
        JMenuBar menu_bar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenu help = new JMenu("Help");

        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem redo = new JMenuItem("Redo");
        JMenuItem undo = new JMenuItem("Undo");

        this.setJMenuBar(menu_bar);
        menu_bar.add(options);
        menu_bar.add(help);
        options.add(save);
        options.add(load);
        options.add(undo);
        options.add(redo);
        options.add(quit);

        quit.addActionListener(bc);
        quit.setActionCommand("quitGame");

        // setting up the board and the buttons for the board
        boardPanel = new JPanel(new GridLayout(ScrabbleGame.SIZE, ScrabbleGame.SIZE));
        boardPanel.setSize(new Dimension(800, 800));
        boardButtons = new JButton[ScrabbleGame.SIZE][ScrabbleGame.SIZE];
        for (int i = 0; i < ScrabbleGame.SIZE; i++) {
            for (int j = 0; j < ScrabbleGame.SIZE; j++) {
                JButton b = new JButton(" ");
                b.setActionCommand(i + " " + j); // this is what provides the coordinates
                boardButtons[i][j] = b;
                int x = i;
                int y = j;
                // b.addActionListener(e -> model.play(x,y));
                b.addActionListener(bc);
                boardPanel.add(b);
                switch (i) {
                    // Coloring top row to bottom row (left to right)
                    case 0:
                        if (i == 0 && j == 0) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 1:
                        if (i == 0 && j == 7) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 2:
                        if (i == 0 && j == 14) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 3:
                        if (i == 1 && j == 1) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 4:
                        if (i == 1 && j == 5) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 5:
                        if (i == 1 && j == 9) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 6:
                        if (i == 1 && j == 13) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 7:
                        if (i == 2 && j == 2) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 8:
                        if (i == 2 && j == 6) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 9:
                        if (i == 2 && j == 8) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 10:
                        if (i == 2 && j == 12) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 11:
                        if (i == 3 && j == 3) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 12:
                        if (i == 3 && j == 7) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 13:
                        if (i == 3 && j == 7) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 14:
                        if (i == 3 && j == 11) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 15:
                        if (i == 4 && j == 4) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 16:
                        if (i == 4 && j == 10) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 17:
                        if (i == 5 && j == 1) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 18:
                        if (i == 5 && j == 5) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 19:
                        if (i == 5 && j == 9) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 20:
                        if (i == 5 && j == 13) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 21:
                        if (i == 6 && j == 2) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 22:
                        if (i == 6 && j == 6) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 23:
                        if (i == 6 && j == 8) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 24:
                        if (i == 6 && j == 12) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 25:
                        if (i == 7 && j == 0) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 26:
                        if (i == 7 && j == 3) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 27:
                        if (i == 7 && j == 7) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 28:
                        if (i == 7 && j == 11) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 29:
                        if (i == 7 && j == 14) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 30:
                        if (i == 8 && j == 2) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 31:
                        if (i == 8 && j == 6) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 32:
                        if (i == 8 && j == 8) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 33:
                        if (i == 8 && j == 12) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 34:
                        if (i == 9 && j == 1) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 35:
                        if (i == 9 && j == 5) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 36:
                        if (i == 9 && j == 9) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 37:
                        if (i == 9 && j == 13) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 38:
                        if (i == 10 && j == 4) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 39:
                        if (i == 10 && j == 10) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 40:
                        if (i == 11 && j == 3) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 41:
                        if (i == 11 && j == 7) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 42:
                        if (i == 11 && j == 11) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 43:
                        if (i == 12 && j == 2) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 44:
                        if (i == 12 && j == 6) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 45:
                        if (i == 12 && j == 8) {
                            b.setBackground(new Color(153, 204, 255));
                        }
                    case 46:
                        if (i == 12 && j == 12) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 47:
                        if (i == 13 && j == 1) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 48:
                        if (i == 13 && j == 5) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 49:
                        if (i == 13 && j == 9) {
                            b.setBackground(new Color(51, 153, 255));
                        }
                    case 50:
                        if (i == 13 && j == 13) {
                            b.setBackground(new Color(255, 204, 229));
                        }
                    case 51:
                        if (i == 14 && j == 0) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 52:
                        if (i == 14 && j == 7) {
                            b.setBackground(new Color(255, 102, 178));
                        }
                    case 53:
                        if (i == 14 && j == 14) {
                            b.setBackground(new Color(255, 102, 178));
                        }

                }
                b.setOpaque(true);
            }
        }
        // adding all the panel to this (the parent JFrame)
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.EAST);
        this.add(PlayerPanel, BorderLayout.WEST);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true);

        // The number of players selection window
        String[] playerOptions = new String[] { "4", "3", "2", "1" };
        int response = JOptionPane.showOptionDialog(null,
                "Please select the number of players", "Player selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, playerOptions, playerOptions[3]);

        // Where response == 3 for 1, 2 for 2, 1 for 3 and 0 for 4
        model.addPlayers(response);

        // Display the hand of the current player. Start with player 0.
        displayCurrentPlayerHand();
    }

    public void displayCurrentPlayerHand() {
        currentPlayerText.setText("Player " + model.getCurrentPlayerIndex() + "'s hand:");
        String hand = this.model.getCurrentPlayer().getHandString(); // this returns a string
        JButton[] playerHandButtons = new JButton[hand.length()];
        int i = 0;
        for (String letter : hand.split(" ")) {
            JButton letterButton = new JButton(letter); // set the label of the button to be the letter
            letterButton.setActionCommand(letter); // this is what saves the letter string to be placed on the board
            playerHandButtons[i] = letterButton;
            letterButton.addActionListener(bc);
            currentPlayerHandPanel.add(letterButton);
            i++;
        }
        currentPlayerHandPanel.updateUI();
    }

    public void hidePreviousPlayerHand() {
        currentPlayerText.selectAll();
        currentPlayerText.replaceSelection("");
        currentPlayerHandPanel.removeAll();
    }

    public void updateScore() {
        scorePanelText.selectAll();
        scorePanelText.replaceSelection(""); // clear the previous scores
        StringBuilder score = new StringBuilder("GAME SCORE:\n\n");
        for (Player player : model.players) {
            score.append("Player " + player.getID() + ": " + player.getScore() + "\n");
        }
        scorePanelText.setText(score.toString());
    }

    public static void main(String[] args) {
        new ScrabbleFrame();
    }

    @Override
    public void updateView(ScrabbleEvent e) {
        // String tile = e.getCurrentPlayer.placeLetter().toString;
        String tile = e.getLetterToPlace();
        if (tile != "NONE") { // when there's not letter to place, we set that to "NONE"
            boardButtons[e.getRow()][e.getCol()].setText(tile);
        }
        boardPanel.updateUI();
        hidePreviousPlayerHand();
        updateScore();
        displayCurrentPlayerHand();
    }
}
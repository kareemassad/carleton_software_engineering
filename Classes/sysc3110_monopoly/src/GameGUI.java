import Models.Game;
import Models.Player;
import Models.Property;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.PrintStream;
import java.util.Scanner;

public class GameGUI extends JFrame {

    private GamePlay gamePlay;
    private Game model;

    private JButton jailButton;
    public static JButton rollButton;


    private final int ROLLDICE = 1;
    private final int PASSTURN = 2;
    private final int QUITGAME = 3;
    private final int PRINTSTATUS = 4;
    private final int BUYPROPERTY = 5;
    private final int ENDGAME = 6;
    private final int BUYHOUSE = 7;
    private final int BUYHOTEL = 8;
    private final int PAYJAILFINE = 9;
    private final int EXITAPP = 111;

    public GameGUI(GamePlay gamePlay) {
        super("Monopoly");
        this.gamePlay = gamePlay;

        BoardFrame boardFrame = new BoardFrame();

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1980, 1080);

        //Setup for Text Area
        JPanel eastPanel = new JPanel();
        JTextArea playerTextArea = new JTextArea(30, 30);
        playerTextArea.setEditable(false);
        playerTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
        playerTextArea.append("Welcome to the game!\n");
        playerTextArea.append("Please choose the number of players\n");

        JScrollPane scrollPane = new JScrollPane(playerTextArea);
        eastPanel.add(scrollPane);

        JTextAreaOutputStream out = new JTextAreaOutputStream(playerTextArea);
        System.setOut(new PrintStream(out));

        //Create button panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        //JLabel playerNum = new JLabel("Enter Player Numbers:");

        // Setup for Buttons
        rollButton = new JButton("Roll");
        JButton quitButton = new JButton("Quit");
        JButton playerStatusButton = new JButton("Display Player Status");
        JButton helpButton = new JButton("Help");
        JButton buyButton = new JButton("Buy");
        JButton passButton = new JButton("Pass");
        jailButton = new JButton("Pay jail fine");
        JButton buyHotelButton = new JButton("Buy Hotel");
        JButton buyHouseButton = new JButton("Buy Hotel");

        // set up for player numbers drop down list
        JLabel dropdownMenu = new JLabel("Number of player: ");
        String[] numberOfPlayers = new String[4];
        numberOfPlayers[0] = "1";
        numberOfPlayers[1] = "2";
        numberOfPlayers[2] = "3";
        numberOfPlayers[3] = "4";
        JComboBox comboBox = new JComboBox(numberOfPlayers);

        // set up for languages
        JLabel languages = new JLabel("Language: ");
        String[] languageList = new String[2];
        languageList[0] = "English";
        languageList[1] = "Arabic";
        JComboBox comboBox2 = new JComboBox(languageList);

        //Setup for action commands
        rollButton.setActionCommand("R");
        quitButton.setActionCommand("Q");
        playerStatusButton.setActionCommand("D");
        helpButton.setActionCommand("H");
        buyButton.setActionCommand("B");
        passButton.setActionCommand("P");
        comboBox.setActionCommand("C");
        jailButton.setActionCommand("J");
        comboBox2.setActionCommand("L");
        buyHotelButton.setActionCommand("BuyHotel");
        buyHouseButton.setActionCommand("BuyHouse");
        quitButton.setActionCommand("Q");


        //Setup for action listeners
        rollButton.addActionListener(gamePlay);
        quitButton.addActionListener(gamePlay);
        playerStatusButton.addActionListener(gamePlay);
        helpButton.addActionListener(gamePlay);
        buyButton.addActionListener(gamePlay);
        passButton.addActionListener(gamePlay);
        comboBox.addActionListener(gamePlay);
        jailButton.addActionListener(gamePlay);
        buyHotelButton.addActionListener(gamePlay);
        buyHouseButton.addActionListener(gamePlay);
        comboBox2.addActionListener(gamePlay);
        quitButton.addActionListener(gamePlay);


        rollButton.setFont(new Font("Verdana", Font.BOLD, 15));
        quitButton.setFont(new Font("Verdana", Font.BOLD, 15));
        playerStatusButton.setFont(new Font("Verdana", Font.BOLD, 15));
        helpButton.setFont(new Font("Verdana", Font.BOLD, 15));
        buyButton.setFont(new Font("Verdana", Font.BOLD, 15));
        passButton.setFont(new Font("Verdana", Font.BOLD, 15));
        jailButton.setFont(new Font("Verdana", Font.BOLD, 15));
        buyHotelButton.setFont(new Font("Verdana", Font.BOLD, 15));
        buyHouseButton.setFont(new Font("Verdana", Font.BOLD, 15));

        southPanel.add(rollButton);
        southPanel.add(buyButton);
        southPanel.add(passButton);
        southPanel.add(quitButton);
        southPanel.add(playerStatusButton);
        southPanel.add(helpButton);
        southPanel.add(dropdownMenu);
        southPanel.add(comboBox);
        southPanel.add(jailButton);
        southPanel.add(comboBox2);
        southPanel.add(buyHotelButton);
        southPanel.add(buyHouseButton);


        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(boardFrame.getContentPane(), BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    public JButton getRollButton(){
        return this.rollButton;
    }


    public boolean AddPlayers(int num) {
        for (int i = 1; i <= num; i++){
            String name = String.format("Player %s", i);
            Player player = new Player(name);
            this.gamePlay.getGame().addPlayer(player);
            return true;
        }









    public boolean OptionTasks(int choice) {

        boolean bool = true;
        Player cp = this.gamePlay.getGame().getCurrentPlayer();

        switch (choice) {
            case ROLLDICE:
                int diceRoll = this.gamePlay.rollDice();
                this.gamePlay.movePlayerPosition(cp, diceRoll);
                this.gamePlay.checkPositionEvents(cp);
                break;
            case PASSTURN:
                this.gamePlay.passTurn(cp);
                break;
            case QUITGAME:
                this.gamePlay.quitGame(cp);
                bool = false;
                break;
            case PRINTSTATUS:
                System.out.println(this.gamePlay.printPlayerStatus(cp) + "\n");
                break;
            case ENDGAME:
                bool = false;
                break;
            case BUYPROPERTY:
                Property property = this.gamePlay.getGame().getProperty(cp.getPosition());
                this.gamePlay.buyProperty(property);
                break;
            case BUYHOUSE:
                Property houseProperty = this.gamePlay.getGame().getProperty(cp.getPosition());
                this.gamePlay.buyHouse(cp,houseProperty);
                break;
            case BUYHOTEL:
                Property hotelProperty = this.gamePlay.getGame().getProperty(cp.getPosition());
                this.gamePlay.buyHotel(cp,hotelProperty);
                break;
            case PAYJAILFINE:
                this.gamePlay.payJailFine(cp);
                break;

            case EXITAPP:
                System.exit(0);
                break;
        }

        return bool;
    }


    public void RunGame() {
        /*
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!this.AddPlayers(scanner)) {
                break;
            }
        }
        // TODO fix this
        this.gamePlay.getGame().setCurrentPlayer(gamePlay.getGame().getPlayerList().get(0));
        while (true) {
            int choice = FirstMenuOptions(scanner);
            if (!OptionTasks(choice)) {
                break;
            }
            int choice2 = SecondMenuOptions(scanner);
            if (!OptionTasks(choice2)) {
                break;
            }
        }
         */


    }




}


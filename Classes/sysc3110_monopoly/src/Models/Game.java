package Models;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class serves to store all data relevant to a game of monopoly
 * (persistence)
 */

public class Game {

    private ArrayList<Player> playerList;
    private Board board;
    private Player currentPlayer;
    private Dice dice;
    private int turnCount;
    private int numberOfPlayers;

    public Game() {
        this.playerList = new ArrayList<>();
        this.board = new Board();
        this.dice = new Dice();
        this.currentPlayer = null;
        this.turnCount = 0;
    }

    public int rollDice() {
        return this.dice.rollDice();
    }

    public boolean isDiceRollDouble() {
        return this.dice.rolledDouble();
    }

    public Player passTurn(Player player) {
        int i = this.playerList.indexOf(player);

        if (i == this.playerList.size() - 1) {
            this.currentPlayer = this.playerList.get(0);
        } else {
            this.currentPlayer = this.playerList.get(i + 1);
        }

        this.incrementTurnCount();
        System.out.println("It's " + this.currentPlayer.getName() + "'s turn!");
        return this.currentPlayer;
    }

    public boolean addPlayer(Player player) {
        return this.playerList.add(player);
    }

    public boolean removePlayer(Player player) {
        return this.playerList.remove(player);
    }

    public Player getPlayer(Player player) {
        return this.playerList.get(this.playerList.indexOf(player));
    }

    public int getPlayerListSize() {
        return this.playerList.size();
    }

    public Property getProperty(int position) {
        return this.board.getProperty(position);
    }

    public boolean setPropertyOwner(Player player, Property property) {
        return this.board.setPropertyOwner(player, property);
    }

    /**
     * Get a map of all properties owned by argument player, maped by color
     *
     * @param owner
     * @return
     */
    public Map<String, List<Property>> getMonopolizedProperties(Player owner) {
        return this.board.getMonopolizedProperties(owner);
    }

    /**
     * Check if argument property is part of a monoply owned by argument player
     *
     * @param player
     * @param property
     * @return
     */
    public boolean checkIfPropertyInMonopoly(Player player, Property property) {
        Map<String, List<Property>> monopProperties = this.getMonopolizedProperties(player);

        List<Property> properties = new ArrayList<>();
        monopProperties.values().forEach(properties::addAll);

        return properties.contains(property);
    }

    public String printMonopolizedProperties(Player owner) {
        Map<String, List<Property>> monopProperties = this.getMonopolizedProperties(owner);

        if (monopProperties != null) {
            return monopProperties.toString();
        }
        return null;
    }

    public Player getPropertyOwner(int position) {
        return this.board.getPropertyOwner(position);
    }

    public boolean removePropertyOwner(Player player) {
        return this.board.removePropertyOwner(player);
    }

    public boolean addHouse(Property property) {
        return this.board.addHouse(property);
    }

    public boolean addHotel(Property property) {
        return this.board.addHotel(property);
    }

    public boolean putInJail(Player player) {
        if (this.getPlayer(player) != null) {
            this.getPlayer(player).setInJail(true);
            return true;
        }

        return false;
    }

    public boolean removeFromJail(Player player) {
        if (this.getPlayer(player) != null) {
            this.getPlayer(player).setInJail(false);
            return true;
        }

        return false;
    }

    public List<Property> getPlayerProperties(Player player) {
        return this.board.getPlayerProperties(player);
    }

    public String printPlayerProperties(Player player) {
        return this.board.printPlayerProperties(player);
    }

    public boolean isJailPosition(int position) {
        return this.board.isJailPosition(position);
    }
    public boolean isChestPosition(int position) {
        return this.board.isChestPosition(position);
    }
    public boolean isChancePosition(int position) {
        return this.board.isChancePosition(position);
    }
    public boolean isGoPosition(int position) {
        return this.board.isGoPosition(position);
    }

    public boolean isTaxPosition(int position) {
        return this.board.isTaxPosition(position);
    }

    public double getTaxCost(int position) {
        return this.board.getTaxCost(position);
    }

    public int getGoPosition() {
        return this.board.getGoPosition();
    }

    public int incrementTurnCount() {
        this.turnCount++;

        if (turnCount>=4){
            turnCount = 0;
        }

        return this.turnCount;
    }

    // region Setters and Getters
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    public void playerSetup(){
        //set up real player
        Player temp;
        if(numberOfPlayers >0){
            for(int i = 0; i < numberOfPlayers ; i++ ){

                temp = new Player("Player: " + (i+1));

                this.playerList.add(temp);
            }
        }
        this.currentPlayer = playerList.get(0);
        System.out.println();
        System.out.println("We have "+ (numberOfPlayers) + " players!");

        //set up AI
        for(int i = 0; i < (4-numberOfPlayers) ; i++ ){
            temp = new Player("AI" + (i+1));
            this.playerList.add(temp);
        }

        for(int i = 0; i < this.playerList.size() ; i++ ){
            System.out.println(this.playerList.get(i).getName());
        }
    }
}
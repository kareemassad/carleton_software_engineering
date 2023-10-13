import Helpers.EventAlertHandler;
import Models.Board;
import Models.Game;
import Models.Player;
import Models.Property;
import jdk.jfr.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Dana El Sherif
 */
public class GamePlay implements ActionListener {

    private Game game;
    private GameGUI view;


    public GamePlay(Game game) {
        this.game = game;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player cp = game.getCurrentPlayer();

        if (e.getActionCommand().equals("H")){
            System.out.println("Please visit the link:");
            System.out.println("https://www.hasbro.com/common/instruct/00009.pdf");
        }
        if (e.getActionCommand().equals("P")){
            this.passTurn(game.getCurrentPlayer());
            GameGUI.rollButton.setEnabled(true);
        }
        if (e.getActionCommand().equals("C")){
            JComboBox source = (JComboBox) e.getSource();
            int playerNUmber = source.getSelectedIndex()+1;
            game.setNumberOfPlayers(playerNUmber);
            game.playerSetup();
            ((JComboBox<?>) e.getSource()).setEnabled(false);

        }
        if (e.getActionCommand().equals("R")){
            JButton source = (JButton) e.getSource();
            int diceRoll = this.rollDice();
            this.movePlayerPosition(cp, diceRoll);
            source.setEnabled(false);

//            game.incrementTurnCount();
//            game.setCurrentPlayer(game.getPlayerList().get(game.getTurnCount()));
//
//            cp = this.game.getCurrentPlayer();
//            while(cp.getName().startsWith("AI")){
//                diceRoll = this.rollDice();
//                this.movePlayerPosition(cp, diceRoll);
//                Property temp = this.game.getBoard().getProperty(cp.getPosition());
//                //this.movePlayerPosition(cp, this.rollDice());
//                this.checkPositionEvents(cp);
//                this.buyProperty(temp);
//
//                game.incrementTurnCount();
//                game.setCurrentPlayer(game.getPlayerList().get(game.getTurnCount()));
//
//                cp = this.game.getCurrentPlayer();
//            }

        }
        if (e.getActionCommand().equals("B")){
            Property property = this.getGame().getProperty(cp.getPosition());
            this.buyProperty(property);
        }
        if (e.getActionCommand().equals("L")){
            //
        }
        if (e.getActionCommand().equals("BuyHotel")){
            Property hotelProperty = this.getGame().getProperty(cp.getPosition());
            this.buyHotel(cp,hotelProperty);
        }
        if (e.getActionCommand().equals("BuyHouse")){
            Property houseProperty = this.getGame().getProperty(cp.getPosition());
            this.buyHouse(cp,houseProperty);
        }





    }



    public int rollDice() {
        return this.game.rollDice();
    }


    public int movePlayerPosition(Player player, int diceRoll) {
        int newPosition = player.getPosition() + diceRoll;
        int maxPos = this.game.getBoard().getMaxPosition();

        if (newPosition > maxPos) {
            this.game.getPlayer(player).setPosition(newPosition - maxPos);
            player.addWealth(200);
            System.out.println(EventAlertHandler.goEvent(player));
        } else {
            this.game.getPlayer(player).setPosition(newPosition);
        }

        // TODO maybe will modify this for cleanliness
        return this.game.getPlayer(player).getPosition();
    }

    public boolean checkPositionEvents(Player player) {
        int position = player.getPosition();
        Property currentProperty = this.game.getProperty(position);
        System.out.println(EventAlertHandler.landedOnPropertyEvent(player, currentProperty));

        if (this.payRent(player)) {
            this.passTurn(player);
            return true;
        }

        else if (this.game.isJailPosition(position)) {
            System.out.println(EventAlertHandler.jailEnterEvent(player));
            this.goToJail(player);
            this.passTurn(player);
            return true;
        }

        else if(this.checkGo(player)){
            passTurn(player);
        }
        else if(this.checkJail(player)){
            passTurn(player);
        }

        else if (this.game.isTaxPosition(position)) {
            System.out.println(EventAlertHandler.incomeTaxEvent(player, this.game.getProperty(position)));
            this.payTax(player);
            this.passTurn(player);
        }

        return true;
    }

    public boolean payTax(Player player) {
        int position = player.getPosition();

        player.subtractWealth(this.game.getTaxCost(position));

        if (player.isBankrupt()) {
            EventAlertHandler.bankruptcyAlertEvent(player);
        }

        return true;
    }


    public boolean goToJail(Player player) {
        int position = player.getPosition();

        if (this.game.isJailPosition(position)) {
            this.game.putInJail(player);
            return true;
        }

        return false;
    }

    public boolean isDoubleRollForJailExit(Player player) {
        if (this.game.isDiceRollDouble()) {
            this.game.removeFromJail(player);
            System.out.println(EventAlertHandler.jailExitDoubleEvent(player));
            return true;
        }

        return false;
    }

    public boolean payJailFine(Player player) {
        player.subtractWealth(50);

        if (player.isBankrupt()) {
            System.out.println(EventAlertHandler.bankruptcyAlertEvent(player));
            return false;
        } else {
            this.game.removeFromJail(player);
            System.out.println(EventAlertHandler.jailExitPayEvent(player));
        }

        return true;
    }

    public String printPlayerStatus(Player player) {

        StringBuilder sb = new StringBuilder();
        sb.append(player.toString() + "\n");
        sb.append(this.game.printPlayerProperties(player));

        return sb.toString();
    }

    public Player passTurn(Player player) {

        return this.game.passTurn(player);

    }
    // check method to check if the property is chance or chest to ignore it for purchase
    public boolean checkBuyable(Player player) {
        int position = player.getPosition();

        if (this.game.isChancePosition(position)) {
            System.out.println(EventAlertHandler.chanceEvent(player));
            return false;
        }
        else if (this.game.isChestPosition(position)) {
            System.out.println(EventAlertHandler.chestEvent(player));
            return false;
        }
        System.out.println("Please choose 'Buy' or 'Pass'");
        return true;
    }


    // check method to check if the property is jail to ignore it for purchase
    public boolean checkJail(Player player) {
        int position = player.getPosition();
        if (this.game.isJailPosition(position)) {
            if(player.getName().startsWith("AI")){
                this.payJailFine(player);
            }else{
                System.out.println(EventAlertHandler.visitingJailEvent(player));

            }
            return true;
        }
        return false;
    }

    public boolean checkGo(Player player){
        int position = player.getPosition();
        if (this.game.isGoPosition(position)) {
            return true;
        }
        return false;
    }

    /**
     * Add a house to the argument property if it is part of a monoply owned by
     * argument player
     *
     * @param player
     * @param property
     * @return
     */
    public boolean buyHouse(Player player, Property property) {
        if (this.game.checkIfPropertyInMonopoly(player, property)) {
            this.passTurn(player);
            return this.game.addHouse(property);
        }

        return false;
    }

    /**
     * Add a hotel to the argument property if it is part of a monoply owned by
     * argument player
     *
     * @param player
     * @param property
     * @return
     */
    public boolean buyHotel(Player player, Property property) {
        if (this.game.checkIfPropertyInMonopoly(player, property)) {
            this.passTurn(player);
            return this.game.addHotel(property);
        }

        return false;
    }

    public boolean buyProperty(Property property) {
        Player cp = this.game.getCurrentPlayer();
        int currentPos = cp.getPosition();
        if (this.checkBuyable(cp) && !this.checkGo(cp) && !this.checkJail(cp)){
            if (this.game.setPropertyOwner(cp, property)) {
                int newCurrentPlayerWealth = (int) (cp.getWealth() - property.getCost());

                if (newCurrentPlayerWealth < 0) {
                    System.out.println(EventAlertHandler.failPurchaseEvent(property, cp));

                    return false;
                } else {
                    this.game.getCurrentPlayer().setWealth(newCurrentPlayerWealth);
                    this.game.setPropertyOwner(cp, property);
                    System.out.println(EventAlertHandler.successPurchaseEvent(property, cp));

                    return true;
                }
            }
        }
        return false;
    }


    public boolean payRent(Player player) {
        int position = player.getPosition();

        Property property = this.game.getProperty(position);
        Player owner = property.getOwner();

        if (owner == null) {
            return false;
        } else {
            double rentCost = property.getRentCost();
            player.setWealth(player.getWealth() - rentCost);
            owner.setWealth(owner.getWealth() + rentCost);
            System.out.println(EventAlertHandler.rentPayEvent(player, property.getOwner()));

            if (player.isBankrupt()) {
                this.game.removePropertyOwner(player);
                this.game.removePlayer(player);
                System.out.println(EventAlertHandler.bankruptcyAlertEvent(player));
            }
        }

        return true;
    }

    public boolean quitGame(Player player) {
        System.out.println(EventAlertHandler.quitGameEvent(player));
        return this.game.removePlayer(player);
    }

    public Game getGame() {
        return this.game;
    }


}
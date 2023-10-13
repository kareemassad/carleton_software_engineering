package Models;

/**
 * Author: Keefer Belanger Date: 2021-10-20
 */

public class Dice {

    private int die1;
    private int die2;

    public int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    public int rollDice() {
        die1 = (int) (Math.random() * 6) + 1;
        die2 = (int) (Math.random() * 6) + 1;
        return this.die1 + this.die2;
    }

    public boolean rolledDouble() {
        return die1 == die2;
    }

    public int getDiceRoll() {
        return this.die1 + this.die2;
    }

    public String toString() {
        return die1 + " + " + die2;
    }


}
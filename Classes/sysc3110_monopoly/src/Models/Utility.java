package Models;

/**
 * Author: Dana El Sherif
 */
public class Utility extends Property{

    public Utility(String name,
                   int position,
                   double cost,
                   Player owner) {
        super(name, position, cost, owner);
        this.rentCost = cost * 0.1;
    }
}
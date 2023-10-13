
package Models;

public class Railroad extends Property{

    public Railroad(String name,
                    int position,
                    double cost,
                    Player owner) {
        super(name, position, cost, owner);
        this.rentCost = cost * 0.12;
    }
}
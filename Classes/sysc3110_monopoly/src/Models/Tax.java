package Models;

public class Tax extends Property{



    public Tax(String name,
               int position,
               double taxCost,
               Player owner) {
        super(name, position, 0, null);
        this.rentCost = taxCost;
    }


}
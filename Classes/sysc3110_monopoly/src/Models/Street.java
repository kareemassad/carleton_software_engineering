package Models;

/**
 * Author: Dana El Sherif
 * This encompasses all properties that can build houses and hotels
 */

public class Street extends Property {

    private int houses;
    private int hotels;
    private String color;

    public Street(String name,
                  int position,
                  String color,
                  double cost,
                  Player owner) {
        super(name, position, cost, owner);
        this.color = color;
        this.rentCost = cost * 0.15;
        this.houses = 0;
        this.hotels = 0;
    }


    public int addHouse() {
        return this.houses++;
    }

    public int addHotel() {
        return this.hotels++;
    }


    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public int getHotels() {
        return hotels;
    }

    public void setHotels(int hotels) {
        this.hotels = hotels;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                "color='" + color + '\'' +
                "# of houses='" + houses + '\'' +
                "# of hotels='" + hotels + '\'' +
                "}\n";
    }
}
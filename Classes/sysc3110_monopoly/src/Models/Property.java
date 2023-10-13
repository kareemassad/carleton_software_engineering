package Models;

import java.util.Objects;

import org.json.JSONObject;

public class Property {

    protected Player owner;
    protected String name;
    protected double cost;
    protected double rentCost;
    protected int position;

    public Property(String name,
            int position,
            double cost,
            Player owner) {
        this.owner = owner;
        this.name = name;
        this.cost = cost;
        this.rentCost = cost * 0.15;
        this.position = position;
    }

    public boolean removeOwner(Player player) {
        this.owner = null;
        return true;
    }

    // region Setters and Getters
    public Player getOwner() {
        return owner;
    }

    public boolean setOwner(Player player) {
        if (this.owner == null) {
            this.owner = player;
            return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRentCost() {
        return rentCost;
    }

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    // endregion

    @Override
    public String toString() {
        return "Property{ " +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Property property = (Property) o;
        return Double.compare(property.cost, cost) == 0 && Double.compare(property.rentCost, rentCost) == 0
                && position == property.position && name.equals(property.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, rentCost, position);
    }
}
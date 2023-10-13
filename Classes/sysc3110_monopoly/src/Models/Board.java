package Models;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Board {
    // options englishUK, arabic
    public static final String DEFAULT_LANGUAGE = "englishUK";

    final static String RED = "Red";
    final static String BLUE = "Blue";
    final static String INDIGO = "Indigo";
    final static String GREEN = "Green";
    final static String YELLOW = "Yellow";
    final static String PINK = "Pink";
    final static String ORANGE = "Orange";
    final static String BROWN = "Brown";

    HashMap<Integer, Property> properties;
    Map<String, List<Property>> propertiesByColorMap;
    private JSONHelper jsonHelper;

    public Board() {
        try {
            this.jsonHelper = new JSONHelper(DEFAULT_LANGUAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = new HashMap<>();
        this.createBoard();
        propertiesByColorMap = new HashMap<>();
        propertiesByColorMap = this.storePropertiesSameColor();
    }

    private HashMap<Integer, Property> createBoard() {
        // uses the JSONArray tiles from JSONHelper to create a HashMap of all the
        // properties
        JSONArray tiles = this.jsonHelper.getTiles();
        for (int i = 0; i < tiles.length(); i++) {
            JSONObject tile = tiles.getJSONObject(i);

            String name = tile.getString("name");
            double price = tile.getInt("price");
            String type = tile.getString("type");

            // if type property, cast to street
            if (type.equals("property")) {
                // get tile name, price, color
                String color = tile.getString("color");
                Property property = new Street(name, i, color, price, null);
                this.properties.put(i, property);
                // if type go, special, jail cast to property
            } else if (type.equals("go") || type.equals("special") || type.equals("jail")) {
                Property property = new Property(name, i, price, null);
                this.properties.put(i, property);
                // if type tax, cast to tax
            } else if (type.equals("tax")) {
                Tax tax = new Tax(name, i, price, null);
                this.properties.put(i, tax);
                // if type utility, cast to utility
            } else if (type.equals("utility")) {
                Utility utility = new Utility(name, i, price, null);
                this.properties.put(i, utility);
                // if type station, cast to railroad
            } else if (type.equals("station")) {
                Railroad railroad = new Railroad(name, i, price, null);
                this.properties.put(i, railroad);
            }
        }
        return this.properties;
    }

    public HashMap<Integer, Property> getProperties() {
        return this.properties;
    }

    /**
     * rn a list of properties belonging to the argument player
     *
     * @param player
     * @return
     */
    public List<Property> getPlayerProperties(Player player) {
        List<Property> playerProperties = new ArrayList<>();

        for (Property p : this.properties.values()) {
            if (p.getOwner() != null && p.getOwner().equals(player)) {
                playerProperties.add(p);
            }
        }

        return playerProperties;
    }

    public String printPlayerProperties(Player player) {
        return this.getPlayerProperties(player).toString();
    }

    public Player getPropertyOwner(int position) {
        return this.getProperty(position).getOwner();
    }

    public boolean setPropertyOwner(Player player, Property property) {
        Property prop = this.properties.get(property.getPosition());

        return prop.setOwner(player);
    }

    public boolean removePropertyOwner(Player player) {
        Collection<Property> properties = this.properties.values();

        for (Property p : this.properties.values()) {
            if (p.getOwner().equals(player)) {
                p.removeOwner(player);
            }
        }

        return true;
    }

    public boolean addHouse(Property property) {
        if (property instanceof Street) {
            ((Street) property).addHouse();
            return true;
        }
        return false;
    }

    public boolean addHotel(Property property) {
        if (property instanceof Street) {
            ((Street) property).addHotel();
            return true;
        }
        return false;
    }

    public int getMaxPosition() {
        Set<Integer> positions = this.properties.keySet();

        return Collections.max(positions);
    }

    public Property getProperty(int position) {
        return this.properties.get(position);
    }

    /**
     * Returns a "map<color, properties>" of all properties of color monopolized by
     * a player
     *
     * @param owner
     * @return
     */
    public Map<String, List<Property>> getMonopolizedProperties(Player owner) {

        List<Property> monopolizedProperties = new ArrayList<>();
        Map<String, List<Property>> monopPropertiesMap = new HashMap<>();

        for (String colorKey : this.propertiesByColorMap.keySet()) {
            List<Property> properties = this.propertiesByColorMap.get(colorKey);
            if (checkPropertiesOfColorSameOwner(properties, owner)) {
                monopPropertiesMap.put(colorKey, properties);
            }
        }

        return monopPropertiesMap;
    }

    /**
     * Stores all properties of the same color within the hashmap
     *
     * @return
     */
    public Map<String, List<Property>> storePropertiesSameColor() {
        this.propertiesByColorMap.put(RED, this.getPropertiesByColor(RED));
        this.propertiesByColorMap.put(BLUE, this.getPropertiesByColor(BLUE));
        this.propertiesByColorMap.put(INDIGO, this.getPropertiesByColor(INDIGO));
        this.propertiesByColorMap.put(GREEN, this.getPropertiesByColor(GREEN));
        this.propertiesByColorMap.put(YELLOW, this.getPropertiesByColor(YELLOW));
        this.propertiesByColorMap.put(PINK, this.getPropertiesByColor(PINK));
        this.propertiesByColorMap.put(ORANGE, this.getPropertiesByColor(ORANGE));
        this.propertiesByColorMap.put(BROWN, this.getPropertiesByColor(BROWN));

        return this.propertiesByColorMap;
    }

    /**
     * Return a list of properties of the argument color
     *
     * @param color
     * @return
     */
    public List<Property> getPropertiesByColor(String color) {
        List<Property> propertiesOfColor = new ArrayList<>();

        for (Property p : this.properties.values()) {
            if (p instanceof Street) {
                if (((Street) p).getColor().equals(color)) {
                    propertiesOfColor.add(p);
                }
            }
        }

        return propertiesOfColor;
    }

    /**
     * check if all properties of a color belong to the same argument owner
     *
     * @param propertiesOfColor
     * @param owner
     * @return
     */
    public boolean checkPropertiesOfColorSameOwner(List<Property> propertiesOfColor, Player owner) {
        for (Property p : propertiesOfColor) {
            if (p.getOwner() == null || !p.getOwner().equals(owner)) {
                return false;
            }
        }

        return true;
    }

    public void setProperty(int position, Property property) {
        this.properties.put(position, property);
    }

    public boolean isJailPosition(int position) {
        return position == 30;
    }

    public boolean isChestPosition(int position) {
        return position == 33 || position == 17 || position == 2;
    }

    public boolean isChancePosition(int position) {
        return position == 36 || position == 22 || position == 7;
    }

    public boolean isGoPosition(int position) {
        return position == 0;
    }

    public boolean isTaxPosition(int position) {
        return position == 38 || position == 4;
    }

    /**
     * Get the amount of tax payment required from the tax properties
     *
     * @param position
     * @return
     */
    public double getTaxCost(int position) {
        if (this.isTaxPosition(position)) {
            return this.properties.get(position).getRentCost();
        }

        return 0;
    }

    public int getGoPosition() {
        return 0;
    }

}

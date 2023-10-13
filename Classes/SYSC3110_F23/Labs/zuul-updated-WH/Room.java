import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public static final String NORTH = "north";
    public static final String SOUTH = "south";
    public static final String EAST = "east";
    public static final String WEST = "west";
    public static final String UP = "up";
    public static final String DOWN = "down";

    public String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }
    public Room getExit(String direction){
        return  exits.get(direction);
    }
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param up The up
     * @param down The down
     *
     */
    public void setExit( String direction, Room room)
    {
        exits.put(direction, room);

    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public void getLongDescription(){
        System.out.println("You are " + this.getDescription());
        System.out.print("Exits: ");
        for (String key: exits.keySet()){
            System.out.print(key + "  ");
        }
        System.out.println();
    }

}

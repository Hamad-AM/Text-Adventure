import java.util.Set;
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "LIMBO" application. 
 * "LIMBO" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes and Areeb Hamad Mohammed
 * @version 2016.02.29
 */

public class Room 
{
    private String name;
    private String description;

    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Item> itemsInRoom;  // stores the items of this room.
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        itemsInRoom = new HashMap<>();
        this.name = name;
    }

    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    
    /**
     * This method adds an item to the room
     * @param The item to be added to the room
     */
    public void addItem(Item newItem)
    {
        itemsInRoom.put(newItem.getName(), newItem);
    }

    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemsString();
    }
    
    /**
     * This method returns the name of the room
     * @return returns the name of room as a string
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Returns the room from the hashMap exits
     * @param direction
     * @return  The room in a given direction
     */
    public Room getRoomFromExits(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * This method retrieves an item from the room
     * @param name of the item
     * @return the Item object from the room
     */
    public Item getItem(String name)
    {
        return itemsInRoom.get(name);
    }
    
    
    /**
     * This method removes an item from the room
     * @param the item object to be removed from the room
     */
    public void removeItem(Item itemToRemove) 
    {
        itemsInRoom.remove(itemToRemove.getName(),itemToRemove);
    }
    
    
    /**
     * This method gets the items names from the room
     * @return the list of item names as a string
     */
    private String getItemsString()
    {
        String returnString = "Items: ";
        Set<String> keys = itemsInRoom.keySet();
        for(String item : keys) {
            returnString += " " + item;
        }
        return returnString;
    }
}
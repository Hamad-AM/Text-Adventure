import java.util.HashMap;
import java.util.Set;
/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This Class holds the data for the Entities.
 * The Data it holds includes the inventory, the room they are in their name.
 *
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class Entity
{
    // instance variables
    private HashMap<String, Item> inventory;    // stores the items that entity has.
    
    private int currentWeight;                  // The current weight the player is holding
    private int maxWeight;                 // The max weight the player can hold        
    
    private Room currentRoom;                   // The room the entity is in.
    private String name;
    
    /**
     * This method creates a new entity
     */
    public Entity(String name, int maxWeight)
    {
        this.name = name;
        inventory = new HashMap<>();
        this.maxWeight = maxWeight;
    }


    /**
     * This method gets the entities name
     * @return name The name of the entity as a string
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * This method is an accessor method for the current room the player resides in
     * @return the currentRoom a room object
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }


    /**
     * This method sets the currentRoom the player is in
     * @param the newRoom the player is in
     */
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }


    /**
     * This method adds an item to the Entities inventory
     * @param The item to be added to the inventory
     */
    public void addItem(Item itemToAdd)
    {
        inventory.put(itemToAdd.getName(), itemToAdd);
        currentWeight += inventory.get(itemToAdd.getName()).getWeight();
    }
    
    
    /**
     * This method removes an item from the inventory
     * @param The Item object to be removed
     */
    public void removeItem(Item itemToRemove)
    {
        currentWeight -= itemToRemove.getWeight();
        inventory.remove(itemToRemove.getName());
    }
    
    
    /**
     * This method removes an item from the inventory
     * @param The item's name to be removed
     */
    public void removeItem(String itemToRemove)
    {
        currentWeight -= inventory.get(itemToRemove).getWeight();
        inventory.remove(itemToRemove);
    }
    

    /**
     * This method drops an item from the entity
     * @param item
     */
    public void dropItem(Item item)
    {
        removeItem(item);
        
        // Leave item in the room the entity is in
        currentRoom.addItem(item);
    }


    /**
     * This method retrieves an item from the entities inventory
     * @param the name of the item
     * @return null if item is not present in the inventory otherwise the item
     */
    public Item getItemInInventory(String name)
    {
        return inventory.get(name);
    }
    
    
    /**
     * This method checks if an item is in the entities inventory
     * @param The item object
     * @return true if the item is in the players inventory
     */
    public boolean isItemInInventory(Item item)
    {
        if (getItemInInventory(item.getName()) != null){
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * This method checks if an item is in the entities inventory
     * @param The items name
     * @return true if the ite is in the players inventory
     */
    public boolean isItemInInventory(String name)
    {
        if (getItemInInventory(name) != null){
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * This method returns the items in the players inventory
     * @return An ArrayList of the items names
     */
    public String getInventory()
    {
        String returnString = "Items in inventory:";
        Set<String> keys = inventory.keySet();
        for(String itemName : keys) {
            returnString += " " + itemName;
        }
        return returnString;
    }
    
    
    /**
     *  This method gets the current weight of items the player is holding
     *  @return the weight of items in total as an integer
     */
    public int getCurrentWeight()
    {
        return currentWeight;
    }
    
    
    /**
     * This method returns the max weight the player can hold on to
     * @return the max weight as an integer
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
}

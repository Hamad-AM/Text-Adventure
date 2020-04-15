/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This class stores the data related to Items. And lets the user retrieve the relavent data.
 *
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class Item
{
    // instance variables

    private String name;        // The name of the character
    private boolean canPickup;  // Whether the item can be pickedup
    private int weight;         // The weight of the item
    private String description; // The description of the printed out telling us what it is and where it was found

    /**
     * Creates an item and initialises its fields
     * @param name The name of the object
     * @param canPickup Whether the item can be picked up
     * @param weight The weight of the item
     * @param discription The discription of the item
     */
    public Item(String name, boolean canPickup, int weight, String description)
    {
        this.name = name;
        this.canPickup = canPickup;
        this.weight = weight;
        this.description = description;
    }


    /**
     * This method returns the items name
     * @return name of type String
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method returns the items description 
     * @return desciption of type string
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * This method returns the items weight
     * @return weight of type int
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * This method return whether or not the item can be picked up
     * @return canPickup true if item can be picked up
     */
    public boolean getPickup()
    {
        return canPickup;
    }
}

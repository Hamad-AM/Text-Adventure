
/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This class Weapon is subclass that inherits from the item class.
 * Holds the information for the amount of damage it does, and the all weapons can be pickedup
 *
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class Weapon extends Item
{
    // instance variables - replace the example below with your own
    private float damage;   // the amount of damage the weapon will do;

    private float currentDurability;
    private float maxDurability;

    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String name, int weight, String description, float damage, float maxDurability)
    {
        super(name, true, weight, description);
        
        this.damage = damage;

        this.maxDurability = this.currentDurability = maxDurability;
    }

    /**
     * This method returns the amount of damage this weapon does
     * @return the number of hitpoints of damage the weapon does
     */
    public float getDamage() 
    {
        // Find percentage of the durabilty used up, and calculate the damage
        return (float)Math.floor(this.damage * (currentDurability / maxDurability));
    }

    /**
     * This method is to be called when the weapon is being used
     * decreases the durability of the weapon and returns the damage to be done
     * @return newDamage The damage dealt by the weapon as a float
     */
    public float use()
    {   
        decreaseDurability();

        float newDamage = getDamage();

        return newDamage;
    }

    /**
     * This method calculates the change in durabilty
     */
    private void decreaseDurability()
    {
        // Make sure the durability of the item does not go below 25%
        if (currentDurability <= (0.25* maxDurability)) {
            currentDurability = (float) 0.25 * maxDurability;
        } else {
            currentDurability--;
        }
    }
}

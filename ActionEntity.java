/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This Class is the ActionEntity class, and inherits from the Entity class.
 * This class holds the fields and methods necessary for entities that will be in battles.
 * The fields and data are the weapon they are holding on to and their hit points.
 * 
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class ActionEntity extends Entity
{

    private float maxHP;        // The max Hit points the entity can revieve
    private float currentHP;    // The current amount of hitpoints the entity has

    private Weapon weapon;      // The weapon the entity is currently using

    /**
     * Create a new ActioEntity and sets its HitPoints
     */
    public ActionEntity(String name, int weight, float maxHP)
    {
        super(name, weight);
        
        this.weapon = null;
        this.maxHP = this.currentHP = maxHP;
    }


    /**
     * This method returns the Max Hit Points of the entity
     * @return maxHP The maxmimum hitpoints as a float
     */
    public float getMaxHP()
    {
        return this.maxHP;
    }


    /**
     * This method returns the current amount of hit points the entity has left
     * @return currentHP the current number of hit points as a float
     */
    public float getCurrentHP()
    {
        return this.currentHP;
    }


    /**
     * This method sets the hit points based on the input value
     * @param addedHP The number of hit points to be added or subtracted if input is negative
     */
    public void setHP(float addedHP)
    {
        this.currentHP += addedHP;

        // Clamp the hit points of the player between 0 and the max hit points
        if (this.currentHP >= maxHP) {
            this.currentHP = this.maxHP;
        } else if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }


    /**
     * This method sets the current weapon of the entity
     * @param weapon The weapon being given to the entity of class Weapon
     */
    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }


    /**
     * This method retrieves the current weapon the entity is holding on to
     * @return weapon The weapon the entity has
     */
    public Weapon getWeapon()
    {
        return weapon;
    }


    /**
     * This methods calculutes the damage done to the entity
     * @param damageDone The number of hit points of damage done to the entity
     */
    public void doDamage(float damageDone)
    {
        this.currentHP -= damageDone;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }
}

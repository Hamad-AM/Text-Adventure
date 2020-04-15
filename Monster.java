/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This Class is the Monster class, inherits from the ActionEntity class.
 * This class checks if the monster is dead and if the monster is in the same room as the player
 * 
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class Monster extends ActionEntity
{
    // instance variables - replace the example below with your own

    private boolean isDead;   // the state of the monster

    private static Room currentPlayerRoom;  // The current room the player is in
    
    private String description;

    /**
     * Constructor for objects of class Monster
     */
    public Monster(String name, String description, float maxHP, Weapon weapon)
    {
        super(name, 10000, maxHP);
        
        this.setWeapon(weapon);

        this.description = description;
        this.isDead = false;
    }

    /**
     * This method checks if the monster is dead
     * @return true if the monster is dead otherwise false
     */
    public boolean getIsDead()
    {
        if (getCurrentHP() <= 0) {
            isDead = true;
            this.dropItem(this.getWeapon());
        } else {
            isDead = false;
        }
        return isDead;
    }

    /**
     * This method checks if the player is the same room as the character
     * @return true if the player is in the room
     */
    public boolean isPlayerInRoom()
    {
        if (currentPlayerRoom.getName().equals(this.getCurrentRoom().getName())) {
            return true;
        } else {
            return false;
        }
    }
    

    /**
     * This method updates hte currentPlayers position in the character class
     * @param currentPlayerRoom
     */
    public static void setCurrentPlayerRoom(Room currentPlayerRoom1)
    {
        currentPlayerRoom = currentPlayerRoom1;
    }
    
    /**
     * This method gets the description of the monster
     * @return description The description string the monster
     */
    public String getDescription()
    {
        return description;
    }
}

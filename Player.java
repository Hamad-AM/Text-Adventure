import java.util.Stack;
/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This Class describes the player.
 * Holding information such as the weight of the items the player is holding
 * As well as the previous rooms the player has been to, including checkpoints.
 * 
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class Player extends ActionEntity
{

    private Stack<Room> previousRoom;           // The previous room the player is in
    private Room checkPoint;


    /**
     * Constructor for objects of class Player
     */
    public Player(float maxHP, int weight)
    {
        super("player", weight, maxHP);
        previousRoom = new Stack<>();

    }
    
    
    /**
     * This method adds the previously visited room to the stack of previousRooms
     * @param oldRoom is the previously visited room
     */
    private void setPreviousRoom(Room oldRoom)
    {
        this.previousRoom.push(oldRoom);
    }
    
    
    
    /**
     * This method removes the most recent room from the stack
     * @return return the most recent visted room
     */
    private Room getPreviousRoom()
    {
        return previousRoom.pop();
    }
    

    /**
     * This method sets the new check point room for the player
     * @param room the new checkpoint room for the player
     */
    public void setCheckPoint(Room room)
    {
        checkPoint = room;
    }


    /**
     * This method retrieves the checkpoint room for the player
     * @return checkPoint the checkpoint room for the player
     */
    public Room getCheckPoint()
    {
        return checkPoint;
    }

    /**
     * This method moves the player to another room dependent on the direction
     * and adds the previously visited room to the previousRoom stack
     * @param direction The direction of the new room
     */
    public void goRoom(String direction)
    {
        Room nextRoom = this.getCurrentRoom().getExit(direction);
    
        // Check if room exists
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            // Add previous room to stack
            this.setPreviousRoom(this.getCurrentRoom());
            this.setCurrentRoom(nextRoom);

            // Heal player if they are at the checkpoint
            healAtCheckPoint();

            printUpdateRoom();
        }
    }

    /**
     * This method heals the player if they are at their checkpoint
     */
    private void healAtCheckPoint()
    {
        if(this.getCurrentRoom().equals(this.getCheckPoint())) {
            this.setHP(this.getMaxHP());
        }
    }

    /**
     * This method sends the player back to the preivous Room
     */
    public void goBack()
    {
        // Check if the player has any previous rooms
        if (this.previousRoom.empty()) {
            System.out.println("You can go no further back!");
        } else {
            this.setCurrentRoom(this.getPreviousRoom());
            printUpdateRoom();
        }
    }
    
    /**
     * This method prints out the information needed when the player moves to a new room
     */
    private void printUpdateRoom()
    {
        System.out.println(this.getCurrentRoom().getLongDescription());
    }
}

/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This Class is the Character class, describes the state of a character.
 * such as the location of it in the game world.
 * 
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */

 
public class Character extends Entity
{
    // instance variables
    private long startTime;                         // Holds the starting time of the timer
    private long waitTimeInRoom;                    // The amount of time in nanoseconds the character will wait in the room
    private static Room currentPlayerRoom;          // The Current Room the player is in

    private String description;                     // A description of the character

    RandomRoomGenerator randRoom;

    private String[] dialogue = {"Hello", "Thank you.", "No need for that."};      // The dialogue options of the character
    private int currentDialogueIndex = 0;                // Current dialogue option

    private boolean questComplete = false;           // Stores the value if the characters quest is complete

    private String questItem = "";              // Items name needed for quest

    /**
     * Constructor for objects of class Character
     */
    public Character(String name, String description, String introDialogue, long waitTimeInRoom)
    {   
        super(name, 100000);
        this.description = description;

        this.waitTimeInRoom = waitTimeInRoom;

        randRoom = new RandomRoomGenerator();

        dialogue[0] = introDialogue;

        // Start the timer for the characters movement
        startTime = System.nanoTime();
    }
    

    /**
     * This method changes the players current position to the next room in the path
     */
    private void move()
    {
        setCurrentRoom(randRoom.generateExit(getCurrentRoom()));
    }
    

    /**
     * This method updates the position of the player mased on the timer
     */
    public void update()
    {
        // Get the current time
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - startTime;
        
        // check if the player is currently not in the room and the elapsed time is greater than the waitTimeiInRoom
        if(!playerInRoom() && elapsedTime >= waitTimeInRoom){
            // move the character to the next room
            move();

            // Start the timer again
            startTime = System.nanoTime();
        }
        
        // if the player is in the room return print the description of the character
        if(playerInRoom()) {
            System.out.println(description);
            System.out.println(this.getName() + " : \"" + dialogue[currentDialogueIndex] + "\" ");
        }
    }


    /**
     * This method checks if the player is the same room as the character
     * @return true if the player is in the room
     */
    public boolean playerInRoom()
    {
        if (currentPlayerRoom.getName().equals(getCurrentRoom().getName())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method returns the dialogue if they have recived the wanted item
     * @return A string of the dialogue
     */
    public String getStatifiedDialogue()
    {
        return dialogue[1];
    }


    /**
     * This method sets the dialogue if for being given what they wanted
     * @param d The string of the dialogue
     */
    public void setStatifiedDialogue(String d)
    {
        dialogue[1] = d;
    }


    /**
     * This method returns the dialogue if they recieved an item they don't need
     * @return The second dialogue from the list
     */
    public String getFailedDialogue()
    {
        return dialogue[2];
    }

    /**
     * This method sets the dialogue if they were not given the item they wanted
     * @param d The string of the dialogue
     */
    public void setFailedDialogue(String d)
    {
        dialogue[2] = d;
    }


    /**
     * Set quest items name
     * @param itemName The Items name needed for a quest as a String
     */
    public void setQuestItem(String itemName)
    {
        this.questItem = itemName;
    }

    /**
     * @return the quest items name
     */
    public String getQuestItem()
    {
        return questItem;
    }


    /**
     * This method updates the currentPlayers position in the character class
     * @param currentPlayerRoom
     */
    public static void setCurrentPlayerRoom(Room currentPlayerRoom1)
    {
        currentPlayerRoom = currentPlayerRoom1;
    }

    
    /**
     * This method sets the quest to complete
     */
    public void questCompleted()
    {
        currentDialogueIndex = 1;
        questComplete = true;
    }


    /**
     * @return questComplete true if quest is finished for this character otherwise false
     */
    public boolean isQuestCompleted()
    {
        return questComplete;
    }
}

import java.util.Random;
/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This class is to generate random aspects of the game.
 * Current generates a random valid exit for a specfic room and a random room from a root room.
 *
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class RandomRoomGenerator
{

    // Inialise the random number generator with the time as a seed
    private Random rand = new Random(System.nanoTime());
    // The possible directions from a room
    private static String[] directions = {"north", "south", "west", "east"};


    /**
     * Creates a new RandomNumberGenerator
     */
    public RandomRoomGenerator() {}

    /**
     * This method generates a valid random exit for a room
     * @param currentRoom The room for which to get an exit from
     * @return The new Room to go to
     */
    public Room generateExit(Room currentRoom)
    {
        // Generate the exit direction
        int directionIndex = rand.nextInt(4);

        // Keep generating new exits until a valid exit is found
        while (currentRoom.getRoomFromExits(directions[directionIndex]) == null) {
            directionIndex = rand.nextInt(4);
        }

        return currentRoom.getRoomFromExits(directions[directionIndex]);
    }


    /**
     * This method find a random room
     * @param currentRoom The root room from which all other rooms are traversed
     * @return The final random room chosen
     */
    public Room generateRoom(Room currentRoom)
    {
        int options = rand.nextInt(100);
        
        // check if finding a new room should be finished. 5% chance of stopping
        if (options <= 5) {
            // Return the room chosen
            return currentRoom;
        } else {
            // Find new exit from the room and repeat
            return generateRoom(generateExit(currentRoom));
        }
    }
}

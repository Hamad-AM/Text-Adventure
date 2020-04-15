/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This class is subclass that inherits from the room class.
 * It sends the player to a random room when exited.
 *
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public class MagicTransporterRoom extends Room
{
    
    public MagicTransporterRoom(String name, String description) {
        super(name, description);
    }
    
    /**
     * This method returns a random room
     * @return Room a random room generated
     */
    @Override
    public Room getExit(String direction)
    {
        RandomRoomGenerator rrg = new RandomRoomGenerator();
        
        // The root room from which a new room is generated
        Room exitRoom = getRoomFromExits(direction);

        return rrg.generateRoom(exitRoom);
    }
}
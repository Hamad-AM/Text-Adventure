import java.util.HashMap;
/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This is an Interface which defines the function header to exectute a command
 * 
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */
public interface CommandAction
{
    /**
     * This method defines the command execution
     * @param command The command inputed by the player
     * @param player  The player object to access the players data
     * @param characters The characters collection to retrieve character information
     * @param monsters  The monsters collection to retrieve the monsters information
     * @return true if the player wants to quit, false if the game is to be completed
     */
    boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters);
}

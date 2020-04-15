/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public enum CommandWord
{
    HELP("help"), GO("go"), QUIT("quit"), TAKE("take"), DROP("drop"), BACK("back"), OPEN("open"), GIVE("give"), LOOK("look"), ATTACK("attack"), USE("use"), STATS("stats"), EQUIP("equip"), HINT("hint"),UNKNOWN("?");

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
}

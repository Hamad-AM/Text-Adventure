import java.util.HashMap;

/**
 * This class ia part of the "LIMBO" application.
 * "LIMBO" is a very simple text based adventure game.
 * 
 * This class describes the state and the choices are made in a battle.
 * 
 * A battle is updates ever time a command is inputed, in which the player completes a choice
 * and a choice for the monster is made. A battle is over when either the player or monster has
 * went below 0 hit points.
 *
 * @author Areeb Hamad Mohammed
 * @version 2019.23.11
 */


public class BattleMode
{
    // instance variables - replace the example below with your own
    private boolean over;   // Value that stores if the battle is over

    private Monster opponent;   // The monster the player is fighting against
    private Player player;
    
    private HashMap<String, Monster> monsters;

    /**
     * Creates the battle and initalises the objects for the battle
     */
    public BattleMode(Monster opponent, Player player, HashMap<String, Monster> monsters)
    {
        this.opponent = opponent;
        this.player = player;
        this.monsters = monsters;
        startBattleOutput();
    }


    /**
     * The method controls the routine and updates the state of the battle
     * @param command The command input by player
     */
    public void update(Command command)
    {

        CommandProcessor commandProcessor = new CommandProcessor();
        over = commandProcessor.execute(command, player, null, monsters);

        // Stop battle if the player isn't in the room
        if(!opponent.isPlayerInRoom()) {
            over = true;
        } else {
            System.out.println("");

            doOpponentTurn();
 
            if (player.getCurrentHP() <= 0) {
                playerDeath();
            }
        }
    }


    /**
     * This method outputs the information needed at the start of the battle
     */
    private void startBattleOutput()
    {
        System.out.println();
        System.out.println(opponent.getName() + " is getting ready to attack you what will you do?");
        System.out.println("You look closely at " + opponent.getName() + ".");
        System.out.println();
        System.out.println(opponent.getDescription());
        System.out.println();
        System.out.println("It seems " + opponent.getName() + " is holding a " + opponent.getWeapon().getName() + ".");
        System.out.println("If you don't want to fight you can escape to the previous room by typing back!");
        System.out.println();
    }


    /**
     * This method defines the monsters decisions
     */
    private void doOpponentTurn()
    {
        
        // Check if the monster is dead, else complete the opponents turn
        if (opponent.getIsDead()) {
            System.out.println("You have defeated " + opponent.getName() + ".");
            over = true;
        } else {
            player.doDamage(opponent.getWeapon().use());

            printOpponentAttack();
        }
    }


    /**
     * This method outputs the message when the opponent attacks
     */
    private void printOpponentAttack()
    {
        // update player on his current stats and what the monster did
        System.out.println(opponent.getName() + " attacked you!");
        System.out.println(opponent.getName() + " did " + opponent.getWeapon().getDamage() + " to you! ");
        System.out.println("You now have " + player.getCurrentHP() + "HP left!");
    }


    /**
     * This method describes what occurs on the players death
     * Currently send them back to their checkpoint and sets their HP back to max.
     * As well as sets the opponenets HP to max.
     */
    private void playerDeath()
    {
        // Reset the hitpoints of the player
        player.setHP(player.getMaxHP());

        // send player back to the last checkpoint
        player.setCurrentRoom(player.getCheckPoint());

        System.out.println("You were defeated by " + opponent.getName() + ".");
        System.out.println("As a result you have been sent to your last checkpoint!");
        over = true;
        // Reset the monsters hitpoints
        opponent.setHP(opponent.getMaxHP());
    }


    /**
     * This method gets if the game is over
     * @return over Returns true if the game is over vice versa
     */
    public boolean isOver()
    {
        return over;
    }
}

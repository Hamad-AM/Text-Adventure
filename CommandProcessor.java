import java.util.HashMap;

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

public class CommandProcessor implements CommandAction
{
    private static final HashMap<String, CommandAction> COMMANDS = new HashMap<>();     // Stores the commands with their relative commands operations

    public CommandProcessor()
    {

        COMMANDS.put(CommandWord.GO.toString(), new CommandAction(){
            /**
             * Try to in to one direction. If there is an exit, enter the new
             * room, otherwise print an error message.
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    // if there is no second word, we don't know where to go...
                    System.out.println("Go where?");
                    return false;
                }
        
                String direction = command.getSecondWord();
        
                player.goRoom(direction);

                return false;
            }
        });





        COMMANDS.put(CommandWord.ATTACK.toString(), new CommandAction(){
        
            /**
             * This method allows the player to attack monster
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    // if there is no second word, we don't know where to go...
                    System.out.println("Attack who? You missed your opportunity to act!");
                    return false;
                }

                Weapon weaponUsed = player.getWeapon();

                Monster opponent = monsters.get(command.getSecondWord());

                // If there is no opponent or the player isnt in the room fail attack
                if(opponent == null || !opponent.isPlayerInRoom()) {
                    System.out.println("No such person exists, you missed your opportunity to attack!");
                    return false;
                }

                if(weaponUsed == null) {
                    System.out.println("You have no such weapon! Your opponent seems have gotten the upper hand!");
                } else {
                    opponent.doDamage(weaponUsed.use());
                    System.out.println("You attacked " + opponent.getName());
                    System.out.println(opponent.getName() + " now only has " + opponent.getCurrentHP() + "HP left!");
                }

                return false;
            }
        });





        COMMANDS.put(CommandWord.BACK.toString(), new CommandAction(){
        
            /**
             * This method sends the player back to the previous room they were in
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                player.goBack();
                return false;
            }
        });





        COMMANDS.put(CommandWord.DROP.toString(), new CommandAction(){
            
            /**
             * This method drops a specified item from the players inventory
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    System.out.println("Drop which item");
                    return false;
                }
        
                String itemName = command.getSecondWord();
        
                // Get item from the players inventory
                Item itemToDrop = player.getItemInInventory(itemName);
        
                // Null if no item is retrevied, therefore item is not in the players inventory
                if (itemToDrop == null) {
                    System.out.println("There is no such item in your inventory!");
                } else {
        
                    System.out.println("You have dropped the "+itemName+", in the room"+player.getCurrentRoom().getLongDescription());
                    player.dropItem(itemToDrop);
                }

                return false;
            }
        });





        COMMANDS.put(CommandWord.TAKE.toString(), new CommandAction(){
            
            /**
             * This method allows the player to take an item and place in their inventory
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    System.out.println("Pick up which item?");
                    return false;
                }
                
                // Get the items name to be picked up
                String itemName = command.getSecondWord();
        
                // Retrieve said item from the room otherwise returns null
                Item itemToPickup = player.getCurrentRoom().getItem(itemName);
                
                // if itemToPickup is null, item is current not in the room 
                if (itemToPickup == null) {
                    System.out.println("There is no such item in this room!");
                }
                // check if player has enough carrying capacity to pickup item 
                else if( (itemToPickup.getWeight() + player.getCurrentWeight()) > player.getMaxWeight()) {
                    System.out.println("You cannot carry this item, it is too heavy currently.");
                } else {
                    System.out.println("You have taken the "+itemName+".");
                    player.addItem(itemToPickup);
                    // Remove item from the room if taken
                    player.getCurrentRoom().removeItem(itemToPickup);
                }

                return false;
            }
        });





        COMMANDS.put(CommandWord.GIVE.toString(), new CommandAction(){

            /**
             * This method allows the player to give an item to another character
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                // Check if either the second or the third word is not stated
                if (!command.hasSecondWord() || !command.hasThirdWord()) {
                    System.out.println("What would you like to give and to whom?");
                    return false;
                }
                
                
                String itemToGiveName = command.getSecondWord();
                String characterToGiveName = command.getThirdWord();
                
                // Check if the player has the item in their inventory
                if (player.isItemInInventory(itemToGiveName)) {

                    Character characterToGive = characters.get(characterToGiveName);

                    // Check if the character is real and if the player is in the same room as the character
                    if(characterToGive != null && characterToGive.playerInRoom()) {

                        // Tell player what item was given to the Character
                        System.out.println("You gave " + characterToGive.getName() + " your " + itemToGiveName + ".");

                        // Retrieve the item from the players inventory
                        Item itemToGive = player.getItemInInventory(itemToGiveName);

                        // Only give item to character if they need it for a quest
                        if (characterToGive.getQuestItem().equals(itemToGiveName)) {
                            
                            characterToGive.addItem(itemToGive);
                            characterToGive.questCompleted();

                            player.removeItem(itemToGive);

                        } else {
                            System.out.println(characterToGiveName + " : \"" + characterToGive.getFailedDialogue() + "\"");
                        }

                    } else {
                        System.out.println("This person does not exist, or is currently not in this room.");
                    }

                } else {
                    System.out.println("You do not have such an item in your posession.");
                }

                return false;
            }
        });





        COMMANDS.put(CommandWord.OPEN.toString(), new CommandAction(){

            /**
             * This method allows the player to open their inventory
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    System.out.println("What would you like to open?");
                    return false;
                }
        
                String toOpen = command.getSecondWord();
        
                // Check what the player wants to open
                if (toOpen.equals("inventory")) {
                    System.out.println("Your inventory contains : ");
                    // Print out the contents of the players inventory
                    System.out.println(player.getInventory());
                } else {
                    System.out.println("You are unable to open that!");
                }

                return false;
            }
        });





        COMMANDS.put(CommandWord.LOOK.toString(), new CommandAction(){
            
            /**
             * This method outputs items and exits in the players current room
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                System.out.println(player.getCurrentRoom().getLongDescription());

                return false;
            }
        });





        COMMANDS.put(CommandWord.USE.toString(), new CommandAction(){
            
            /**
             * This method allows the player to use items such a potions
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    System.out.println("Use what item? You took to long to react! ");
                    return false;
                }
        
                Item itemToUse = player.getItemInInventory(command.getSecondWord());
        
                if(itemToUse == null) {
                    System.out.println("You have no such item, it seems you've lost your turn!");
                } else {
                    if(itemToUse.getName().equals("Potion")) {
                        player.removeItem(itemToUse);
                        player.setHP(5);
                    }
                }

                return false;
            }
        });





        COMMANDS.put(CommandWord.QUIT.toString(), new CommandAction(){
            
            /**
             * This method allows the player to quit the game
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                }
                else {
                    return true;  // signal that we want to quit
                }

            }
        });




        COMMANDS.put(CommandWord.STATS.toString(), new CommandAction(){
            
            /**
             * This method returns the current attributes of the player
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {    
                System.out.println("You have " + player.getCurrentHP() + "HP currently!");
                System.out.println("You have a max HP of " + player.getMaxHP()+".");

                // return weapons damage if they have one
                if(player.getWeapon() != null) {
                    System.out.println("You're currently equiped weapon does " + player.getWeapon().getDamage() + " points of damage.");
                }

                return false;
            }
        });


        COMMANDS.put(CommandWord.EQUIP.toString(), new CommandAction(){

            /**
             * This method alows the player to equip a weapon form their inventory
             */
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters)
            {
                if(!command.hasSecondWord()) {
                    System.out.println("Equip which weapon?");
                    return false;
                }
                
                // Get the items name to be picked up
                String itemName = command.getSecondWord();
        
                if(player.isItemInInventory(itemName)) {

                    // Check if specfied item is a weapon
                    if(!(player.getItemInInventory(itemName) instanceof Weapon)) {
                        System.out.println("This item is not a weapon!");
                    } else {
                        // If player has weapon already equiped, drop said item
                        if (player.getWeapon() != null) {
                            player.getCurrentRoom().addItem(player.getWeapon());
                        }
                        
                        player.setWeapon((Weapon)player.getItemInInventory(itemName));
                        player.removeItem(itemName);
                    }
                } else {
                    System.out.println("Such an item does not exist in your inventory!");
                }
                
                return false;
            }
        });

        COMMANDS.put(CommandWord.HINT.toString(), new CommandAction(){
            @Override
            public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters) {
                System.out.println();
                System.out.println("Current position of all the characters");
                System.out.println("Merchant is currently in: " + characters.get("Merchant").getCurrentRoom().getName());
                System.out.println("TallDwarf is currently in: " + characters.get("TallDwarf").getCurrentRoom().getName());
                System.out.println("NiceDemon is currently in: " + characters.get("NiceDemon").getCurrentRoom().getName());
                System.out.println();
                System.out.println("You might find a pebble with other rocks.");
                System.out.println("Maybe someone is currently using a gun.");
                System.out.println("Maybe the slime had from goo on them.");
                System.out.println();
                return false;
            }
        });
    }


    public boolean execute(Command command, Player player, HashMap<String, Character> characters, HashMap<String, Monster> monsters) 
    {
        CommandAction commandAction = COMMANDS.get(command.getCommandWord());

        /**
         * This checks if a valid command has been inputed
         */
        if(commandAction == null) {
            System.out.println("Invalid command!");
            return false;
        }

        return commandAction.execute(command, player, characters, monsters);
    }
}


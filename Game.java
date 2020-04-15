import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.
 * 
 * @author  Michael Kölling, David J. Barnes and Areeb Hamad Mohammed
 * @version 2016.02.29
 */

public class Game
{
    // Instance variables
    private Parser parser;

    private Player player;                            // The player object containing all the players data

    private HashMap<String, Character> characters;    // A collection of all the Non-playable-Characters(NPC) in the game
    private HashMap<String, Monster> monsters;        // A collection of all the monsters in the game

    private boolean doBattle = false;                 // The value tells us if the conditions for battle are completed
    private BattleMode currentBattle;                 // Stores the current battle that is taking place
    private String currentOpponent;                   // The monster which the player is fighting
    
    private boolean[] conditionsToWin = {false, false, false};  // The conditions to win the game


    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        
        // Initialise the player with 50 hit points
        player = new Player(50, 50);
        
        characters = new HashMap<>();
        monsters = new HashMap<>();

        // Generates the characters and monsters
        generateEntities();

        // Creates the rooms and the items stored in them
        createRooms();
    }
    
    
    /**
     * This method generates all the characters and monsters of the game
     */
    private void generateEntities()
    {
        Monster dudeBro, slime, ogre, orc, theRock, finalBigBadGuy;

        Character merchant, tallDwarf, niceDemon;

        Weapon woodenSword, goo, sledgeHammer, pebble, pistol, bazooka;


        // Create the weapons of the monsters
        goo = new Weapon("goo", 1, "not sure if this is a weapon", 4, 100);
        woodenSword = new Weapon("WoodenTrainingSword", 10, "wooden training sword", 2, 100);
        sledgeHammer = new Weapon("SledgeHammer", 100, "a might sledge hammer", 8, 200);
        pebble = new Weapon("Pebble", 1, "a pebble that unleashes bruetal damage", 16, 500);
        pistol = new Weapon("Pistol", 10, "but there is no ammo", 18, 300);
        bazooka = new Weapon("Bazooka", 50, "a bazooka, yes a bazooka", 20, 300);
        
        // Create the monsters
        dudeBro = new Monster("DudeBro", "Just some dude who is a bro", 20f, woodenSword);
        slime = new Monster("Slime", "Just a ball of the slime", 5f, goo);
        ogre = new Monster("Ogre", "This guy is a just an intern", 30f, pistol);
        orc = new Monster("Orc", "Omg it's Meryl Streep dressed up as an orc", 40f, sledgeHammer);
        theRock = new Monster("TheRock", "Yes this is the famous actor rock", 50f, pebble);
        finalBigBadGuy = new Monster("FinalBigBadGuy", "The Final Boss is famous actor Keanu Reeves?", 50f, bazooka);

        // Create the Characters
        merchant = new Character("Merchant", "It looks like a ghostly merchant, wandering around.", "I would like some goo to fix my kart?", 20000000000L);
        tallDwarf = new Character("TallDwarf", "An unusualy tall dwarf, wobbles around. ", "I would like a pebble to add to my collection!", 20000000000L);
        niceDemon = new Character("NiceDemon", "An unexpectedly nice demon.", "Would you be so kind to give me a pistol! ", 20000000000L);

        // Set items the characters need
        merchant.setQuestItem("goo");
        tallDwarf.setQuestItem("Pebble");
        niceDemon.setQuestItem("Pistol");

        // Store monsters in their collection
        monsters.put(dudeBro.getName(), dudeBro);
        monsters.put(slime.getName(), slime);
        monsters.put(ogre.getName(), ogre);
        monsters.put(orc.getName(), orc);
        monsters.put(theRock.getName(), theRock);
        monsters.put(finalBigBadGuy.getName(), finalBigBadGuy);

        // Store the characters in their collection
        characters.put(merchant.getName(), merchant);
        characters.put(tallDwarf.getName(), tallDwarf);
        characters.put(niceDemon.getName(), niceDemon);
    }
    

    /**
     * This method generetes the rooms of the game, and initialises player and monster positions
     */
    private void createRooms()
    {
        Room hub, safeRoom, introBattle, intersection, blueRoom, ogreRoom, orcRoom, stickRoom, rockRoom, greenRoom, intermediate, endRoom;
        MagicTransporterRoom mysteryRoom;

        Item potion1, rocks;
        Weapon twig, sword;


        // Create the rooms
        hub = new Room("Hub", "in a plain room, you feel something unsettling. ");
        safeRoom = new Room("SafeRoom", " in the safe room. Don't go alone take this.\nCome back here to heal.");
        introBattle = new Room("IntroBattle", " in the room with an odd looking dude in the coner of the room. ");
        intersection = new Room("Intersection", " at an intersection you have not many options");
        blueRoom = new Room("BlueRoom", "at a room that has a blue tint. ");
        greenRoom = new Room("GreenRoom", "at a room that has a green tint. ");
        ogreRoom = new Room("OgreRoom", "at a room that is very orgrey. ");
        orcRoom = new Room("OrcRoom", "at a room that is very orcy");
        stickRoom = new Room("StickRoom", " at a room filled with sticks, but there seems to be a special one. ");
        rockRoom = new Room("RockRoom", " at a room filed with rocks, is that the rock? ");
        intermediate = new Room("Intermediate", " at a room of no significance. No point in staying here. ");
        endRoom = new Room("EndRoom", "at the end nothing much else to do.");

        mysteryRoom = new MagicTransporterRoom("MysteryRoom", "at a room that is quite mysterious and spooky.");

        // Initialise the exits for the rooms
        hub.setExit("west", safeRoom);
        hub.setExit("east", introBattle);
        hub.setExit("north", intersection);

        intersection.setExit("south", hub);
        intersection.setExit("west", blueRoom);
        intersection.setExit("north", orcRoom);
        
        introBattle.setExit("west", hub);
        introBattle.setExit("east", mysteryRoom);

        safeRoom.setExit("east", hub);

        blueRoom.setExit("east", intersection);
        blueRoom.setExit("west", ogreRoom);

        orcRoom.setExit("south", intersection);
        orcRoom.setExit("west", endRoom);
        orcRoom.setExit("north", stickRoom);

        ogreRoom.setExit("east", blueRoom);
        ogreRoom.setExit("north", intermediate);
        
        stickRoom.setExit("south", orcRoom);
        stickRoom.setExit("west", endRoom);
        stickRoom.setExit("east", rockRoom);

        rockRoom.setExit("west", stickRoom);
        rockRoom.setExit("south", greenRoom);

        greenRoom.setExit("north", rockRoom);

        intermediate.setExit("south", ogreRoom);
        intermediate.setExit("north", endRoom);

        endRoom.setExit("east", stickRoom);
        endRoom.setExit("south", intermediate);

        mysteryRoom.setExit("west", introBattle);



        // Create the items
        potion1 = new Item("Potion", true, 1, "a potion that heals you");
        rocks = new Item("Rocks", false, 100, "some pile of rocks");
        
        // Create the weapons
        twig = new Weapon("Twig", 5,"just a twig, but also a powerful weapon. ", 30, 500);
        sword = new Weapon("Sword", 5, "just a sword. ", 20, 100);

        // Put items in the rooms
        stickRoom.addItem(twig);
        intersection.addItem(potion1);
        rockRoom.addItem(rocks);

        


        // Set the starting rooms for the monsters
        monsters.get("DudeBro").setCurrentRoom(introBattle);
        monsters.get("Slime").setCurrentRoom(intersection);
        monsters.get("Ogre").setCurrentRoom(ogreRoom);
        monsters.get("Orc").setCurrentRoom(orcRoom);
        monsters.get("TheRock").setCurrentRoom(rockRoom);
        monsters.get("FinalBigBadGuy").setCurrentRoom(endRoom);
        
        // Set the starting rooms for the characters
        characters.get("Merchant").setCurrentRoom(safeRoom);
        characters.get("TallDwarf").setCurrentRoom(stickRoom);
        characters.get("NiceDemon").setCurrentRoom(blueRoom);

        // Set starting room for the player
        player.setCurrentRoom(hub);

        player.setWeapon(sword);

        player.setCheckPoint(hub);

        // update the position for the player, for the monsters and characters
        Monster.setCurrentPlayerRoom(player.getCurrentRoom());
        Character.setCurrentPlayerRoom(player.getCurrentRoom());
        
    }


    /**
     * This method updates the current state of all the characters
     */
    private void updateCharacters()
    {
        // Update the position of the player for the characters
        Character.setCurrentPlayerRoom(player.getCurrentRoom());

        // Loop through and update the characters
        Set<String> keys = characters.keySet();
        for(String characterName : keys) {
            Character processingCharacter = characters.get(characterName);
            processingCharacter.update();
        }
    }
    

    /**
     * This method updates the current state of all the monsters
     */
    private void updateMonsters()
    {
        // Update the position of the player for the monsters
        Monster.setCurrentPlayerRoom(player.getCurrentRoom());

        Set<String> keys = monsters.keySet();
        Iterator<String> keysIt = keys.iterator();


        while(keysIt.hasNext()) {

            String monsterName = keysIt.next();
            Monster processingMonster = monsters.get(monsterName);

            //Drop item and remove monster if dead
            if (processingMonster.getIsDead() == true) {
                keysIt.remove();
            } else if (processingMonster.isPlayerInRoom()) {
                // Start battle if the player is in the same room as this monster
                doBattle = true;
                currentOpponent = monsterName;
            }
        }
    }

    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {

            Command command = parser.getCommand();

            // If the command inputed is help, do not count as a game tick
            if (command.getCommandWord() != null && command.getCommandWord().equals(CommandWord.HELP.toString())) {
                printHelp();
                command = parser.getCommand();
            }

            CommandProcessor commandProcessor = new CommandProcessor();
            finished = commandProcessor.execute(command, player, characters, monsters);
            
            // Check if the games win conditions are met
            if (checkIfGameWon()) {
                finished = true;
                printComplete();
            }

            // Update all the entities of the game
            updateCharacters();
            updateMonsters();

            // Initiate Battle if conditions are met
            if(doBattle) {
                battle();
            }

        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    
    /**
     * The loop and routine for a battle
     */
    private void battle()
    {
        // Initialise a new battle with the monster and player
        currentBattle = new BattleMode(monsters.get(currentOpponent), player, monsters);
        
        // Start the game loop for the battle
        while(!currentBattle.isOver()) {
            updateMonsters();
            Command command = parser.getCommand();
            currentBattle.update(command);

        }

        // Reset the battle after finished
        doBattle = false;
        currentBattle = null;
        currentOpponent = null;
    }
    

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("the creators of propertyviewer (asignemnt 1) brings you: ");
        System.out.println("            \"LIMBO\"           \n");
        System.out.println("    AN AREEB HAMAD M. GAME   \n");
        System.out.println("Based on a true story");
        System.out.println("[Characters and events have no relation to real life and are purely coincidental.]");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }


    /**
     * Print out information for completing the game
     */
    private void printComplete()
    {
        System.out.println();
        System.out.println("You have finished the game!");
        System.out.println();
    }


    /**
     * This method checks if the condition for the game to be won has been met
     * @return true if games win conditions have been met
     */
    private boolean checkIfGameWon()
    {
        // Such that the game doesn't have to check if the characters have the item
        // in their inventory if it has already been completed
        if(!conditionsToWin[0]) {
            
            if (characters.get("Merchant").isItemInInventory("goo")) {
                conditionsToWin[0] = true;
            }
        }
        if (!conditionsToWin[1]) {
            
            if(characters.get("NiceDemon").isItemInInventory("Pistol")) {
                conditionsToWin[1] = true;
            }
        }
        if (!conditionsToWin[2]) {
            
            if(characters.get("TallDwarf").isItemInInventory("Pebble")) {
                conditionsToWin[2] = true;
            }
        }
        return conditionsToWin[0] && conditionsToWin[1] && conditionsToWin[2];
    }
}

Project: limbo
Authors: Michael Kölling, David J. Barnes and Areeb Hamad Mohammed

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016
   
To start this application, create an instance of class "Game" and call its
"play" method.

Class Game
    Contains all the information to run the game.
    Such as the creation of the games objects.
    Taking input of the players commands
    Processing the game objects.
    Checking if the games win conditions have been met.
    
Class BattleMode
    Contains all the information when an new battle is started
    Processes all the information needed for a battle
    Completes decisions for the monster in the current battle
    
Enum CommandWord
    Contains the command words for the games

Class Command
    Contains all the information for a command the player has submitted
    
Interface CommandAction
    Describes the header for an execution of the a players command
    
Class CommandProcessor
    Describes what each command does and completes the commands task.
    Chooses the command based on the players input command.
    
Class Parser
    Reads the players input and attempts to interpret it.
    
Class CommandWords
    Holds all the enumerations of the game. It is used to recognise them.
    
Class Room
    A "Room" represents one location in the scenery of the game.

Class MagicTransporterRoom
    This class is subclass that inherits from the room class.
    It sends the player to a random room when exited.
    
Class RandomRoomGenerator
    This class is to generate random aspects of the game.
    Current generates a random valid exit for a specfic room and a random room from a root room.

Class Entity
    This Class holds the data for the Entities.
    The Data it holds includes the inventory, the room they are in their name.
    
Class Character
    This Class is the Character class, describes the state of a character.
    Such as the location of it in the game world.
    
Class ActionEntity
    This Class is the ActionEntity class, and inherits from the Entity class.
    This class holds the fields and methods necessary for entities that will be in battles.
    
Class Player
    This Class describes the player.
    Holding information such as the weight of the items the player is holding
    As well as the previous rooms the player has been to, including checkpoints.

Class Monster
    This Class is the Monster class, inherits from the ActionEntity class.
    This class checks if the monster is dead and if the monster is in the same room as the player

Class Item
    This class stores the data related to Items. And lets the user retrieve the relavent data.

Class Weapon
    This class Weapon is subclass that inherits from the item class.
    Holds the information for the amount of damage it does, and the all weapons can be pickedup
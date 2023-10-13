# Monopoly

## SYSC 3110 Project - Group 25

## Team Members and Contributions

* Kareem El Assad
  * Models.Property.java
  * Documentation
* Keefer Belanger
  * Player.java
  * Dice.Java
  * UML Diagrams
* Dana El Sherif
  * Play.java
  * Models.Board.java
* Chia-Yu Liu
  * Play.java

## Implemented Features

* A text-based playable version of Monopoly
* The game can be played with any number of computer players. (By default, the game will have three computer players.)
* Five commands are implemented:
  * `status`: Displays the current status of the game and of the players.
  * `buy`: The player purchases the property.
  * `pass`: The player passes on purchasing the property.
  * `help`: This keyword outputs the User Manual.
  * `quit`: The player quits the game.
* Rent is set at a fixed rate for each property at 10% of the purchase price.
* Bankruptcy occurs when the player's balance is less than zero.
  
## Known Issues

* Would like to add an extra command `Summary` that displays information about the game such as (not limited to):
  * All players and their information
  * The current turn
  * The number of turns played
  
* Currently the game can be played only with up to four total players. We plan on addressing this in the GUI stage of the project. The user will be able to specify any number of players.

* The help command currently links to the official rules of Monopoly. We plan on adding a manual for the game but we think that the game is self explanatatory at this moment and does not require one until furthur milestones.

* Will be cleaning up some of the code and making it more readable. A few different sections of the code could benefit from refactoring and encapsulation.

## Roadmap

* We plan on adding a GUI version of the game.
* We will be implementing GO, Railroads, and Utilities.
* We will be adding Houses and Hotels.
* We plan on fixing all known issues and any other bugs pointed out by the TA.
* We will be adding bots to the game for the player(s) to play with. This will help with furthur testing and simulating of the game.
* Will be adding a feature that allowes the players to agree to end the game at any time. Currently, the game will end when all but one player is bankrupt.

## UML Diagrams and Sequence Diagrams

![UML Diagram](/diagrams/img/Monopoly-UML.jpg)

![Sequence Diagram](/diagrams/img/Sequence-Diagram.jpg)

## Design Decisions

* We hard coded the Models.Board in Models.Board.java initially to easily test the game. This allowed us to test the game with a few different scenarios (such as a smaller board). In the next milestone, this will be converted to a JSON. We decided to design it so all of the classes are seperated with their own methods instead of having them all in one class to loosen the coupling of the classes so we could change that class without having a direct effect on the other classes. We also did this to increase the cohesion between classes so they could all have their own tasks to later be called by Play()

## User Manual

Once the user chooses to play Monopoly, they will be welcomed by the interface, and asked to enter the number of players plays (within range of 2-4).

The user will then be required to enter the name of each player in the game, and the first player will be prompted to play their turn.

During their turn, a player has the option to type “r” to roll the die, “q” to quit the game, “p” to check their status (e.g., money, position, and owned properties), and “h” for the list of monopoly rules.

Once the current player enters “r” to roll the die, the program will automatically roll two dice, and add the sum of the die to the player’s current position on the board.

If the player lands on an empty property, they interface will tell them that they landed on an empty tile and move on to the next player’s turn. 

However, if the player lands on an “Income Tax” tile, they will be deducted $200 like in the original monopoly game. 

If the player lands on property that is not owned, the program will check if they have enough money to buy the property and ask them if they would like to buy it. 

When that occurs, the player will be given the option to enter "b" to buy the property, or "p" to decline. 

However, if the property is owned by the player, it will be the next player’s turn, and if the player lands on property owned by another player, they will be required to rent, which, in this version, is a flat 10% of the property’s original price.

A player is automatically removed when they go bankrupt (e.g., run out of money), and the game automatically ends when there is one player left.

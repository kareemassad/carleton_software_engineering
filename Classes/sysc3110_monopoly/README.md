# Monopoly

![Monopoly](/diagrams/showcaseM4.png)

## SYSC 3110 Project - Group 25

##### Note: All team members believe that all team members contributed equally as not all work is reflected in the commits

## Team Members and Contributions

* Kareem El Assad
  * Models.Property.java
  * Refactoring of Play.java
  * Models.Board.json
  * Documentation for M1
  * Documentation for M2
  * Documentation for M3
  * JSON integration (see jsonify PR)
  * Hotel and House Classes
* Keefer Belanger
  * MonopolyFrame.java class
  * Player.java
  * Dice.Java
  * View.java
  * UML Diagrams for M1, M2, M3, M4
  * Sequence Diagrams for M1, M2, M3, M4
  * JTextAreaOutputStream
  * BoardFrame
  * Tests
* Dana El Sherif
  * Play.java
  * Models.Board.java
  * Redid MonopolyController.java during M3, because we can't start M3 until we have a functioning GUI
  * Edited MonopolyFrame for GUI
  * Refactored Play.java
  * Jail
  * Refactored all code
  * GamePlay
* Chia-Yu Liu
  * Play.java
  * MonopolyController.java
  * The AI that automatically plays the game
  * player status popup 
  * Monopoly Frame

## Implemented Features

* A GUI-based playable version of Monopoly
* Nine Buttons are available to the user:
  * `status`: Displays the current status of the game and of the players.
  * `buy`: The player purchases the property.
  * `pass`: The player passes on purchasing the property.
  * `help`: This keyword outputs the User Manual.
  * `quit`: The player quits the game.
  * `roll`: The player rolls the dice.
  * `pay jail fine`: The player can pay the jail fine to get out of jail.
  * `buy hotel`: The player buys hotel.
  * `buy house`: The player buys house.
  * `number of players drop down`: The players can choose how many players there are
  * `languages drop down menu`: The players can choose what language to use
* Rent is set at a fixed rate for each property at 10% of the purchase price.
* Bankruptcy occurs when the player's balance is less than zero.
* JSON files are used to store the board and the players.
* Unit testing is performed to ensure that the game is working properly.

## Known Issues

* Language cannot be changed through GUI but can be changed through code
* Save/Load not implemented(Ran out of time, spent most time refactoring)
* Rolled Double to exit jail not implemented(methods there)
* AI not working
* Printing issues to the text area(GUI issues)
* Quit button doesn't do anything


## UML Diagrams and Sequence Diagrams

![UML Diagram](/diagrams/img/M4-UML.png)

![Sequence Diagram](/diagrams/img/M4-Sequence-Diagram.png)

## Design Decisions

* We decided to use a JSON file to store the board and the players. This will allow us to easily create different monopoly boards with international locations. We plan on adding different monopoly boards in different languages.
* We decided to design it so all of the classes are seperated with their own methods instead of having them all in one class to loosen the coupling of the classes so we could change that class without having a direct effect on the other classes. We also did this to increase the cohesion between classes so they could all have their own tasks to later be called by Play().
* Added dropdown menu to the GUI to allow the user to select the language.
* We added a pay jail fine button for when a player is in jail.
* It is not possible to buy a property that is owned by another player.

## User Manual

Once the user chooses to play Monopoly, they will be welcomed by the interface, and asked to enter the number of players (within range of 2-4).

The user will then be required to enter the name of each player in the game, and the first player will be prompted to play their turn.

During their turn, a player has the option of clicking a few buttons.

* Roll Button
* Quit Button
* Display Player Status Button
* Buy Button
* Pass Button
* Help Button
* Buy Button
* Pass Button

Once the current player clicks the roll button to roll the die, the program will automatically roll two dice, and add the sum of the die to the player’s current position on the board. It will also update the position of the player on the board.

If the player lands on an empty property, they interface will tell them that they landed on an empty tile and move on to the next player’s turn.

However, if the player lands on an “Income Tax” tile, they will be deducted $200 like in the original monopoly game.

If the player lands on property that is not owned, the program will check if they have enough money to buy the property and ask them if they would like to buy it.

When that occurs, the player will be given the option to click the buy button to buy the property, or to click the pass button to decline.

However, if the property is owned by the player, it will be the next player’s turn, and if the player lands on property owned by another player, they will be required to rent, which, in this version, is a flat 10% of the property’s original price.

A player is automatically removed when they go bankrupt (e.g., run out of money), and the game automatically ends when there is one player left.

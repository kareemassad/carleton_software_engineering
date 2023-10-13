# SYSC3303 Assignment 1

Author: Kareem El Assad - 101107739

Date: 2022-1-29

Note: This is originally a .md file, but converted to a .txt for the assignment specification.

## Run Instructions

1) Unzip 101107739_Assignment1.zip and place into Eclipse.
2) Run Kitchen.java.

## File Description

1) Kitchen.java
   - This is the main class for the program.
   - Initializes and starts the Chef and Agent threads.
   - Keeps track of sandwiches made.

2) Agent.java
    - Will randomly select 2 ingredients and will place them on the table.
    - After that it will notifyAll threads.

3) Chef.java
   - Will wait for the Agent to place 2 ingredients on the table.
   - The correct chef will add the missing ingredient to the table, create the sandwich, increment the number of sandwiches made, then notifyAll threads.

## Questions for TA

1) Why is @Override necessary for the run() methods? I wasn't able to get it to work without it, but the prof's Box example worked fine.
2) Why did I need to move the contents of the run() methods to their own seperate synchronized methods? It wouldn't run properly without it but the prof's Box example worked fine.
3) How can I deal with one of the threads being too fast? I used wait() and notifyAll() to make sure the other thread was waiting for the other thread to finish but that wasn't the case.

## UML

Can be found in the "UML Diagram.pdf" file.

## References

A substantial part of the assignment was based on the Box example from class.

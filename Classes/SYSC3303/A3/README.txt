# SYSC3303 Assignment 3

Author: Kareem El Assad - 101107739

Date: 2022-3-05

Note: This is originally a .md file, but converted to a .txt for the assignment specification.

## Run Instructions

1) Unzip 101107739_Assignment3.zip and place into Eclipse.
2) Run Server.java then Host.java then finally Client.java.

## File Description

### /src

* Contains the code for the project:
  * Client.java
  * Server.java
  * Host.java
* Also contains examples from class.
  * SimpleEchoClient.java
  * SimpleEchoServer.java

### /bin

* Contains the compiled code for the project.
  * Client.class
  * Server.class
  * Host.class

### /diagrams

* Contains the UML and Sequence Diagrams for the project.
  * Class Diagram.png
  * Sequence Diagram.png

Note: The software didn't let me get rid of or change the numbers in the sequence diagram. I tried to layer it sequentially instead.

### /docs

Javadocs for the project.

## Answers to Provided Questions

1. Why did I suggest that you use more than one thread for the implementation of the intermediate task?
   * The suggestion was meant to split the responsibility of receiving and sending messages between two threads. It is not necessary in this case, but it does make things significantly easier as it means there is likely always thread freed up to receive the messages.  
2. Is it necessary to use synchronized in the intermediate task? Explain.
   * It is necessary for the intermediate task to be synchronized as it is handling multiple requests at the same time. Without it, the system would run into issues with multiple threads trying to access the same data.

## References

The code for the project is largely based off the professor's examples.

# Team 15

Project is hosted on [Github](https://github.com/isbogdanov/sysc3303_project)

## Team Members

- Igor Bogdanov [101169300]
- Zeyad Elgendy [101129229]
- Kareem El Assad [101107739]
- Meia Copeland [100972654]
- Taiye Davies [101116265]

## File Naming

- The classes are contained in the `src` folder.
  - FloorController.java (Client): The Floor subsystem reads in tasks using the provided format.
  - SchedulerController.java (Server): The Scheduler handles the input and communicates with the Elevator and Floor subsystems.
  - ElevatorController.java (Client): Elevator subsystem.
  - SimulatonRun.java (Main) : Runs the simulation
  - Task.java: Helper class to handle input.
  - MainFlowTest.java: Reads the input and passes the data back and forth between the Floor and Elevator subsystems.

## Setup Instructions from Github

1. Clone the repository from Github

```bash
git clone https://github.com/isbogdanov/sysc3303_project.git
```

2. Run SimulationRun.java

The program should start up and run the simulation.

## Setup Instructions from .zip

1. Unpack the .zip file

2. Run SimulationRun.java

The program should start up and run the simulation.

## Testing Instructions

After setting up the project, you can run the tests by running the `MainFlowTest.java` file.

## Expected Sample Output

```bash
Floor is running
Elevator is running
Floor got new task and submits it to Scheduler: 14:05:15.000 2 up 4
-> Putting a task into: requests 14:05:15.000 2 up 4
Elevator got task from Scheduler (requests): 14:05:15.000 2 up 4   
Elevator completed the task: 14:05:15.000 2 up 4
-> Putting a task into: completed 14:05:15.000 2 up 4   
Floor got notified about completion: 14:05:15.000 2 up 4

Floor got new task and submits it to Scheduler: 14:10:16.100 3 up 7
-> Putting a task into: requests 14:10:16.100 3 up 7
Elevator got task from Scheduler (requests): 14:10:16.100 3 up 7
Elevator completed the task: 14:10:16.100 3 up 7
-> Putting a task into: completed 14:10:16.100 3 up 7
Floor got notified about completion: 14:10:16.100 3 up 7

Floor got new task and submits it to Scheduler: 14:15:17.120 6 down 1
-> Putting a task into: requests 14:15:17.120 6 down 1
Elevator got task from Scheduler (requests): 14:15:17.120 6 down 1
Elevator completed the task: 14:15:17.120 6 down 1
-> Putting a task into: completed 14:15:17.120 6 down 1
Floor got notified about completion: 14:15:17.120 6 down 1

Floor got new task and submits it to Scheduler: 14:20:18.123 1 up 5
-> Putting a task into: requests 14:20:18.123 1 up 5
Elevator got task from Scheduler (requests): 14:20:18.123 1 up 5
Elevator completed the task: 14:20:18.123 1 up 5
-> Putting a task into: completed 14:20:18.123 1 up 5
Floor got notified about completion: 14:20:18.123 1 up 5

Floor got new task and submits it to Scheduler: 14:27:19.004 5 up 10
-> Putting a task into: requests 14:27:19.004 5 up 10
Elevator got task from Scheduler (requests): 14:27:19.004 5 up 10
Elevator completed the task: 14:27:19.004 5 up 10
-> Putting a task into: completed 14:27:19.004 5 up 10   
Floor got notified about completion: 14:27:19.004 5 up 10

Floor got new task and submits it to Scheduler: 14:35:20.000 9 down 3
-> Putting a task into: requests 14:35:20.000 9 down 3
Elevator got task from Scheduler (requests): 14:35:20.000 9 down 3
Elevator completed the task: 14:35:20.000 9 down 3
-> Putting a task into: completed 14:35:20.000 9 down 3   
Floor got notified about completion: 14:35:20.000 9 down 3

Floor got new task and submits it to Scheduler: 14:40:15.000 2 down 1
-> Putting a task into: requests 14:40:15.000 2 down 1
Elevator got task from Scheduler (requests): 14:40:15.000 2 down 1
Elevator completed the task: 14:40:15.000 2 down 1
-> Putting a task into: completed 14:40:15.000 2 down 1
Floor got notified about completion: 14:40:15.000 2 down 1

No requests.
All tasks completed

```

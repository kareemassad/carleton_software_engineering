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
  - ElevatorController.java (Client): Responsible for controlling the Elevator subsystem. It is responsible for reading the input and passing the data back and forth between the Floor and Elevator subsystems.
  - Elevator.java: The Elevator subsystem.
  - SimulatonRun.java (Main) : Runs the simulation
  - Task.java: Helper class to handle input.
  - MainFlowTest.java: Main Testing framework.

## Setup Instructions from Github

1. Clone the repository from Github

```bash
git clone https://github.com/isbogdanov/sysc3303_project.git
```

2. Run `SimulationRun.java`

The program should start up and run the simulation.

## Setup Instructions from .zip

1. Unpack the `.zip` file

2. Run `SimulationRun.java`

The program should start up and run the simulation.

## Testing Instructions

After setting up the project, you can run the tests by running the `MainFlowTest.java` file.

## Expected Sample Output

```bash
Floor is running
Elevator is running
Floor got new task and submits it to Scheduler: 14:05:15.000 going to: 2
-> Putting a task into: requests 14:05:15.000 going to: 2
Elevator got task from Scheduler (requests): 14:05:15.000 going to: 2
Elevator is at: 1
Elevator is at: 2
Elevator arrived
Elevator completed the task: 14:05:15.000 going to: 2
-> Putting a task into: completed 14:05:15.000 going to: 2
Floor got notified about completion: 14:05:15.000 going to: 2

Floor got new task and submits it to Scheduler: 14:10:16.100 going to: 3
-> Putting a task into: requests 14:10:16.100 going to: 3
Elevator got task from Scheduler (requests): 14:10:16.100 going to: 3
Elevator is at: 2
Elevator is at: 3
Elevator arrived
Elevator completed the task: 14:10:16.100 going to: 3
-> Putting a task into: completed 14:10:16.100 going to: 3
Floor got notified about completion: 14:10:16.100 going to: 3

Floor got new task and submits it to Scheduler: 14:15:17.120 going to: 6
-> Putting a task into: requests 14:15:17.120 going to: 6
Elevator got task from Scheduler (requests): 14:15:17.120 going to: 6
Elevator is at: 3
Elevator is at: 4
Elevator is at: 5
Elevator is at: 6
Elevator arrived
Elevator completed the task: 14:15:17.120 going to: 6
-> Putting a task into: completed 14:15:17.120 going to: 6
Floor got notified about completion: 14:15:17.120 going to: 6

Floor got new task and submits it to Scheduler: 14:20:18.123 going to: 1
-> Putting a task into: requests 14:20:18.123 going to: 1
Elevator got task from Scheduler (requests): 14:20:18.123 going to: 1
Elevator is at: 6
Elevator is at: 5
Elevator is at: 4
Elevator is at: 3
Elevator is at: 2
Elevator is at: 1
Elevator arrived
Elevator completed the task: 14:20:18.123 going to: 1
-> Putting a task into: completed 14:20:18.123 going to: 1
Floor got notified about completion: 14:20:18.123 going to: 1

Floor got new task and submits it to Scheduler: 14:27:19.004 going to: 5
-> Putting a task into: requests 14:27:19.004 going to: 5
Elevator got task from Scheduler (requests): 14:27:19.004 going to: 5
Elevator is at: 1
Elevator is at: 2
Elevator is at: 3
Elevator is at: 4
Elevator is at: 5
Elevator arrived
Elevator completed the task: 14:27:19.004 going to: 5
-> Putting a task into: completed 14:27:19.004 going to: 5
Floor got notified about completion: 14:27:19.004 going to: 5

Floor got new task and submits it to Scheduler: 14:35:20.000 going to: 9
-> Putting a task into: requests 14:35:20.000 going to: 9
Elevator got task from Scheduler (requests): 14:35:20.000 going to: 9
Elevator is at: 5
Elevator is at: 6
Elevator is at: 7
Elevator is at: 8
Elevator is at: 9
Elevator arrived
Elevator completed the task: 14:35:20.000 going to: 9
-> Putting a task into: completed 14:35:20.000 going to: 9
Floor got notified about completion: 14:35:20.000 going to: 9

Floor got new task and submits it to Scheduler: 14:40:15.000 going to: 2
-> Putting a task into: requests 14:40:15.000 going to: 2
Elevator got task from Scheduler (requests): 14:40:15.000 going to: 2
Elevator is at: 9
Elevator is at: 8
Elevator is at: 7
Elevator is at: 6
Elevator is at: 5
Elevator is at: 4
Elevator is at: 3
Elevator is at: 2
Elevator arrived
Elevator completed the task: 14:40:15.000 going to: 2
-> Putting a task into: completed 14:40:15.000 going to: 2
Floor got notified about completion: 14:40:15.000 going to: 2

No requests. 
All tasks completed
```



# Changelog

All notable changes from the iteration to this project will be documented in this file.

## Iteration 2

### Added

- New entity class Elevator. Elevator updates its own local queue of tasks that will be implemented as priority queue in future iterations 
- New UML class and sequence diagrams.
- State machine diagram for the scheduler and elevator subsystems.
- Added state machine logic to the Elevator Controller class.
- Added task parsing to the Floor Controller class.

### Changed

- Moved the elevator from the Elevator Controller to it's own separate class.
- Changed logic of creating a task ogject, the constructor now takes floor and creation time. 
- Added additional parameters and applicable getter methods to the Task class. (e.g. request time, destination, etc.)
- Elevator can now move up and down the floors

### Removed

- None
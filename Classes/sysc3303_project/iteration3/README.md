# Team 15

Project is hosted on [Github](https://github.com/isbogdanov/sysc3303_project)

## Team Members

- Igor Bogdanov [101169300]
- Zeyad Elgendy [101129229]
- Kareem El Assad [101107739]
- Meia Copeland [100972654]
- Taiye Davies [101116265]

## File Naming
The classes are contained in the `src` folder.

- FloorController.java: The Floor subsystem reads in tasks using the provided format.
- SchedulerController.java (Server): The Scheduler handles the input and communicates with the Elevator and Floor subsystems.
- ElevatorController.java: Responsible for controlling the Elevator subsystem. It is responsible for reading the input and passing the data back and forth between the Floor and Elevator subsystems.
- Elevator.java: The Elevator subsystem.
- SimulatonRun.java (Main) : Runs the simulation
- Task.java: Helper class to handle input.
- MainFlowTest.java: Main Testing framework.
- InvalidRequestException: Handles the invalid request error custom exception.
- RequestsQueues: A class that handles putting, getting, and the organization of the Tasks.
- RPCClient: Client of the system.

## Setup Instructions from Github

1. Clone the repository from Github

```bash
git clone https://github.com/isbogdanov/sysc3303_project.git
```

2. Run `SchedulerController.java`, `ElevatorController.java`, then `FloorController.java`

The programs should start up and run the simulation.

## Setup Instructions from .zip

1. Unpack the `.zip` file

2. Run `SchedulerController.java`, `ElevatorController.java`, then `FloorController.java`

The programs should start up and run the simulation.

## Testing Instructions

After setting up the project, you can run the tests by running the `MainFlowTest.java` file.

## Expected Sample Output

### `SchedulerController.java`
```java
Sample code here
```
### `ElevatorController.java`
```java
Sample code here
```
### `FloorController.java`
```java
Sample code here
```

# Changelog

All notable changes from the iteration to this project will be documented in this file.

## Iteration 2

### Added

- Added a task generator where user can input number of floors and number of tasks, and the generator randomly assigns a floor for the elevator to travel to for each task.

- New entity class Elevator. Elevator updates its own local queue of tasks that will be implemented as priority queue in future iterations
- New UML class and sequence diagrams.
- State machine diagram for the scheduler and elevator subsystems.
- Added state machine logic to the Elevator Controller class.
- Added task parsing to the Floor Controller class.

### Changed

- Moved the elevator from the Elevator Controller to its own separate class.
- Changed logic of creating a task object, the constructor now takes floor and creation time.
- Added additional parameters and applicable getter methods to the Task class. (e.g. request time, destination, etc.)
- Elevator can now move up and down the floors

### Removed

- None

## Iteration 3

### Added
- `InvalidRequestException` to handle the invalid request error.
- `RPCClient` to facilitate the system.
- `RequestsQueues` to handle putting, getting, and the organization of the Tasks
- New UML class and sequence diagrams.

### Changed
- `ElevatorController` to handle multiple elevators. 
- `FloorController` to send requests to the `Elevator` and submit received requests to the `SchedulerController`
- Clarified the behavior and actions of the Elevators. 

### Removed
- `SimulationRun` as it is no longer in use
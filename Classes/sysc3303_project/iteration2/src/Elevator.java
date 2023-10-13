import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Elevator {
	
/**
 * The elevator itself, as to what is stored inside it.
 * The elevator has a currentFloor that tracks what floor
 * it's on, a Task currentTask that is supposed to be what the elevator
 * will do now, the queue of the elevator, that stores the tasks, and the
 * door.	
 */
	
	
 private int currentFloor;
 private Task currentTask;
 private int position;
 private ArrayDeque<Task> innerQueue;
 private int door;

 
 /**
  * Initialize the elevator with the current floor it's on
  * @param currentFloor the number of the floor the elevator is currently on
  */
 public Elevator(int currentFloor){
     this.currentFloor = currentFloor;
     innerQueue = new ArrayDeque<Task>();
     position = 0;
     door = 1;
 }

 /**
  * 
  * @return the current floor the elevator is on
  */
    public int getCurrentFloor() {
        return currentFloor;
    }

    
    /**
     * 
     * @param currentFloor once the elevator moves to a different floor, we use this to set the currentFloor of the elevator
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    
    /**
     * 
     * @param currentTask the task that the elevator will do now, whether it's go up, down, or open doors in case we are on the same floor.
     */
    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    /**
     * 
     * @param task add a task to the queue of the elevator
     */
    public void addToInnerQueue(Task task) {
        innerQueue.addFirst(task);
        if (currentTask == null) {
            currentTask = innerQueue.removeLast();
            updatePosition();
        }

    }

    
    /**
     * this updates the position of the elevator as to where it is on floors, NOT THE CURRENT FLOOR.
     */
    private void updatePosition(){
     position = currentTask.getDestination()-currentFloor;
    }


    /**
     * This moves one floor up or down. 
     * @return if we are on the same floor, return true. else, return false.
     */
    public boolean moveOneFloor() {
        if (position > 0 ) {
            currentFloor +=1;
            updatePosition();
        } else if (position < 0 ) {
            currentFloor -= 1;
            updatePosition();
        }
        return (position == 0);
    }

    
    /**
     * opens the door of the elevator, sets it to 0.
     */
    public void openDoor(){
     door = 0;
    }

    /**
     * closes the door of the elevator, sets it to 1.
     */
    public void closeDoor(){
        door = 1;
    }

    public void processFloor() {

    }

}

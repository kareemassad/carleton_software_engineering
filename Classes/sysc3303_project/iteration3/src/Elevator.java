import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import static java.lang.Math.abs;


public class Elevator implements Runnable {
	
/**
 * The elevator itself, as to what is stored inside it.
 * The elevator has a currentFloor that tracks what floor
 * it's on, a Task currentTask that is supposed to be what the elevator
 * will do now, the queue of the elevator, that stores the tasks, and the
 * door.	
 */
	
	
 private int currentFloor;
 private Task currentTask;
 private int positionToDestination;

 private PriorityQueue<Task> innerQueue;

 private int door;
 private String currentDirection;
 private int currentNumberOfPassengers;
 private boolean isIdle;
 private CompletionBuffer completionBuffer;
 private ArrayDeque<Task> incomingTasks;

 
 /**
  * Initialize the elevator with the current floor it's on
  * @param currentFloor the number of the floor the elevator is currently on
  */
 public Elevator(int currentFloor, CompletionBuffer completionBuffer, ArrayDeque<Task> incomingTasks ){

     this.currentFloor = currentFloor;

     this.incomingTasks = incomingTasks;

     innerQueue = new PriorityQueue<>(new TaskComparator());
     currentTask = null;
     positionToDestination = 0;
     door = 1;
     currentDirection = "idle";
     isIdle = true;
     this.completionBuffer = completionBuffer;
 }

    static class TaskComparator implements Comparator<Task> {

        @Override
        public int compare(Task task1, Task task2) {
            return task1.getPriority() - task2.getPriority(); //? 1 : -1;
        }
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

        synchronized (innerQueue) {
            System.out.println("Adding to inner Queue");

            task.setPriority(abs(currentFloor-task.getDestination()));
            innerQueue.add(task);

            System.out.println("Best task is " + innerQueue.peek().getTaskRawData());

            System.out.println("InnerQueue size: " + innerQueue.size());

            if (currentTask == null) {
                currentTask = innerQueue.poll();
                currentDirection = currentTask.getDirection();
                updatePosition();
                isIdle = false;
            }
        }
    }

    
    /**
     * this updates the position of the elevator as to where it is on floors, NOT THE CURRENT FLOOR.
     */
    private void updatePosition(){
     positionToDestination = currentTask.getDestination()-currentFloor;
    }


    /**
     * This moves one floor up or down. 
     * @return if we are on the same floor, return true. else, return false.
     */
    public boolean moveOneFloor() {
        if (positionToDestination > 0 ) {
            currentFloor +=1;
            updatePosition();
        } else if (positionToDestination < 0 ) {
            currentFloor -= 1;
            updatePosition();
        }
        updateState();
        return (positionToDestination == 0);
    }

    public void updateState(){

        checkOptimalTask();
    }

    public void  checkOptimalTask(){

        if (currentTask!= null) {

            synchronized (innerQueue) {
                innerQueue.add(currentTask);
                currentTask = null;

                Iterator<Task> activeTasks = innerQueue.iterator();

                PriorityQueue<Task> bufferInnerQueue = new PriorityQueue<>(new TaskComparator());

                while (activeTasks.hasNext()) {
                    Task task = activeTasks.next();
                    int priority = abs(currentFloor - task.getDestination());
                    task.setPriority(priority);
                    bufferInnerQueue.add(task);
                }

                innerQueue.clear();
                innerQueue = bufferInnerQueue;

                currentTask = innerQueue.poll();

            }
        }
    }

    /**
     * opens the door of the elevat`or, sets it to 0.
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

    public String getCurrentDirection() {
        return currentDirection;
    }

    public PriorityQueue<Task> getInnerQueue () {
        return innerQueue;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void run (){

        while (true) {
            System.out.println(Thread.currentThread().getName() + " is ready");
            // Prints where the elevator is at, adds some readability
            System.out.println(Thread.currentThread().getName()+ " is at: " + getCurrentFloor());

            if (!innerQueue.isEmpty() && currentTask != null) {
                System.out.println(Thread.currentThread().getName() + " is running");
                // If the elevator didn't arrive to destination, print where it's at now
                while (!moveOneFloor()) {
                    System.out.println(Thread.currentThread().getName()+  " is at: " + getCurrentFloor() ) ;
                }

                // Once done, print where the elevator is at now to make sure we are at the
                // right floor.
                System.out.println(Thread.currentThread().getName()+ " is at: " + getCurrentFloor());

                // Open the door for people to get out, wait some time, close the door, and then
                // set the current task to elevator to null.
                System.out.println(Thread.currentThread().getName()+ " arrived");
                openDoor();
                closeDoor();

                currentTask.setStatus("completed");
                //completionBuffer.putTask(currentTask);
                System.out.println(Thread.currentThread().getName()+ " completed the task: " + currentTask.getTaskRawData());
                setCurrentTask(null);
                //updateState();
                continue;
            } else {
                System.out.println(Thread.currentThread().getName()+ " is idle \n");
                isIdle = true;
                currentDirection = "idle";
            }

            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Cant sleep");
            }


        }
    }

}

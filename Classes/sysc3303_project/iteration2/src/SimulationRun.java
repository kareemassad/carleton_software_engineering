import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * A simulation run for the whole program.
 *
 */
public abstract class SimulationRun {
/**
 * 
 * @param args the command line arguments of the main method.
 * @throws InterruptedException if the file is not found, throw this exception.
 */
    public static void main(String[] args) throws InterruptedException {
    	//the two threads of the floor and elevator.
        Thread floor, elevator;
        //the scheduler class.
        SchedulerController scheduler = new SchedulerController();

        try {
        	//open the file of the input.
            FileReader elevatorTasks = new FileReader("elevator_tasks.txt");

            BufferedReader taskInput = new BufferedReader(elevatorTasks);

            //initialize the 2 threads
            floor = new Thread(new FloorController(taskInput, scheduler), "Floor");
            elevator = new Thread(new ElevatorController(scheduler), "Elevator");

            //start the 2 threads
            floor.start();
            elevator.start();

            //end the 2 threads
            floor.join();
            elevator.interrupt();
            
            taskInput.close();

        } catch (FileNotFoundException e) {
            System.out.println("No input file");
    	} catch (IOException e) {
    		System.out.println("Error");
    	}
    }
}

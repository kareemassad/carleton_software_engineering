import java.io.BufferedReader;
import java.io.IOException;

/**
 * 
 * The Floor subsystem, it's controller by the SchedulerController.
 *
 */
public class FloorController implements Runnable {

    private BufferedReader userInput;
    private SchedulerController scheduler;

    /**
     * 
     * @param userInput the data the class gets from the User.
     * @param scheduler The SchedulerController variable for the ElevatorController class that is used to communicate with the SchedulerController class.
     */
    public FloorController(BufferedReader userInput, SchedulerController scheduler) {
        this.scheduler = scheduler;
        this.userInput = userInput;
    }

    
   /**
    * Run method for the FloorController class
    * @throws InterruptedException If the thread is sleeping and it gets interrupted, this exception is thrown.
    * @throws IOException If the file can't be read, throw this exception 
    */
   public void run(){
       System.out.println("Floor is running");

       try {
           for (String line = userInput.readLine(); line != null; line=userInput.readLine()) {
        	   
        	   //create a proper request after we get the user input

               String[] parsed = line.split("\\s+");
               String requestTime = parsed[0];
               int pickupDestination = Integer.parseInt(parsed[1]);
               
               Task request = new Task(pickupDestination, requestTime);

               request.setStatus("requests");
               System.out.println("Floor got new task and submits it to Scheduler: " + request.getTaskRawData());
               
               //send the task to the scheduler
               scheduler.putTask(request, request.getStatus());
               Task task = scheduler.getTask("completed");

               System.out.println("Floor got notified about completion: " + task.getTaskRawData());
               System.out.println();
               try {
            	   //sleep for 500 milliseconds
                   Thread.sleep(500);
               } catch (InterruptedException e) {
            	   //throw the exception and print can't sleep
                   System.out.println("Cant sleep");
               }
           }
           return;
       }
        catch (IOException e) {
        	//can't read the file
           System.out.println("Cant read file");
        }
    }

}

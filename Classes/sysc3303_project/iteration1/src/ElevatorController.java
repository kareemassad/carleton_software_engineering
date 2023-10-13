/**
 * 
 * The Elevator subsystem, It's controlled by the SchedulerController.
 * 
 * @author Kareem Assad
 * @author Igor Bogdanov
 * @author Meia Copeland
 * @author Taiye Davies
 * @author Zeyad Elgendy
 * @version iteration1
 *
 *
 *
 */

public class ElevatorController implements Runnable {

	
  private SchedulerController scheduler;

  
  /** 
   * Initialization of the ElevatorController class
   * @param scheduler The SchedulerController variable for the ElevatorController class that is used to communicate with the SchedulerController class.
   * 
   * */
  public ElevatorController(SchedulerController scheduler) {
    this.scheduler = scheduler;
  }

  /**
   * Run method for the ElevatorController
   * 
   * @throws InterruptedException If the thread is sleeping and it gets interrupted, this exception is thrown.
   */
  public void run() {
    System.out.println("Elevator is running");

    while (true) {

      Task task = scheduler.getTask("requests");

      //If there is a task
      if (task != null) {
    	  
    	//Show that the class got the data  
        System.out.println("Elevator got task from Scheduler (requests): " + task.getTaskRawData());
        task.setStatus("completed");
        try {
          Thread.sleep(500); //Sleep for 500 milliseconds
        } catch (InterruptedException e) {
          System.out.println("Cant sleep");
        }
        //ElevatorController is done with the task
        System.out.println("Elevator completed the task: " + task.getTaskRawData()); 

        //Send the task back to the scheduler
        scheduler.putTask(task, task.getStatus());
      }
      //No task to process
       else {
        System.out.println("No requests. \nAll tasks completed");
         return;
      }

    }
  }
}

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

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

public class ElevatorController extends RPCClient {

  private ArrayList<Elevator> elevators;
  private ArrayList<Thread>  elevatorThreads;
  private ElevatorsMonitor elevatorsMonitor;
  private  CompletionBuffer completionBuffer;
  private HashMap<String, ArrayDeque<Task>> elevatorsTasks;


  public ElevatorController(int schedulerPort, int numberOfElevators) {
    super();

    completionBuffer = new CompletionBuffer();
    this.schedulerPort = schedulerPort;

    this.elevators = new ArrayList<Elevator>();
    this.elevatorThreads = new ArrayList<Thread>();
    elevatorsTasks = new HashMap<String, ArrayDeque<Task>>();
    int floor = 1;

    for (int i=0; i< numberOfElevators; i++) {
      String elevatorName = "elevator_"+(i+1);
      elevatorsTasks.put(elevatorName, new ArrayDeque<Task>());

      Elevator elevator = new Elevator(floor, completionBuffer, elevatorsTasks.get(elevatorName));

      Thread elevatorThread = new Thread(elevator, elevatorName);
      elevators.add(elevator);
      elevatorThreads.add(elevatorThread);


    }
    elevatorsMonitor = new ElevatorsMonitor(elevators);

  }

  public void activateElevators(){
    for (int i=0; i< elevatorThreads.size(); i++) {
      elevatorThreads.get(i).start();
    }
  }

  public void shutDownElevators(){
    for (int i=0; i< elevatorThreads.size(); i++) {
      elevatorThreads.get(i).interrupt();
    }
  }

  private Boolean validateArrivingPacket() {

    // needs to be implemented

    if ((int) arrivingPacket.getData()[1] != 0) {
      return true;

    }

    return false;

  }

  public Task decodeTask()  {

    // needs to be implemented

    int destination = (int) arrivingPacket.getData()[1];

    String direction;
    if  ((int) arrivingPacket.getData()[3] == 1){
      direction =  "up";
    } else {
      direction =  "down";
    };

    String requestTime = LocalTime.now().toString();

    return new Task(destination, direction, requestTime);
  }

  /**
   * Run method for the ElevatorController
   * 
   * @throws InterruptedException If the thread is sleeping and it gets
   *                              interrupted, this exception is thrown.
   */


  public void elevatorControllerIteration() throws InvalidRequestException {

    System.out.println("Waiting for tasks");

    waitingForDataMode("ready?");

    boolean correctRequest = false;
    while (!correctRequest) {
      RPC_request();

      correctRequest = validateArrivingPacket();
      if ((correctRequest == false)&&(arrivingPacket.getLength()!=100)&&arrivingPacket.getLength()!=8){
        this.closeSocket();
        throw new InvalidRequestException("Request Invalid!");
      }
    }
    System.out.println("Task received...\n");

    Task task = decodeTask();
    System.out.println("Elevator got task fro m Scheduler (requests): " + task.getTaskRawData());

    // searching for an optimal elevator for the task
    Elevator elevator = elevatorsMonitor.getOptimalElevator(task);

    // Add the task to the elevator
     synchronized (elevator.getInnerQueue()) {elevator.addToInnerQueue(task);}
    arrivingPacket = null;
  }


  /**
   * main method to run the client
   * @param args a regular arguments array
   */
  public static void main( String args[] ) throws InvalidRequestException
  {
    System.out.println("Elevator is running");
    ElevatorController ec = new ElevatorController(69, 3);
    ec.activateElevators();

    while(true) {
      try {
        ec.elevatorControllerIteration();
      }
      catch (InvalidRequestException e) {
        ec.closeSocket();
        e.printStackTrace();
        System.exit(1);
      }
    }
  }

}

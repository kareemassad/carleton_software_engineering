import java.util.*;
import java.time.*;

/**
 * 
 * The Floor subsystem, it's controller by the SchedulerController.
 *
 */
public class FloorController extends RPCClient{

	private int currentNumberOfFloors;
	private int currentNumberOfTrips;

	private String[] buttonDirection = {"up", "down"};

	private ArrayDeque<Task> buttonPresses;


	public FloorController(int currentNumberOfFloors, int currentNumberOfTrips, int schedulerPort) {
		super();

		this.currentNumberOfFloors = currentNumberOfFloors;
		this.currentNumberOfTrips = currentNumberOfTrips;
		buttonPresses = new ArrayDeque<Task>();

		this.schedulerPort = schedulerPort;

		System.out.print("Generating Floor Requests...");

		for (int i = 0; i < currentNumberOfTrips; i++)
		{
			generateButtonPress();
			Random randomDuration = new Random();
			int sleepDuration = randomDuration.nextInt(900 - 110) + 110;

			try {
				//sleep for random length of time (milliseconds)
				Thread.sleep(sleepDuration);
			} catch (InterruptedException e) {
				//throw the exception and print can't sleep
				System.out.println("Cant sleep");
			}
			System.out.print(i+".");
		}
		System.out.println();
		System.out.println("Floor Request Data generated!");

	}


	public Task getButtonPress() {
		return buttonPresses.removeLast();
	}


	private void generateButtonPress()
   {
	   // Random thread sleep times -> 110 - 900 ms
	   Random random = new Random();

	   int floor = random.nextInt(currentNumberOfFloors) + 1;

	   String direction;

	   String currentTime = LocalTime.now().toString();

	   if (floor == 1) {
		   direction = buttonDirection[0];
	   }
	   else if (floor == currentNumberOfFloors){
		   direction = buttonDirection[1];
	   }
	   else{
	   		direction = buttonDirection[random.nextInt(2)];
	   }

	   Task task = new Task(floor, direction, currentTime);
	   
	   buttonPresses.addFirst(task);
   }

   public void floorControllerIteration(){

	   System.out.println("Preparing message...");
	   Task elevatorRequest = getButtonPress();

	   encodeTask(elevatorRequest);

	   byte [] message = leavingPacket.getData();

	   String messageAsString = new String(message,0,message.length);
	   System.out.println("String content: "+messageAsString);
	   printData(message, message.length);

	   String response = "none";

	   makeArrivingPacket();

	   while (!response.equals("received")) {
		   RPC_request();
		   response = new String(arrivingPacket.getData(), 0, arrivingPacket.getLength());
	   }

	   System.out.println("Elevator request sent...\n");
	   System.out.println("Floor received new task and submitted it to Scheduler: " + elevatorRequest.getTaskRawData());

   }


	public static void main(String[] args) throws InterruptedException {
		//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("Enter # of floors: ");
		int currentNumberOfFloors = 50; // sc.nextInt();
//		System.out.println("Enter # of tasks: ");
		int currentNumberOfTrips = 50; //sc.nextInt();

		System.out.println("Floor is running");

		int schedulerPort = 23;

		FloorController fc = new FloorController(currentNumberOfFloors, currentNumberOfTrips, schedulerPort);

		for (int i = 0; i < currentNumberOfTrips; i++)
		{

			fc.floorControllerIteration();

			try {
				//sleep for random length of time (milliseconds)
				Thread.sleep(500);
			} catch (InterruptedException e) {
				//throw the exception and print can't sleep
				System.out.println("Cant sleep");
			}

		}


	}

}

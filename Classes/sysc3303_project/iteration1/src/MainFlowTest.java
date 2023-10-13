import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * 
 * Class to test the main data flow of the application
 * 
 */

class MainFlowTest {
	
	/**
	 * 
	 * test method implements simple Task transfer between queues
	 * 
	 */

	@Test
	void test() {
		
		try {
			FileReader elevatorTasks = new FileReader("expected_output.txt");
			BufferedReader taskInput = new BufferedReader(elevatorTasks);
			SchedulerController scheduler = new SchedulerController();

			Task task = new Task(taskInput.readLine());

			scheduler.putTask(task, "requests");

			task = scheduler.getTask("requests");

			assertEquals("14:40:15.000 2 down 1", task.getTaskRawData());

			scheduler.putTask(task, "completed");

			task = scheduler.getTask("completed");

			assertEquals("14:40:15.000 2 down 1", task.getTaskRawData());
			
			taskInput.close();

		} catch (FileNotFoundException e) {
			System.out.println("No input file");
		}
		catch (IOException e) {

		}
		
	}

}

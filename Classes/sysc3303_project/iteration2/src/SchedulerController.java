
import java.util.HashMap;
import java.util.ArrayDeque;

/**
 * 
 * The Scheduler class, It does all the communication between the Floor subsystem and the Elevator subsystem. It sends and gets tasks from both classes.
 *
 */
public class SchedulerController {

    private HashMap<String, ArrayDeque<Task>> taskQueue;
    /**
     * Initialization
     */
    public SchedulerController()
    {
        taskQueue = new HashMap<String, ArrayDeque<Task>>();
        taskQueue.put("requests", new ArrayDeque<Task>());
        taskQueue.put("completed", new ArrayDeque<Task>());
    }

    /**
     * 
     * @param task the task we have, whether it's from the Elevator subsystem or Floor subsystem.
     * @param queue all the tasks present.
     */
    public synchronized  void putTask(Task task, String queue)
    {
    	//add the task as the first in the queue.
        System.out.println("-> Putting a task into: " + queue +" " + task.getTaskRawData());
        taskQueue.get(queue).addFirst(task);
        notifyAll();
    }

    
    /**
     * 
     * @param queue the task we want to retrieve
     * @return the task with the given name
     */
    public synchronized Task getTask(String queue)
    {
    	//if there are no tasks, wait.
        while(taskQueue.get(queue).size()==0)
        {
            try {
                wait();
            } catch (InterruptedException e)
            {
                return null;
            }
        }
        //if there are tasks, find the task with the given name and return it.
        Task currentRequest = taskQueue.get(queue).removeLast();
        notifyAll();
        return currentRequest;
    }
}

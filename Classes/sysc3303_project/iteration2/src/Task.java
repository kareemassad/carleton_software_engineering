
/**
 * 
 * The task class is what's used to modify details about the tasks passed by the Scheduler, like stats and data.
 *
 */
public class Task {

    private String taskRawData;
    private String status;
    private int destination;
    private String requestTime;

/**
 * sets the data of the task.
 * @param line the data of the task.
 */
    public Task(int destination, String requestTime) {
        this.destination = destination;
        this.requestTime = requestTime;
        this.taskRawData = requestTime + " going to: " +  destination;
    }

    /**
     * 
     * @return the data of the task.
     */
    public String getTaskRawData() {

        return this.taskRawData;
    }

    /**
     * sets the status of the task
     * @param status the status of the task.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * gets the status of the task
     * @return status of the task
     */
    public String getStatus() { return this.status; }

    public int getDestination() {
        return destination;
    }
}


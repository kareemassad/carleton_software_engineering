
/**
 * 
 * The task class is what's used to modify details about the tasks passed by the Scheduler, like stats and data.
 *
 */
public class Task {

    private String taskRawData;
    private String status;

/**
 * sets the data of the task.
 * @param line the data of the task.
 */
    public Task(String line) {
        this.taskRawData = line;
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
}

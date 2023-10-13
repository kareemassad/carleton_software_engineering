
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
    private String direction;
    private int priority;


    public Task(int destination, String direction , String requestTime) {
        this.destination = destination;
        this.direction = direction;
        this.requestTime = requestTime;
        this.taskRawData = "Button " + "'"+direction+"' "+ "pressed at:" + requestTime + ". Calling Floor: " + destination;
        priority = 0;
    }

    public Task(int destination, String requestTime) {
        this.destination = destination;
        this.direction = null;
        this.requestTime = requestTime;

        this.taskRawData = "Button pressed at:" + requestTime + ". Calling Floor: " + destination + " Priority:" + priority;
    }

    /**
     * 
     * @return the data of the task.
     */
    public String getTaskRawData() {

        return "Button pressed at:" + requestTime + ". Calling Floor: " + destination + "Direction"+direction+" Priority:" + priority;
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

    public String getDirection() {
        return direction;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
}


import java.util.ArrayDeque;
import java.util.HashMap;

public class CompletionBuffer {

    private ArrayDeque<Task> completedTasks;

    public CompletionBuffer(){
        completedTasks = new ArrayDeque<Task>();
    }


    public void putTask(Task task)
    {
        synchronized(completedTasks) {
            completedTasks.addFirst(task);
        }
        notifyAll();
    }

    public Task getTask(String queue)
    {
        //if there are no tasks, wait.
        Task task;
        synchronized (completedTasks) {
            while(completedTasks.size()==0)
            {
                try {
                    wait();
                } catch (InterruptedException e)
                {
                    return null;
                }
            }

            task = completedTasks.removeLast();
            notifyAll();
        }
        return task;
    }

}

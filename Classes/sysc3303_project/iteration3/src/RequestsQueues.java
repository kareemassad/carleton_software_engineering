import java.util.ArrayDeque;
import java.util.HashMap;

public class RequestsQueues {

    private HashMap<String, ArrayDeque<byte[]>> messages;

    /**
     * Initialization
     */
    public RequestsQueues()
    {
        messages = new HashMap<String, ArrayDeque<byte[]>>();
        messages.put("requests", new ArrayDeque<byte[]>());
        messages.put("completed", new ArrayDeque<byte[]>());
    }

    public synchronized  void putTask(byte[] data, String queue)
    {
//        while(messages.get(queue).size()!=0)
//        {
//            try {
//                wait();
//            } catch (InterruptedException e)
//            {
//                return;
//            }
//        }

        messages.get(queue).addFirst(data);
        System.out.println("PUT " + queue + " has "+messages.get(queue).size()+" elements");

        notifyAll();
    }

    public synchronized byte[] getTask(String queue)
    {
        //if there are no tasks, wait.
        while(messages.get(queue).size()==0)
        {
            try {
                wait();
            } catch (InterruptedException e)
            {
                return null;
            }
        }

        byte[] message = messages.get(queue).removeLast();
        notifyAll();
        System.out.println("GET " + queue + " has "+messages.get(queue).size()+" elements");
        return message;
    }

    public boolean isEmpty(String queue) {
        return messages.get(queue).isEmpty();
    }

}

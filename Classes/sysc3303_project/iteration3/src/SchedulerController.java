
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayDeque;

/**
 * 
 * The Scheduler class, It does all the communication between the Floor subsystem and the Elevator subsystem. It sends and gets tasks from both classes.
 *
 */
public class SchedulerController implements Runnable{

    DatagramPacket leavingPacket, arrivingPacket;
    DatagramSocket receiveSocket;

    InetAddress addressFrom;
    int portFrom;

    RequestsQueues requestsQueues;

    String destinationQueueName, relayQueueName;


//    private HashMap<String, ArrayDeque<Task>> taskQueue;
    /**
     * Initialization
     */
    public SchedulerController(RequestsQueues requestsQueues, String destinationQueueName, String relayQueueName, int port)
    {
        this.requestsQueues = requestsQueues;
        this.destinationQueueName = destinationQueueName;
        this.relayQueueName = relayQueueName;

        try {
            receiveSocket = new DatagramSocket(port);

        } catch (SocketException se) {
            se.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * A helper method to print request contents
     */
    public void printRequest() {
        byte[] data = arrivingPacket.getData();
        int len = arrivingPacket.getLength();

        String received = new String(data,0,len);

        received =  received;
        System.out.println("String content: " + received);
        printData(data, len);

    }

    /**
     * A method used as a helper to print byte array
     * @param data array to print
     * @param len array length
     */
    public void printData(byte[] data, int len){
        String str = "";
        for (int i = 0; i < len; i++) {
            str += data[i] + " ";
        }
        System.out.println("Byte data: "+str);
    }


    public void receiveRequest(boolean saveSource){

        byte data[] = new byte[100];

        arrivingPacket = new DatagramPacket(data, data.length);

        try {
            receiveSocket.receive(arrivingPacket);

        } catch (IOException e) {
            System.out.print("IO Exception: likely:");
            System.out.println("Receive Socket Timed Out.\n" + e);
            e.printStackTrace();
            System.exit(1);
        }

        if (saveSource) {
            addressFrom = arrivingPacket.getAddress();
            portFrom = arrivingPacket.getPort();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e ) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void sendResponse(byte [] data) {

        DatagramSocket sendSocket;
        try {
            sendSocket = new DatagramSocket();
            System.out.println("From "+addressFrom+":"+portFrom);
            leavingPacket = new DatagramPacket( data, data.length, addressFrom, portFrom);
            try {
                sendSocket.send(leavingPacket);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } catch (SocketException se) {
            se.printStackTrace();
            System.exit(1);
        }

    }

    public void run(){

        System.out.println("Waiting for "+receiveSocket.getLocalPort()+"...");


        while (true) {
            receiveRequest(true);

            System.out.print("IH@"+receiveSocket.getLocalPort()+":");
            //printRequest();

            byte[] requestData = arrivingPacket.getData();
            String message = new String(requestData, 0, arrivingPacket.getLength());

            if (message.equals("ready?")) {
                System.out.println("IH@:"+receiveSocket.getLocalPort()+" someone is polling");

                if (!requestsQueues.isEmpty(relayQueueName)) {

                    byte[] encodedTask = requestsQueues.getTask(relayQueueName);
                    System.out.println("IH@"+receiveSocket.getLocalPort()+"m length:"+encodedTask.length);
                    sendResponse(encodedTask);
                    System.out.println("IH@"+receiveSocket.getLocalPort()+"relayed");
                }
                else continue;
            } else {
                System.out.println("IH@"+receiveSocket.getLocalPort()+" some data arrived");
                byte messageToPropagate[] = Arrays.copyOfRange(arrivingPacket.getData(), 0, arrivingPacket.getLength());
                System.out.println("IH@"+receiveSocket.getLocalPort()+"the length:"+messageToPropagate.length);
                requestsQueues.putTask(messageToPropagate, destinationQueueName);
                System.out.println("Sending confirmation");

                sendResponse("received".getBytes());

            }

        }

    }


    /**
     * main method to run the client
     * @param args a regular arguments array
     */
    public static void main( String args[] )
    {
        Thread clientHandler, serverHandler;

        RequestsQueues buffer = new RequestsQueues();

        SchedulerController intermediateHostServer = new SchedulerController(buffer, "requests", "completed", 23);
        SchedulerController intermediateHostClient = new SchedulerController(buffer, "completed", "requests",69);

        clientHandler = new Thread(intermediateHostClient, "FloorRelay");
        serverHandler = new Thread(intermediateHostServer, "ElevatorRelay");

        clientHandler.start();
        serverHandler.start();

    }
}

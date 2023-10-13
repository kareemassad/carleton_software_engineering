import java.io.IOException;
import java.net.*;

/**
 * Class that represents the client in the system
 * 
 * @Author Igor Bogdanov, 101169300
 * @Author Carleton University
 */

public abstract class RPCClient {

    DatagramPacket leavingPacket, arrivingPacket;
    DatagramSocket sendReceiveSocket;

    int schedulerPort;

    public RPCClient() {
        try {
            sendReceiveSocket = new DatagramSocket();
        } catch (SocketException se) {
            se.printStackTrace();
            System.exit(1);
        }

        try {
            sendReceiveSocket.setSoTimeout(500);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void encodeTask(Task task) {

        byte[] destination = { (byte) task.getDestination() };

        byte[] direction;
        if (task.getDirection().equals("up")){
            direction = new byte[]{1};
        } else {
            direction = new byte[]{2};
        }

        byte[] requestTime = task.getRequestTime().getBytes();
        byte zero[] = { 0 };

        byte[] result = new byte[zero.length + destination.length + zero.length + direction.length + zero.length
                + requestTime.length + zero.length];

        // zero+destination+zero+direction+zero+requestTime+zero
        System.arraycopy(zero, 0, result, 0, zero.length);
        System.arraycopy(destination, 0, result, zero.length, destination.length);
        System.arraycopy(zero, 0, result, zero.length + destination.length, zero.length);
        System.arraycopy(direction, 0, result, zero.length + destination.length + zero.length, direction.length);
        System.arraycopy(zero, 0, result, zero.length + destination.length + zero.length + direction.length,
                zero.length);
        System.arraycopy(requestTime, 0, result,
                zero.length + destination.length + zero.length + direction.length + zero.length, requestTime.length);
        System.arraycopy(zero, 0, result,
                zero.length + destination.length + zero.length + direction.length + zero.length + requestTime.length,
                zero.length);

        try {
            leavingPacket = new DatagramPacket(result, result.length,
                    InetAddress.getLocalHost(), schedulerPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void makeArrivingPacket() {
        byte[] data = new byte[100];
        arrivingPacket = new DatagramPacket(data, data.length);
    }

    private void makeStatusCheckPacket(String status) {

        byte result[] = status.getBytes();

        try {
            leavingPacket = new DatagramPacket(result, result.length,
                    InetAddress.getLocalHost(), schedulerPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void waitingForDataMode(String status){
        makeArrivingPacket();
        makeStatusCheckPacket(status);
    }

    public void RPC_request() {

        try {
            sendReceiveSocket.send(leavingPacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // System.out.println("Message sent...\n");

        try {
            sendReceiveSocket.receive(arrivingPacket);

        } catch (SocketTimeoutException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        // System.out.println("Message received...\n");
    }

    /**
     * A method used as a helper to print byte array
     * 
     * @param data array to print
     * @param len  array length
     */
    public void printData(byte[] data, int len) {
        String str = "";
        for (int i = 0; i < len; i++) {
            str += data[i];
        }
        System.out.println("Byte data: " + str);
    }

    /**
     * a helper method to print out a received a message saved in class attribute
     * received packet
     */
    public void printReceived() {
        byte[] data = arrivingPacket.getData();
        int len = arrivingPacket.getLength();

        String received = new String(data, 0, len);

        received = received;
        System.out.println("String content: " + received);
        printData(data, len);

    }

    /**
     * a helper method to close opened socket
     */
    public void closeSocket() {
        sendReceiveSocket.close();
    }

}


import java.io.*;
import java.net.*;

public class Client {

   DatagramPacket sendPacket;
   DatagramSocket sendReceiveSocket;

   public Client() {
      try {
         // Construct a datagram socket and bind it to any available
         // port on the local host machine. This socket will be used to
         // send and receive UDP Datagram packets.
         sendReceiveSocket = new DatagramSocket();
      } catch (SocketException se) { // Can't create the socket.
         se.printStackTrace();
         System.exit(1);
      }
   }

   private static byte[] messageBuilder(boolean w, String fileName, String mode) throws IOException {
      // need 5 reads, 5 writes, 1 invalid request
      byte[] readByteStarter = { 0, 1 };
      byte[] writeByteStarter = { 0, 2 };
      byte[] bytesFileName = fileName.getBytes();
      byte[] zeroByte = { 0 };
      byte[] bytesMode = mode.getBytes();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      if (w) {
         // create with write format
         System.out.println("write " + fileName + " " + mode);
         outputStream.write(writeByteStarter);
         outputStream.write(bytesFileName);
         outputStream.write(zeroByte);
         outputStream.write(bytesMode);
         outputStream.write(zeroByte);

         return outputStream.toByteArray();
      } else {
         if (fileName == "not valid") {
            // throw invalid request exception and quit
            System.out.println("invalid request");
            System.exit(1);
         }

         System.out.println("read " + fileName + " " + mode);

         outputStream.write(readByteStarter);
         outputStream.write(bytesFileName);
         outputStream.write(zeroByte);
         outputStream.write(bytesMode);
         outputStream.write(zeroByte);

         System.out.println();

         return outputStream.toByteArray();

      }

   }

   public static void sendPacket(DatagramSocket socket, byte[] message, InetAddress ipAddress, int port)
         throws IOException {
      int messageLength = message.length;
      System.out.println("Client: Sending packet from: " + socket.getLocalAddress() + " to: " + ipAddress);
      System.out.println("Client: Packet length: " + messageLength);
      System.out.println("Client: Packet contents: ");

      // print contents of packet by iterating through the byte array
      for (int i = 0; i < messageLength; i++) {
         System.out.print(message[i] + " ");
      }
      System.out.println();

      System.out.println("---------------------------");

      DatagramPacket sendPacket = new DatagramPacket(message, messageLength, ipAddress, port);
      System.out.println("Client: Sending Packet: " + sendPacket);
      socket.send(sendPacket);

   }

   public static DatagramPacket receivePacket(DatagramSocket socket) throws IOException {
      System.out.println("Client: Waiting for packet...");
      byte[] receiveMessage = new byte[100];
      DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
      socket.receive(receivePacket);

      // print out the contents of the packet
      System.out.println("Client: Packet received: " + receivePacket);

      System.out.println("Client: Packet contents: ");

      // print contents of packet by iterating through the byte array
      for (int i = 0; i < receivePacket.getLength(); i++) {
         System.out.print(receivePacket.getData()[i] + " ");
      }
      System.out.println();
      System.out.println("===========================");

      return receivePacket;
   }

   public void sendAndReceiveSocket(byte[] message, int port) throws IOException {
      try {
         // send packet
         System.out.println("Client: Sending packet...");
         System.out.println("Client: Packet Contents: " + message);
         sendPacket(sendReceiveSocket, message, InetAddress.getLocalHost(), port);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
      // receive packet
      receivePacket(sendReceiveSocket);
   }

   public static void main(String args[]) {
      Client client = new Client();
      boolean w = false;
      String mode = "netascii";

      for (int i = 0; i < 10; i++) {
         String fileName = "test" + i + ".txt";
         // print fileName
         System.out.println(fileName);
         try {
            byte[] message = messageBuilder(w, fileName, mode);
            // print message
            System.out.println(message);
            client.sendAndReceiveSocket(message, 69);
            // alterate between read and write
            w = !w;
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      // handle invalid packet here
      byte[] errorMessage;
      try {
         errorMessage = messageBuilder(false, "not valid", mode);
         client.sendAndReceiveSocket(errorMessage, 69);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }

   }
}

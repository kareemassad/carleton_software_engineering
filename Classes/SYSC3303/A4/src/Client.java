
import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.Instant;

public class Client {

   DatagramPacket sendPacket;
   DatagramSocket sendReceiveSocket;

   /**
    * Constructor for the Client class.
    */
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

   /**
    * This method builds the message to be sent. It uses the w boolean to alternate
    * between read and write requests.
    * 
    * @param w        A read/write boolean that determines the type of request.
    * @param fileName The name of the file containing the message to send.
    * @param mode     the type of request being run (Typically netascii or octet)
    * @return The message to be sent as a byte array.
    */
   private static byte[] messageBuilder(boolean w, byte[] fileName, String mode) throws IOException {
      // need 5 reads, 5 writes, 1 invalid request
      byte[] readByteStarter = { 0, 1 };
      byte[] writeByteStarter = { 0, 2 };
      byte[] bytesFileName = fileName;
      byte[] zeroByte = { 0 };
      byte[] bytesMode = mode.getBytes();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      if (w) {
         // create with write format
         // System.out.println("write " + fileName + " " + mode);
         outputStream.write(writeByteStarter);
         outputStream.write(bytesFileName);
         outputStream.write(zeroByte);
         outputStream.write(bytesMode);
         outputStream.write(zeroByte);

         return outputStream.toByteArray();
      } else {
         // if (fileName == "not valid") {
         // // throw invalid request exception and quit
         // System.out.println("invalid request");
         // System.exit(1);
         // }

         // System.out.println("read " + fileName + " " + mode);

         outputStream.write(readByteStarter);
         outputStream.write(bytesFileName);
         outputStream.write(zeroByte);
         outputStream.write(bytesMode);
         outputStream.write(zeroByte);

         // System.out.println();

         return outputStream.toByteArray();

      }

   }

   /**
    * This method sends the message to the specified address.
    * 
    * @param socket    The socket to send the message to.
    * @param message   The message to be sent.
    * @param ipAddress The IP address to send the packet to.
    * @param port      The port number of the server.
    * @throws IOException If an I/O error occurs.
    */
   public static void sendPacket(DatagramSocket socket, byte[] message, InetAddress ipAddress, int port)
         throws IOException {
      int messageLength = message.length;
      // System.out.println("Client: Sending packet from: " + socket.getLocalAddress()
      // + " to: " + ipAddress);
      // System.out.println("Client: Packet length: " + messageLength);
      // System.out.println("Client: Packet contents: ");

      // print contents of packet by iterating through the byte array
      // for (int i = 0; i < messageLength; i++) {
      // System.out.print(message[i] + " ");
      // }
      // System.out.println();

      // System.out.println("---------------------------");

      DatagramPacket sendPacket = new DatagramPacket(message, messageLength, ipAddress, port);
      // System.out.println("Client: Sending Packet: " + sendPacket);
      socket.send(sendPacket);

   }

   /**
    * This method receives the message from the specified address.
    * 
    * @param socket The socket to receive the message from.
    * @return A Datagram of the message received.
    * @throws IOException If an I/O error occurs.
    */
   public static DatagramPacket receivePacket(DatagramSocket socket) throws IOException {
      // System.out.println("Client: Waiting for packet...");
      byte[] receiveMessage = new byte[100];
      DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
      socket.receive(receivePacket);

      // print out the contents of the packet
      // System.out.println("Client: Packet received: " + receivePacket);

      // System.out.println("Client: Packet contents: ");

      // print contents of packet by iterating through the byte array
      // for (int i = 0; i < receivePacket.getLength(); i++) {
      // System.out.print(receivePacket.getData()[i] + " ");
      // }
      // System.out.println();
      // System.out.println("===========================");

      return receivePacket;
   }

   /**
    * This method sends a message to the server and waits to receives a response.
    * 
    * @param message The message to be sent.
    * @param port    The port number to send the packet.
    * @throws IOException If an I/O error occurs.
    */
   public void sendAndReceiveSocket(byte[] message, int port) throws IOException {
      try {
         // send packet
         // System.out.println("Client: Sending packet...");
         // System.out.println("Client: Packet Contents: " + message);
         sendPacket(sendReceiveSocket, message, InetAddress.getLocalHost(), port);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
      // receive packet
      receivePacket(sendReceiveSocket);
   }

   public static void main(String args[]) {
      // check total time elapsed using Instant and tmxb.getThreadCpuTime()
      Instant start = Instant.now();
      long startTime = System.nanoTime();

      Client client = new Client();
      boolean w = false;
      String mode = "netascii";
      // create filename of type bytes size 1000
      byte[] fileName = new byte[1000];

      for (int i = 0; i < 1000; i++) {
         // print i
         // System.out.println("i = " + i);
         // print fileName
         // System.out.println(fileName);
         try {
            byte[] message = messageBuilder(w, fileName, mode);
            // print message
            // System.out.println(message);
            client.sendAndReceiveSocket(message, 69);
            // alterate between read and write
            w = !w;
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      // handle invalid packet here
      // byte[] errorMessage;
      // try {
      // errorMessage = messageBuilder(false, "not valid", mode);
      // client.sendAndReceiveSocket(errorMessage, 69);
      // } catch (IOException e) {
      // e.printStackTrace();
      // System.exit(1);
      // }

      Instant finish = Instant.now();
      long endTime = System.nanoTime();
      // check time elapsed using Instant (M2) and tmxb.getThreadCpuTime() (M1)
      long timeElapsedM1 = endTime - startTime;
      long timeElapsedM2 = Duration.between(start, finish).toMillis();

      System.out.println("Time elapsed using tmxb.getThreadCpuTime(): " + timeElapsedM1 + " nanoseconds");

      long timeElapsedM1ms = timeElapsedM1 / 1000000;

      System.out.println("Time elapsed using tmx.getThreadCpuTime(): " + timeElapsedM1ms + " milliseconds");
      System.out.println("Time elapsed using Instant: " + timeElapsedM2 + " milliseconds");

   }
}

// SimpleEchoServer.java
// This class is the server side of a simple echo server based on
// UDP/IP. The server receives from a client a packet containing a character
// string, then echoes the string back to the client.
// Last edited January 9th, 2016

import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.Instant;

public class Server {

   DatagramPacket sendPacket;
   static DatagramPacket receivePacket;
   DatagramSocket sendSocket;
   static DatagramSocket receiveSocket;
   public static final int port = 69;

   /**
    * Constructor for the Server class.
    */
   public Server() {
      try {
         // Construct a datagram socket and bind it to any available
         // port on the local host machine. This socket will be used to
         // send UDP Datagram packets.
         sendSocket = new DatagramSocket();

         // Construct a datagram socket and bind it to port 5000
         // on the local host machine. This socket will be used to
         // receive UDP Datagram packets.
         receiveSocket = new DatagramSocket(port);

         // to test socket timeout (2 seconds)
         // receiveSocket.setSoTimeout(2000);
      } catch (SocketException se) {
         se.printStackTrace();
         System.exit(1);
      }
   }

   /**
    * This method validates the data inside the byte array provided.
    * 
    * @param data - the byte array to be validated
    * @return true if the data is valid, false otherwise
    */
   private static Boolean validateData(byte[] data) {
      // check if data is as expected, return true if it is
      // otherwise, return false
      // data format: {0,1,fileName,0,mode,0}
      // or {0,2,fileName,0,mode,0}

      // base cases
      // 6 total elements
      if (data.length < 6) {
         return false;
      }
      // first element is 0
      if (data[0] != 0) {
         return false;
      }
      // second element is 1 or 2
      if (data[1] != 1 && data[1] != 2) {
         return false;
      }
      // last element is 0
      if (data[data.length - 1] != 0) {
         return false;
      }
      // check if 3rd element and 5th element are characters

      // if packet is a read packet (0,1) then send back 4 bytes {0 3 0 1}
      // if packet is a write packet (0,2) then send back 4 bytes {0 4 0 0}

      return true;
   }

   /**
    * This method receives a packet and prints it's contents.
    * 
    * @return byte array containing the data received
    */
   public static byte[] receivePacket() {
      // Construct a DatagramPacket for receiving packets up
      // to 100 bytes long (the length of the byte array).
      try {
         receivePacket = Client.receivePacket(receiveSocket);
      } catch (IOException e1) {
         e1.printStackTrace();
         System.exit(1);
      }
      // System.out.println("Server: Waiting for Packet.\n");

      // print the packet received as a String and as a byte array
      // System.out.println("Server: Packet received: " + receivePacket);
      // System.out.print("Server: Packet Data: ");
      // for (int i = 0; i < receivePacket.getLength(); i++) {
      // System.out.print(receivePacket.getData()[i] + " ");
      // }
      // System.out.println();

      // byte data of received packet
      return receivePacket.getData();
   }

   /**
    * This method sends a packet to the specified ip address.
    * 
    * @param data      - the byte array to be sent
    * @param length    - the length of the byte array
    * @param ipAddress - the ip address to send the packet to
    * @param port      - the port to send the packet to
    * @return true if the packet was sent, false otherwise
    */
   public Boolean sendPacket(byte[] data, int length, InetAddress ipAddress, int port) {
      // Construct a DatagramPacket to send
      // the received packet back to the client.
      try {
         sendPacket = new DatagramPacket(data, data.length, ipAddress, port);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }

      // send the packet
      try {
         sendSocket.send(sendPacket);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }

      // print the contents of the packet as a String and as a byte array
      // System.out.println("Server: Packet sent: " + sendPacket);
      // System.out.println("Server: Packet Address: " + sendPacket.getAddress());
      // System.out.println("Server: Packet Port: " + sendPacket.getPort());
      // System.out.println("Server: Packet Length: " + sendPacket.getLength());

      // // print the contents of sendPacket.getData() by iterating through the array
      // System.out.print("Server: Packet Data: ");
      // for (int i = 0; i < sendPacket.getLength(); i++) {
      // System.out.print(sendPacket.getData()[i] + " ");
      // }
      // System.out.println();

      return true;
   }

   public static void main(String args[]) throws Exception {
      // check total time elapsed using Instant and tmxb.getThreadCpuTime()
      // Instant start = Instant.now();
      // long startTime = System.nanoTime();

      Server server = new Server();
      while (true) {
         byte[] data = Server.receivePacket();
         // validate the packet
         if (!validateData(data)) {
            throw new Exception("Invalid packet received.");
         }
         // if packet is a read packet (0,1) then send back 4 bytes {0 3 0 1}
         // if packet is a write packet (0,2) then send back 4 bytes {0 4 0 0}
         if (data[1] == 1) {
            // read type packet
            data = new byte[] { 0, 3, 0, 1 };
         } else if (data[1] == 2) {
            // write type packet
            data = new byte[] { 0, 4, 0, 0 };
         } else {
            throw new Exception("Invalid packet received.");
         }
         // System.out.println("Server: Sending packet " + data);
         // send the packet back
         server.sendPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());

         // System.out.println("Server: Packet sent.");
         // System.out.println("==================================================");

         // wait for next packet
         // Server.receivePacket();

      }

      // Instant finish = Instant.now();
      // long endTime = System.nanoTime();
      // // check time elapsed using Instant (M2) and tmxb.getThreadCpuTime() (M1)
      // long timeElapsedM1 = endTime - startTime;
      // long timeElapsedM2 = Duration.between(start, finish).toMillis();

   }
}

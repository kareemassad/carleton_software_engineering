// SimpleEchoServer.java
// This class is the server side of a simple echo server based on
// UDP/IP. The server receives from a client a packet containing a character
// string, then echoes the string back to the client.
// Last edited January 9th, 2016

import java.io.*;
import java.net.*;

public class Host {

   DatagramPacket sendPacket, receivePacket;
   DatagramSocket sendSocket, receiveSocket;
   static final String HOST = "localhost";
   public final static int port = 23;

   public Host() {
      try {
         receiveSocket = new DatagramSocket(port);
         sendSocket = new DatagramSocket();

      } catch (SocketException se) {
         se.printStackTrace();
         System.exit(1);
      }
   }

   private void reply() throws IOException {
      receivePacket = Client.receivePacket(receiveSocket);
      // print the packet receieved as a String and as a byte array
      System.out.println("Host: Packet received: " + receivePacket);

      // print contents of receivePacket by iterating through the byte array
      System.out.println("Host: Packet contents: ");
      for (int i = 0; i < receivePacket.getData().length; i++) {
         System.out.print(receivePacket.getData()[i] + " ");
      }
      System.out.println();
      sleep(500);

      // form the packet with what was received and send it to port 69
      sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(), receivePacket.getAddress(),
            69);

      // print the contents of the packet as a String and as a byte array
      System.out.println("Host: Packet sent: " + sendPacket);

      // print contents of sendPacket by iterating through the byte array
      System.out.println("Host: Packet contents: ");
      for (int i = 0; i < sendPacket.getData().length; i++) {
         System.out.print(sendPacket.getData()[i] + " ");
      }
      System.out.println();

      // send the packet
      sendSocket.send(sendPacket);

   }

   private void sleep(int milliseconds) {
      try {
         Thread.sleep(milliseconds);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public static void main(String args[]) {
      // assuming this is what is meant by "forever"
      Host host = new Host();
      while (true) {
         try {
            host.reply();
         } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }
}

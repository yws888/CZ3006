package cz3006lab2;

import java.net.*;
import java.io.*;

public class Rfc865UdpClient {
	
	public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            //no need to mention IP address/port as UDP is connectionless, only packet has to know about it
        } catch (SocketException e) {
            System.out.println(e);
            socket = null;
        }

        //get IP address
        InetAddress serverIPAddress;
        try {
            //10.27.228.244 is IP address of my personal computer acting as a server
            //serverIPAddress = InetAddress.getByName("10.27.228.244");
            serverIPAddress = InetAddress.getLocalHost();	
            //serverIPAddress = InetAddress.getByName("SWLab2");
        }
        catch (UnknownHostException e){
            System.out.println("Cannot get IP address");
            serverIPAddress = null;
        }


        try {
            //
            // 2. Send UDP request to server
            //
            String message = "Yong Wen Shiuan, TS7, 172.21.150.205";
            byte[] messageInBytes = message.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(messageInBytes, messageInBytes.length, serverIPAddress, 17);
            socket.send(requestPacket);
            System.out.println("Message sent: " + message);

            //
            // 3. Receive UDP reply from server
            //
            byte[] buffer = new byte[1024];

            DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
            // no need to mention port number when receiving
            socket.receive(replyPacket);
            
            String received = new String(replyPacket.getData(), 0, replyPacket.getLength());
			System.out.println("Message received: " + received);
			System.out.println("Your PC IP address: 172.21.150.205");
			InetAddress serverAddress = replyPacket.getAddress();

			System.out.println("Quote of Day server IP address: " + serverAddress.getHostAddress() );


        } catch (IOException e) {
            System.out.println(e);
        }
        
		socket.close();
        
	}

}

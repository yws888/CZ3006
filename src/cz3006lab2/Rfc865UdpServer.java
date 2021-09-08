package cz3006lab2;

import java.net.*;
import java.io.*;
// import java.nio.charset.StandardCharsets;


public class Rfc865UdpServer {
	public static void main(String[] argv) {
		
		//
		// 1. Open UDP socket at well-known port
		//
		System.out.println("Server running");
		DatagramSocket socket;
		
		try {
			socket = new DatagramSocket(17); //hv to mention port number
		} catch (SocketException e) {
            System.out.println(e);
            socket = null;
		}
		
		while (true) {
			try {
				//
				// 2. Listen for UDP request from client
				//
				byte[] buffer = new byte[1024]; 	// buffer for holding incoming datagram
				DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
				socket.receive(requestPacket);
				
	            String received = new String(requestPacket.getData(), 0, requestPacket.getLength());
				System.out.println("Server received: " + received);

				InetAddress address = requestPacket.getAddress();
				int port = requestPacket.getPort();
				
				//
				// 3. Send UDP reply to client
				//
				// Create data packet with verification code
				
				String responseMessage = "Received successfully from " + received;
	            byte[] messageInBytes = responseMessage.getBytes();
				DatagramPacket responsePacket = new DatagramPacket(messageInBytes, messageInBytes.length, address, port);
			    socket.send(responsePacket);	// send to client
			} 
			catch (IOException e) {
				e.printStackTrace();
				return;
			}
						
		}
	}
}

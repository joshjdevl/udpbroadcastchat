/**
 *
 */
package com.ips.udpbroadcastchat;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



/**
 * @author rmsa
 *
 */
public class UdpBroadcastSender {
	private DatagramSocket datagramSocket;
	private final int receiverPort;


	public UdpBroadcastSender(int port) throws SocketException, UnknownHostException,
			BindException {
		datagramSocket = new DatagramSocket(port);
		receiverPort = port;
	}

	/**
	 * Sends data using the UDP protocol to a specific receiver
	 *
	 * @param destinationNodeID
	 *            indicates the ID of the receiving node. Should be a positive
	 *            integer.
	 * @param data
	 *            is the message which is to be sent.
	 * @throws IOException
	 * @throws SizeLimitExceededException
	 *             is thrown if the length of the data to be sent exceeds the
	 *             limit
	 */
	public boolean sendPacket(String ipaddress, byte[] data)
			throws IOException {

		InetAddress IPAddress = InetAddress.getByName(ipaddress);

		DatagramPacket sendPacket;

		datagramSocket.setBroadcast(true);
		sendPacket = new DatagramPacket(data, data.length, IPAddress,
				receiverPort);

		datagramSocket.send(sendPacket);
		return true;

	}

	public void closeSocket() {
		datagramSocket.close();
	}
}

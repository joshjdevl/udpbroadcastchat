/**
 *
 */
package com.ips.udpbroadcastchat;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.content.Context;
import android.util.Log;

/**
 * @author rmsa
 *
 */
public class UdpBroadcastReceiver implements Runnable {
	private DatagramSocket brodcastDatagramSocket;
	private volatile boolean keepBroadcasting = true;
	private Thread udpBroadcastReceiverThread;

	public UdpBroadcastReceiver(int receiverPort) throws SocketException,
			BindException {
		brodcastDatagramSocket = new DatagramSocket(receiverPort);
	}

	public void startBroadcastReceiverthread() {
		keepBroadcasting = true;
		udpBroadcastReceiverThread = new Thread(this);
		udpBroadcastReceiverThread.start();
	}

	private void stopBroadcastThread() {
		keepBroadcasting = false;
		udpBroadcastReceiverThread.interrupt();
	}

	public void run() {
		while (keepBroadcasting) {
			try {
				// 52kb buffer
				byte[] buffer = new byte[52000];
				DatagramPacket brodcastReceivePacket = new DatagramPacket(
						buffer, buffer.length);

				brodcastDatagramSocket.receive(brodcastReceivePacket);

				byte[] result = new byte[brodcastReceivePacket.getLength()];
				System.arraycopy(brodcastReceivePacket.getData(), 0, result, 0,
						brodcastReceivePacket.getLength());

				String[] ip = brodcastReceivePacket.getAddress().toString()
						.split("\\.");


				int address = Integer.parseInt(ip[ip.length - 1]);

				String text = new String(result);
				Log.i("TEXTMESSAGE", address+" "+text);



			} catch (IOException e) {
				// FIXME handle error
			}
		}
	}

}

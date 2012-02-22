package com.ips.udpbroadcastchat;

import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UDPBroadcastChatActivity extends Activity {
	private EditText ipaddressId;
	private EditText portId;
	private EditText messageId;
	private UdpBroadcastSender sender;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ipaddressId = (EditText) findViewById(R.id.SENDIPADDRESSID);
		portId = (EditText) findViewById(R.id.SENDPORTID);
		messageId = (EditText) findViewById(R.id.SENDMESSAGETEXTID);

	}

	public void startReceiver(View view) {
		try {

			String port = portId.getText().toString();

			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "port is-"+port, 5);
			toast.show();

			int portnumber = Integer.valueOf(port);


			UdpBroadcastReceiver receiver = new UdpBroadcastReceiver(portnumber);
			receiver.startBroadcastReceiverthread();
		} catch (BindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startSender(View view) {
		try {

			String port = portId.getText().toString();

			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "port is-"+port, 5);
			toast.show();

			int portnumber = Integer.valueOf(port);

			sender = new UdpBroadcastSender(portnumber);

		} catch (BindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(View view) throws IOException {
		Context context = getApplicationContext();

		String text = messageId.getText().toString();
		String ipaddress = ipaddressId.getText().toString();

		sender.sendPacket(ipaddress, text.getBytes());

		Toast toast = Toast.makeText(context, text, 5);
		toast.show();

	}
}
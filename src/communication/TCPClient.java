package communication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import modules.Module;

public class TCPClient {

	public TCPClient(String text, Module module) {
		Socket s1=null;
		InetAddress address;
		try {
			address=InetAddress.getLocalHost();
			s1=new Socket(address, 8889);
			DataOutputStream outToClient = new DataOutputStream(s1.getOutputStream());
			outToClient.writeBytes(text+"\n");	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

}
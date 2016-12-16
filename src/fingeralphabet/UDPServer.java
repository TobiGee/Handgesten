package fingeralphabet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {
	private DatagramSocket socket;
	private ThreadListner listner = null;
	
	private boolean status;
	public UDPServer(int port) throws SocketException{
	socket = new DatagramSocket(port);	
	status = true;
	
	}
	
	public void addListner(ThreadListner listner){
		this.listner = listner;
	}
	
	public void run(){
		
		while (status){
			try {
				DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
				socket.receive(packet);
				byte[] data = new byte[packet.getLength()];
				data = packet.getData();
				String Nachricht = new String(data);
				
				newMsg(Nachricht);
				System.out.println(Nachricht);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void Halt(){
		status = false;
	}
	
	private void newMsg(String msg){
		
		if (msg.contains("#HANDGESTURES#")){
			int ende=msg.lastIndexOf("#");
			String Nachricht= new String();
			for (int i = 14; i < ende; i++) {
				Nachricht += msg.charAt(i);
			}
			System.out.println(msg);
			
			if (listner != null){
				listner.onNewData(Nachricht);
			}
		}
	}
	
}

package True_False_Detection;

import java.net.InetAddress;

import com.leapmotion.leap.Finger;

public class TrueFalse {
	public static InetAddress hostIP;
	public static int hostPort;
	public static boolean toSend=false;
	public static String input=null;
	public static String output=null;
	
	public static String TCPMessage;
public static String start(Finger thumb, Finger index, Finger middle, Finger ring, Finger pinky)
{
	if (thumb != null && index != null && middle != null && ring != null && pinky != null) {

		// **************true***************
		if (!index.isExtended() && pinky.isExtended()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toSend=true;
			output="f";
			return "false";
		}
		// ****************false**************************
		if (index.isExtended() && !pinky.isExtended()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toSend=true;
			output="t";
			return "true";
		}
	} // Ende Finger erkannt?
	if(index==null||pinky==null) 
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "NullPointerExeption in TrueFalse - keine Finger erkannt";
		}
	else    
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toSend=true;
			output="e";
			return "e";
		}
	}
	

}

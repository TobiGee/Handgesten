package True_False_Detection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import communication.TCPClient;
import modules.Module;

import com.leapmotion.leap.*;

public class MainClass 
{
	public static void main(String RTLions[])
	{
		Module leapModule = new Module("Leap Motion","192.168.178.23",8888,true);
		TCPClient a = new TCPClient("was",leapModule);
		
		//if(a.send("TEST")) System.out.println("Test gesendet");
		//if(a.send("2")) System.out.println("2 Gesendet");
		//String message=a.receive();
		//System.out.println(message);
		//Controler und listener erzeugen
		Controller controller = new Controller();
		LeapListener listener = new LeapListener();
		
		
		//Dem Controler den Leap internen Listener hinzufügen
		controller.addListener(listener);
		
		
		
		// Keep this process running until Enter is pressed
	    System.out.println("Press Enter to quit...");
	    try 
	    {
	    	System.in.read(); //eine art getch für java
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	 // Remove the sample listener when done
        controller.removeListener(listener);
	}
}

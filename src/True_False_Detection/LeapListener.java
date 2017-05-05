package True_False_Detection;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;

/**
 * Leap Listener Extends from the Leap Motion Class Listener to compensate
 * the asynchronous behaviour of the Controller.
 * @author Tobias
 *
 */
public class LeapListener extends Listener{
	public static int counter;
	//überschriebenene fkt vom Listener
	public void onConnect(Controller controller) {
        System.out.println("Connected with the COntroller");
    }

    public void onFrame(Controller controller) {
    	
    	counter++;
    	//Erstellen eines Frame Objekts, welches sich um die Schnittstelle kümmert
    	Frame frame = controller.frame();
    	//erstellen der FInger
    	Finger pollex=null;
    	Finger index=null;
    	Finger medius=null;
    	Finger annularius=null;
    	Finger minimus=null;
    	for (Pointable p : frame.pointables()) {
			Finger tmp = new Finger(p);
			if (tmp.type() == Finger.Type.TYPE_THUMB) {
				pollex = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_INDEX) {
				index = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_MIDDLE) {
				medius = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_RING) {
				annularius = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_PINKY) {
				minimus = new Finger(p);
			}

		}
    	if(true)System.out.println(TrueFalse.start(pollex, index, medius, annularius, minimus));
    		
    }
}

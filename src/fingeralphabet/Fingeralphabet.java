package fingeralphabet;
import com.leapmotion.leap.Controller;

import java.io.IOException;

/**
 * Created by Tobias
 */
public class Fingeralphabet {


    public static void main(String[] args) {
        // Create a sample listener and controller
        AlphabetListener listener = new AlphabetListener();
        Controller controller = new Controller();
        //Allow access to Images from the Leap
        controller.setPolicy(Controller.PolicyFlag.POLICY_IMAGES);

        // Have the sample listener receive events from the controller
        controller.addListener(listener);


        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }
}

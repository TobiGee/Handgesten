package fingeralphabet;

//import  java.awt.Image;
//import java.awt.image.BufferedImage;
import com.leapmotion.leap.*;

import processing.core.PImage;
import processing.core.PApplet;


//import udp.SendStringChannel;

//UDP Packages
import java.io.*;
import java.net.*;
//import java.util.Iterator;				

public class AlphabetListener extends Listener implements ThreadListner {
	int ab = 0;
	float a = (float) 0.015;
	float ankles[] = new float[14];
	private UDPServer server;
	private String nachricht;
	public String letter = ";";
	public String letterOld = null;
	public String myIP = "192.168.178.23";
	public int myPort = 50035;
	public String targetIP = "192.168.188.23";
	public int targetPort = 8888;
	private int fingerCounter = 0;
/**
 * Konstruktor
 */
	public AlphabetListener() {
		try {
			server = new UDPServer(myPort);
			server.addListner(this);
			server.start();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void onConnect(Controller controller) {

	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		Finger thumb = null;
		Finger index = null;
		Finger middle = null;
		Finger ring = null;
		Finger pinky = null;

		for (Pointable p : frame.pointables()) {
			Finger tmp = new Finger(p);
			if (tmp.type() == Finger.Type.TYPE_THUMB) {
				thumb = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_INDEX) {
				index = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_MIDDLE) {
				middle = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_RING) {
				ring = new Finger(p);
			}
			if (tmp.type() == Finger.Type.TYPE_PINKY) {
				pinky = new Finger(p);
			}

		}
		
		


//		if (index.isExtended())
//			fingerCounter++;
//		if (middle.isExtended())
//			fingerCounter++;
//		if (ring.isExtended())
//			fingerCounter++;
//		if (pinky.isExtended())
//			fingerCounter++;
//		if (thumb.isExtended())
//			fingerCounter++;

		//
		// ******************true/false************************
		//
		//

		if (nachricht.equals("3")) {
			ab++;

			if (thumb != null && index != null && middle != null && ring != null && pinky != null) {

				// **************true***************
				if (!index.isExtended() && pinky.isExtended()) {
					if (this.letter.contains("false"))
						;
					else
						this.letter = ";false";
				}
				// ****************false**************************
				if (index.isExtended() && !pinky.isExtended()) {
					if (this.letter.contains("true"))
						;
					else
						this.letter = ";true";
				}
			} // Ende Finger erkannt?
		} // Ende "3"
			//
			// ********************************Counter*****************************************
			//
			//
		if (nachricht.equals("4")) {

			ab++;

			if (thumb != null && index != null && middle != null && ring != null && pinky != null) {

				// ********** Null *********
				if (fingerCounter == 0) {
					this.letter = ";0";
				}
				// ********** Eins *********
				if (fingerCounter == 1) {
					this.letter = ";1";
				}
				// ********** Zwei *********
				else if (fingerCounter == 2) {
					this.letter = ";2";
				}
				// ********** Drei *********
				else if (fingerCounter == 3) {
					this.letter = ";3";
				}
				// ********** Vier *********
				else if (fingerCounter == 4) {
					this.letter = ";4";
				}
				// ********** Fünf *********
				else if (fingerCounter == 5) {
					this.letter = ";5";
				}

			} // Ende der Schliefe zur art der gestenerkennung
		}
		fingerCounter = 0;

		// Doing UDP stuff

		if (ab % 26 == 0) {
			if (this.letter.equals(this.letterOld)) {
				System.out.println("Nix neues");

				// Hier jetzt Ausgabe via UDP
				// Erzeugen eines Sockets

				DatagramSocket socket;
				try {
					socket = new DatagramSocket();
					String message = "#HANDGESTURES#0" + this.letter + "#";
					byte[] b = message.getBytes();

					InetAddress host = InetAddress.getByName(targetIP);
					DatagramPacket request = new DatagramPacket(b, b.length, host, targetPort);
					socket.send(request);
					System.out.println("Packet versendet an:  " + request.getAddress() + ":" + request.getPort()
							+ " -> " + new String(request.getData()));

				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.letterOld = this.letter;

			} else {
				System.out.println(this.letter);

				// Hier jetzt Ausgabe via UDP
				// Erzeugen eines Sockets

				DatagramSocket socket;
				try {
					socket = new DatagramSocket();
					String message = "#HANDGESTURES#1" + this.letter + "#";
					byte[] b = message.getBytes();

					InetAddress host = InetAddress.getByName(targetIP);
					DatagramPacket request = new DatagramPacket(b, b.length, host, targetPort);
					socket.send(request);
					System.out.println("Packet versendet an:  " + request.getAddress() + ":" + request.getPort()
							+ " -> " + new String(request.getData()));

				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.letterOld = this.letter;
			} // Ende der Else bedingung

		} // Ende der UDP Ausgabe
		this.letter = ";";
	} // Ende der "Hauptklasse"

	public boolean angleAmongMetaProxi(Finger finger, float angle1, float angle2) {
		boolean isBetween = false;
		if (finger.bone(Bone.Type.TYPE_METACARPAL).direction()
				.angleTo(finger.bone(Bone.Type.TYPE_PROXIMAL).direction()) > angle1
				&& finger.bone(Bone.Type.TYPE_METACARPAL).direction()
						.angleTo(finger.bone(Bone.Type.TYPE_PROXIMAL).direction()) < angle2) {
			isBetween = true;
		}
		return isBetween;
	}

	public boolean angleAmongProxiInter(Finger finger, float angle1, float angle2) {
		boolean isBetween = false;
		if (finger.bone(Bone.Type.TYPE_PROXIMAL).direction()
				.angleTo(finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction()) > angle1
				&& finger.bone(Bone.Type.TYPE_PROXIMAL).direction()
						.angleTo(finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction()) < angle2) {
			isBetween = true;
		}
		return isBetween;
	}

	public boolean angleAmongInterDistal(Finger finger, float angle1, float angle2) {
		boolean isBetween = false;
		if (finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction()
				.angleTo(finger.bone(Bone.Type.TYPE_DISTAL).direction()) > angle1
				&& finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction()
						.angleTo(finger.bone(Bone.Type.TYPE_DISTAL).direction()) < angle2) {
			isBetween = true;
		}
		return isBetween;
	}

	@Override
	public void onNewData(String msg) {
		this.setNachricht(msg);

	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}

}

import javax.swing.UIManager;

import gui.initialScreen.JF_InitialFrame;

public class Launcher {

	public static void main(String[] args) {
		
		try { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } catch(Exception ignored){}
		
		JF_InitialFrame mainFrame = new JF_InitialFrame();
		mainFrame.setVisible(true);

	}

}

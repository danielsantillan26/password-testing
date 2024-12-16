package graphics;

import javax.swing.JFrame;

/**
 * The Frame class creates the necessary characteristics of the JFrame for the
 * Password Testing System. It adds the JPanel that has the labels and textboxes
 * needed for the program as well.
 * 
 * @author Daniel Santillan
 */
public class Frame extends JFrame {
	
	// Version
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * The Frame constructor adds the necessary characteristics of the JFrame
	 * and adds the JPanel with the login field.
	 */
	public Frame() {
		setTitle("Password Testing System");
		setSize(1200, 800);
		setResizable(false);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		PanelLogin page1 = new PanelLogin();
		add(page1);
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "Frame []";
	}

}

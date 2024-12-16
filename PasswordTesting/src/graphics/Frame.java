package graphics;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private CardLayout cl;
	private Container container;
	
	private PanelLogin page1;
	
	
	
	public Frame() {
		setTitle("Password Testing System");
		setSize(1200, 800);
		setResizable(false);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		page1 = new PanelLogin();
		add(page1);
	}
	
	

}

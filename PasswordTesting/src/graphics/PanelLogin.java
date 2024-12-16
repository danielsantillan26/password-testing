package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import users.UserRegistration;

/**
 * The PanelLogin class creates the JPanel for the Password Testing System. It
 * holds the labels, text fields, and buttons interacted with by the user. It
 * also communicates directly with back-end systems to determine whether a
 * username/password combination is valid.
 * 
 * @author Daniel Santillan
 */
public class PanelLogin extends JPanel {
	
	// Version
	private static final long serialVersionUID = 1L;
	
	// JPanels to be placed in BorderLayout
	private JPanel northPanel;
	private JPanel centerPanel;
	
	// Labels and textfields for panels
	private JLabel enterUsername;
	private JLabel enterPassword;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JLabel visiblePassword;
	private JLabel failed;
	
	// Buttons for panels
	private JButton enterInformation;
	private JButton showHidePassword;
	
	private boolean isPasswordShown;
	
	
	/**
	 * The panelLogin method sets the panel's layout and background, then adds
	 * the panels to be placed in BorderLayout.NORTH and BorderLayout.CENTER.
	 */
	public PanelLogin() {
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 255));
		
		prepareNorthPanel();
		prepareCenterPanel();
		
	}
	
	
	/**
	 * The prepareNorthPanel method sets up the header for the JPanel. It adds
	 * an image that serves as the header.
	 */
	private void prepareNorthPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(new Color(50, 153, 255));
		add(northPanel, BorderLayout.NORTH);
		
		// This image was created by myself using the Siegra font for the logo.
		JLabel pageHeader = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("DS_TECH_HEADER.png")));
		northPanel.add(pageHeader, BorderLayout.CENTER);
		
	}
	
	
	/**
	 * The prepareCenterPanel method sets up the center section for the JPanel.
	 * It sets up all the labels, textfields, and buttons involved in the system.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		centerPanel = new JPanel(sl);
		centerPanel.setBackground(new Color(173, 216, 255));
		add(centerPanel, BorderLayout.CENTER);
		
		// Local field setup
		Font helveticaBold50 = new Font("Helvetica", Font.BOLD, 50);
		Font helveticaBold40 = new Font("Helvetica", Font.BOLD, 40);
		Font helveticaBold30 = new Font("Helvetica", Font.BOLD, 30);
		Dimension textFieldDimension = new Dimension(500, 75);
		
		enterUsername = new JLabel("Username:");
		enterUsername.setFont(helveticaBold50);
		
		fieldUsername = new JTextField();
		fieldUsername.setMinimumSize(textFieldDimension);
		fieldUsername.setPreferredSize(textFieldDimension);		
		fieldUsername.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		enterPassword = new JLabel("Password:");
		enterPassword.setFont(helveticaBold50);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setMinimumSize(textFieldDimension);
		fieldPassword.setPreferredSize(textFieldDimension);	
		fieldPassword.setFont(helveticaBold40);
		
		visiblePassword = new JLabel();
		visiblePassword.setFont(helveticaBold30);
		
		enterInformation = new JButton("Confirm");
		enterInformation.setPreferredSize(new Dimension(200, 45));
		enterInformation.setFont(helveticaBold30);
		enterInformation.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for the enterInformation button's
			 * ActionListener determines whether the password is valid and, if
			 * so, determines whether an account under the proposed username
			 * already exists (through the UserRegistration class). This
			 * method determines the ending message that comes out below the
			 * password field.
			 * 
			 * @param e		ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = fieldUsername.getText();
				String password = String.valueOf(fieldPassword.getPassword());
				
				failed.setText("");
				failed.setForeground(Color.RED);
				Color darkGreen = new Color(34, 139, 34);
				
				if (password.length() < 8) {
					failed.setText("Password too short: must be at least 8 characters");
					return;
				}
				
				boolean needsSymbol = true;
				for (int i = 0; i < password.length(); i++) {
					if (!Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
						needsSymbol = false;
					}
				}
				
				if (needsSymbol) {
					failed.setText("Password unsafe: needs special symbol");
					return;
				}
				
				
				boolean needsCapitalLetter = true;
				for (int i = 0; i < password.length(); i++) {
					if (Character.isUpperCase(password.charAt(i))) {
						needsCapitalLetter = false;
					}
				}
				
				if (needsCapitalLetter) {
					failed.setText("Password unsafe: needs capital letter");
					return;
				}
				
				
				boolean needsLowercaseLetter = true;
				for (int i = 0; i < password.length(); i++) {
					if (Character.isLowerCase(password.charAt(i))) {
						needsLowercaseLetter = false;
					}
				}
				
				if (needsLowercaseLetter) {
					failed.setText("Password unsafe: needs lowercase letter");
					return;
				}
				
				
				boolean needsNumber = true;
				for (int i = 0; i < password.length(); i++) {
					if (Character.isDigit(password.charAt(i))) {
						needsNumber = false;
					}
				}
				
				if (needsNumber) {
					failed.setText("Password unsafe: needs digit");
					return;
				}
				
				
				for (int i = 0; i < password.length(); i++) {
					if (password.charAt(i) ==(' ')) {
						failed.setText("Password illegal: cannot have spaces");
						return;
					}
				}
				
				/**
				* Determining whether the user exists and whether the existing
				* username has the right password
				*/
				UserRegistration ur = new UserRegistration();
				if (ur.doesUserExist(username, password)) {
					if (ur.passwordMatch(username, password)) {
						failed.setForeground(darkGreen);
						failed.setText("Logged in!");
					} else {
						failed.setText("Wrong password.");
					}
				} else {
					failed.setForeground(darkGreen);
					failed.setText("Account created. Welcome!");
				}
			
			}
			
		});
		
		showHidePassword = new JButton("Show/HidePassword");
		showHidePassword.setPreferredSize(new Dimension(500, 45));
		showHidePassword.setFont(new Font("Helvetica", Font.BOLD, 30));
		isPasswordShown = false;
		showHidePassword.addActionListener(new ActionListener() {

			/**
			 * The actionPeformed method for the showHidePassword button's
			 * ActionListener shows or hides the password given by the user.
			 * If the password is not showing, the password will appear as a 
			 * JLabel above the password field. If the password is showing, the
			 * password will disappear.
			 * 
			 * @param e		ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String textVisiblePassword = String.valueOf(fieldPassword.getPassword());
				
				if (!isPasswordShown) {
					visiblePassword.setText(textVisiblePassword);
					repaint();
					isPasswordShown = true;
				} else {
					visiblePassword.setText("");
					repaint();
					isPasswordShown = false;
				}
				
			}
			
		});
		
		failed = new JLabel();
		failed.setFont(new Font("Helvetica", Font.BOLD, 40));
		failed.setForeground(Color.RED);
	
		centerPanel.add(enterUsername);
		centerPanel.add(fieldUsername);
		centerPanel.add(enterPassword);
		centerPanel.add(fieldPassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(enterInformation);
		centerPanel.add(showHidePassword);
		centerPanel.add(failed);
		
		// Setting up the SpringLayout constraints
		sl.putConstraint(SpringLayout.WEST, enterUsername, 150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterUsername, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, fieldUsername, 100, SpringLayout.EAST, enterUsername);
		sl.putConstraint(SpringLayout.NORTH, fieldUsername, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPassword, 150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, fieldPassword, 100, SpringLayout.EAST, enterPassword);
		sl.putConstraint(SpringLayout.NORTH, fieldPassword, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 100, SpringLayout.EAST, enterPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterInformation, 200, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterInformation, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 450, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, failed, 150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, failed, 420, SpringLayout.NORTH, centerPanel);
				
	}

	
	/**
	 * This is the toString method for this class. It prints the fields.
	 */
	@Override
	public String toString() {
		return "PanelLogin [northPanel=" + northPanel + ", centerPanel=" + centerPanel + ", enterUsername="
				+ enterUsername + ", enterPassword=" + enterPassword + ", fieldUsername=" + fieldUsername
				+ ", fieldPassword=" + fieldPassword + ", visiblePassword=" + visiblePassword + ", failed=" + failed
				+ ", enterInformation=" + enterInformation + ", showHidePassword=" + showHidePassword
				+ ", isPasswordShown=" + isPasswordShown + "]";
	}
	
	
	

}


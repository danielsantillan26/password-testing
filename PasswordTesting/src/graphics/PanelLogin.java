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

public class PanelLogin extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel northPanel;
	private JPanel centerPanel;
	
	private JLabel enterUsername;
	private JLabel enterPassword;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JLabel visiblePassword;
	private JLabel failed;
	
	private JButton enterInformation;
	private JButton showHidePassword;
	
	private boolean isPasswordShown;
	
	
	public PanelLogin() {
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 255));
		
		prepareNorthPanel();
		prepareCenterPanel();
		
	}
	
	
	
	private void prepareNorthPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(new Color(50, 153, 255));
		add(northPanel, BorderLayout.NORTH);
		
		// uses siegra font
		JLabel pageHeader = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("DS_TECH_HEADER.png")));
		northPanel.add(pageHeader, BorderLayout.CENTER);
		
	}
	
	
	
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		centerPanel = new JPanel(sl);
		centerPanel.setBackground(new Color(173, 216, 255));
		add(centerPanel, BorderLayout.CENTER);
		
		enterUsername = new JLabel("Username:");
		enterUsername.setFont(new Font("Helvetica", Font.BOLD, 50));
		
		fieldUsername = new JTextField();
		fieldUsername.setMinimumSize(new Dimension(500, 75));
		fieldUsername.setPreferredSize(new Dimension(500, 75));		
		fieldUsername.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		enterPassword = new JLabel("Password:");
		enterPassword.setFont(new Font("Helvetica", Font.BOLD, 50));
		
		fieldPassword = new JPasswordField();
		fieldPassword.setMinimumSize(new Dimension(500, 75));
		fieldPassword.setPreferredSize(new Dimension(500, 75));	
		fieldPassword.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		visiblePassword = new JLabel();
		visiblePassword.setFont(new Font("Helvetica", Font.BOLD, 30));
		
		enterInformation = new JButton("Confirm");
		enterInformation.setPreferredSize(new Dimension(200, 45));
		enterInformation.setFont(new Font("Helvetica", Font.BOLD, 30));
		enterInformation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = fieldUsername.getText();
				String password = String.valueOf(fieldPassword.getPassword());
				
				failed.setText("");
				failed.setForeground(Color.RED);
				
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
				
				
				UserRegistration ur = new UserRegistration();
				if (ur.doesUserExist(username, password)) {
					if (ur.passwordMatch(username, password)) {
						failed.setForeground(new Color(34, 139, 34));
						failed.setText("Logged in!");
					} else {
						failed.setText("Wrong password.");
					}
				} else {
					failed.setForeground(new Color(34, 139, 34));
					failed.setText("Account created. Welcome!");
				}
			
			}
			
		});
		
		showHidePassword = new JButton("Show/HidePassword");
		showHidePassword.setPreferredSize(new Dimension(500, 45));
		showHidePassword.setFont(new Font("Helvetica", Font.BOLD, 30));
		isPasswordShown = false;
		showHidePassword.addActionListener(new ActionListener() {

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

	
	
}


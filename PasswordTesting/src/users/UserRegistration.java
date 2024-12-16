package users;

import java.io.FileNotFoundException;

import files.FileWriter;

public class UserRegistration {

	private static final String filename = "users.txt";
	private static User[] users;


	public UserRegistration() {
		
	}

	
	@SuppressWarnings("unused")
	public void collectUsers() {
		FileWriter file = new FileWriter(filename);
		
		String[] existingUsers = file.readToArray();
		if (existingUsers == null) { return; }
		
		users = new User[existingUsers.length/2];
		
		if (existingUsers == null) { return; }
		
		for (int i = 0; i < existingUsers.length; i+=2) {
			users[i/2] = new User(existingUsers[i], existingUsers[i + 1]);
		}
		
		file.close();
	}
	
	
	public void archiveUsers() {
		if (users == null) { return; }
		FileWriter file = new FileWriter(filename);
		
		String[] existingUsers = new String[users.length * 2];
		for (int i = 0; i < users.length; i++) {
			existingUsers[2*i] = users[i].getUsername();
			existingUsers[2*i+1] = users[i].getPassword();
		}
		
		file.writeToFile(existingUsers);
		file.close();
		
	}
	
	
	public boolean doesUserExist(String username, String password) {
		collectUsers();
		
		boolean userExists = false;
		
		if (users == null) {
			addUser(username, password);
		}
		
		for (int i = 0; i < users.length; i++) {
			if (users[i].getUsername() == username) {
				return true;
			}
		}
		
		if (!userExists) {
			addUser(username, password);
		}
		
		return false;
	}
	
	
	public void addUser(String username, String password) {
		User[] temp;
		
		if (users != null) { 
			temp = users;
		} else {
			temp = new User[0];
		}
		
		users = new User[temp.length + 1];
		
		for (int i = 0; i < temp.length; i++) {
			users[i] = temp[i];
		}
		
		users[users.length - 1] = new User(username, password);
		
		archiveUsers();
		printUsers();
	}
	
	
	public boolean passwordMatch(String username, String password) {
		for (int i = 0; i < users.length; i++) {
			if (users[i].getUsername() == username) {
				if (users[i].getPassword() == password) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		return false;
	}
	
	
	public void printUsers() {
		for (User u : users) {
			System.out.println(u);
		}
	}
	
}

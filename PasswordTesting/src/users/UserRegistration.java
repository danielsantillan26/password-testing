package users;

import files.FileWriter;

/**
 * The UserRegistration class handles the preparation of the array of users
 * based on the information in users.txt. It also has methods to add new users
 * and determine whether a username and password match for login.
 * 
 * @author Daniel Santillan
 */
public class UserRegistration {

	// filename and user array
	private static final String filename = "users.txt";
	private static User[] users;


	/**
	 * This is a blank constructor.
	 */
	public UserRegistration() {
		
	}

	
	/**
	 * The collectUsers method creates a file, reads it, and creates the users
	 * array based on the file reading.
	 */
	public void collectUsers() {
		FileWriter file = new FileWriter(filename);
		
		String[] existingUsers = file.readToArray();
		if (existingUsers == null) { return; }
		
		users = new User[existingUsers.length/2];
		
		for (int i = 0; i < existingUsers.length; i+=2) {
			users[i/2] = new User(existingUsers[i], existingUsers[i + 1]);
		}
		
		file.close();
	}
	
	
	/**
	 * The archiveUsers method creates a String array based on the Users array
	 * and writes the new String array on the fresh file.
	 */
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
	
	
	/**
	 * The doesUserExist method obtains all the users and uses them to determine
	 * whether a particular user exists in the database (by username). If not,
	 * the user is added.
	 * 
	 * @param username		proposed username
	 * @param password		proposed password
	 * @return				whether the user exists
	 */
	public boolean doesUserExist(String username, String password) {
		collectUsers();
		
		boolean userExists = false;
		
		if (users == null) {
			addUser(username, password);
		}
		
		for (int i = 0; i < users.length; i++) {
			if (users[i].getUsername().equals(username)) {
				return true;
			}
		}
		
		if (!userExists) {
			addUser(username, password);
		}
		
		return false;
	}
	
	
	/**
	 * The addUser method adds a new user with the given username and password.
	 * The users array is reset with a new length to fit the new user.
	 * 
	 * @param username		proposed username
	 * @param password		proposed password
	 */
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
	}
	
	
	/**
	 * The passwordMatch method determines whether a given username has the
	 * given password. The method is run assuming the username is bound to be
	 * present in the users array.
	 * 
	 * @param username		username
	 * @param password		proposed password
	 * @return				does the password match?
	 */
	public boolean passwordMatch(String username, String password) {
		for (int i = 0; i < users.length; i++) {
			if (users[i].getUsername().equals(username)) {
				if (users[i].getPassword().equals(password)) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		return false;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "UserRegistration []";
	}
	
}

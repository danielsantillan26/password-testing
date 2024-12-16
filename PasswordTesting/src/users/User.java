package users;

/**
 * The User class is a representation of a user, with fields for a username and
 * password.
 * 
 * @author Daniel Santillan
 */
public class User {

	// Username and password fields
	private String username;
	private String password;
	
	
	/**
	 * The User constructor sets up a user based on the proposed username and 
	 * password.
	 * 
	 * @param username		the proposed username
	 * @param password		the proposed password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	/**
	 * The getUsername method returns the username of the object.
	 * 
	 * @return		the username
	 */
	public String getUsername() {
		return username;
	}

	
	/**
	 * The getPassword method returns the password of the object.
	 * 
	 * @return		the password
	 */
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * This is the toString method for the class. It prints the fields.
	 */
	@Override
	public String toString() {
		return "[" + username + ", " + password + "]";
	}
}

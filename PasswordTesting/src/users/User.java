package users;

public class User {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}

	
	public String getPassword() {
		return password;
	}
}

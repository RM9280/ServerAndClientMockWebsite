/**
 * Author: Yashdeep Deswal This is the base abstract class for user accounts.
 */
public abstract class Account {
	// Fields should be private and follow camelCase naming convention
	private String username; // The username for an account
	private String password; // The password for an account
	private int id;

	/**
	 * Constructor to initialize the account with a username and password.
	 *
	 * @param username The username for the account.
	 * @param password The password for the account.
	 */
	public Account(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public Account(String username, String password, String Profile, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public String getProfile() {
		return "";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the username of the account.
	 *
	 * @return The username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Verify if the provided password matches the account's password.
	 *
	 * @param tempPassword The password to be verified.
	 * @return True if the passwords match, false otherwise.
	 */
	public boolean verifyPassword(String tempPassword) {
		if (tempPassword == null) {
			return this.password == null;
		} else {
			return tempPassword.equals(this.password);
		}
	}

	/**
	 * Set a new password for the account.
	 *
	 * @param newPassword The new password to be set.
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * Get a string representation of the account.
	 *
	 * @return A string containing the username and the class name.
	 */
	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		return username + "  " + className;
	}

	/**
	 * Get the type of the account.
	 *
	 * @return The class name of the account.
	 */
	public String getAccountType() {
		return this.getClass().getSimpleName();
	}
}

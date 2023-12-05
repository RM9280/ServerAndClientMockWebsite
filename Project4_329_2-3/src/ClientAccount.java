/**
 * Author: Yashdeep Deswal
 * Represents a client account that extends the base Account class.
 */

/**
 * Represents a client account that extends the base Account class.
 */
public class ClientAccount extends Account {
	private String profile; // Profile information associated with the client account.

	/**
	 * Constructor to initialize a ClientAccount with a username, password, and
	 * profile information.
	 *
	 * @param u   The username for the client account.
	 * @param pw  The password for the client account.
	 * @param prf The profile information for the client account.
	 */
	public ClientAccount(String u, String pw, String prf, int id) {
		super(u, pw, id); // Call the constructor of the superclass (Account)
		this.profile = prf; // Set the profile information for the client account.
	}

	/**
	 * Get the profile information associated with the client account.
	 *
	 * @return The profile information.
	 */
	public String getProfile() {
		return this.profile;
	}
}

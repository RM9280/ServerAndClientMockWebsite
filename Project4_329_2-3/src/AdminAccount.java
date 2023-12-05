
/**
 * Author: Yashdeep Deswal
 * Represents an administrator account that manages a list of accounts.
 */
import java.util.ArrayList;
import java.util.List;

public class AdminAccount extends Account {
	private List<Account> accounts; // List of accounts managed by the administrator.

	/**
	 * Constructor to initialize an AdminAccount with a username, password, and a
	 * list of accounts.
	 *
	 * @param username     The username for the admin account.
	 * @param password     The password for the admin account.
	 * @param accountsList The list of accounts managed by the administrator.
	 */
	public AdminAccount(String username, String password, List<Account> accountsList, int id) {
		super(username, password, id);
		this.accounts = accountsList;
	}
}

//Rebecca Mantione: Creates an account for the customer
public class CustomerAccount extends Account {

	String profile;
	
	//Creates an account with a username, password, profile, and id
	public CustomerAccount(String u, String pw, String prf, int id) {
		super(u, pw, id);
		this.profile = prf;
	}

	//get the profile
	public String getProfile() {
		return this.profile;
	}
}

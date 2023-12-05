import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class Test_AccountsReader {

	@Test
	void test() {
	        HashMap<String, Account> accounts =  AccountsReader.readFile("Accounts.xml");
	        assertEquals(accounts.size(),4);
	        assertEquals(accounts.get("01").getUsername(),"Admin1");
	        assertEquals(accounts.get("01").getPassword(),"Admin99");
	        assertEquals(accounts.get("01").getAccountType(),"AdminAccount");
	        assertEquals(accounts.get("02").getUsername(),"Customer2");
	        assertEquals(accounts.get("02").getPassword(),"Customer99");
	        assertEquals(accounts.get("02").getAccountType(),"CustomerAccount");
	    }
	}



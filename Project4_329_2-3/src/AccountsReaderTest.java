import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountsReaderTest {
    @Test
    public void testAccountsRead(){
        HashMap<String, Account> accounts =  AccountsReader.readFile("Accounts.xml");
        System.out.println(accounts);
        assertEquals(accounts.size(),2);
        System.out.println(accounts.get("1").getPassword());
        assertEquals(accounts.get("1").getUsername(),"Admin1");
        assertEquals(accounts.get("1").getPassword(),"Admin99");
        assertEquals(accounts.get("1").getAccountType(),"AdminAccount");
        assertEquals(accounts.get("2").getUsername(),"Customer1");
        assertEquals(accounts.get("2").getPassword(),"Customer99");
        assertEquals(accounts.get("2").getAccountType(),"CustomerAccount");
    }
}

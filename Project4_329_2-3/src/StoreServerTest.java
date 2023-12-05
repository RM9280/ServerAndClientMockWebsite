//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.*;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
///**
// * Author: Yashdeep Deswal
// * Represents the server-side application for the Store.
// */
//
//class StoreServerTest {
//
//    private static ArrayList<Account> accounts;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize accounts before each test
//        accounts = new ArrayList<>();
//    }
//
//    @Test
//    void testLoginValidUser() {
//        // Create a test client account
//        Account testClient = new CustomerAccount("testuser", "password", "profile", 3);
//        accounts.add(testClient);
//
//        // Simulate input stream with test username and password
//        ByteArrayInputStream in = new ByteArrayInputStream("testuser\npassword\n".getBytes());
//        System.setIn(in);
//
//        // Create BufferedReader and PrintWriter for testing
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//
//        // Call login method
//        StoreThread.login(accounts, reader, printWriter);
//
//        // Verify that the correct account type is sent to the client
//        assertEquals("ClientAccount\n", writer.toString());
//    }
//
//    @Test
//    void testLoginInvalidUser() {
//        // Create a test client account
//        Account testClient = new CustomerAccount("testuser", "password", "profile", 3);
//        accounts.add(testClient);
//
//        // Simulate input stream with incorrect username and password
//        ByteArrayInputStream in = new ByteArrayInputStream("wronguser\nwrongpassword\n".getBytes());
//        System.setIn(in);
//
//        // Create BufferedReader and PrintWriter for testing
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//
//        // Call login method
//        StoreThread.login(accounts, reader, printWriter);
//
//        // Verify that an error message is sent to the client
//        assertEquals("Invalid username or invalid password\n", writer.toString());
//    }
//
//    @Test
//    void testSendAccountList() {
//
//        // Create test accounts
//        Account testAdmin = new AdminAccount("adminuser", "adminpassword", accounts, 3);
//        Account testClient = new CustomerAccount("testuser", "password", "profile", 3);
//        accounts.add(testAdmin);
//        accounts.add(testClient);
//        // Simulate input stream with incorrect username and password
//        ByteArrayInputStream in = new ByteArrayInputStream("adminuser\nadminpassword\n".getBytes());
//        System.setIn(in);
//
//        // Create BufferedReader and PrintWriter for testing
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//
//        // Call login method
//        StoreThread.login(accounts, reader, printWriter);
//
//        StringWriter accWriter = new StringWriter();
//        PrintWriter printWriter2 = new PrintWriter(accWriter);
//        // Call sendAccountList method
//        StoreThread.sendAccountList(printWriter2);
//
//        // Verify that the correct account information is sent to the client
//        assertEquals("adminuser%AdminAccount\n" +
//                "testuser%CustomerAccount\n" +
//                "Done\n", accWriter.toString());
//    }
//
//    @Test
//    void testSendProfile() {
//        // Create a test client account with a profile
//        Account testClient = new CustomerAccount("testuser", "password", "Test Profile");
//        accounts.add(testClient);
//
//        // Create PrintWriter for testing
//        ByteArrayInputStream in = new ByteArrayInputStream("testuser\npassword\n".getBytes());
//        System.setIn(in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//
//        // Set the userAccount to the test client
//        StoreThread.login(accounts, reader, printWriter);
//
//        StringWriter writer2 = new StringWriter();
//        PrintWriter printWriter2 = new PrintWriter(writer2);
//
//        // Call sendProfile method
//        StoreThread.sendProfile(printWriter2);
//
//        // Verify that the correct profile information is sent to the client
//        assertEquals("Test Profile\n", writer2.toString());
//    }
//
//    @Test
//    void testChangePasswordValid() {
//        // Create a test client account
//        Account testClient = new CustomerAccount("testuser", "password", "profile");
//        accounts.add(testClient);
//
//     // Create PrintWriter for testing
//        ByteArrayInputStream in = new ByteArrayInputStream("password\nnewpassword\n".getBytes());
//        System.setIn(in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//
//
//        // Call changePassword method
//        StoreThread.changePassword(testClient, reader, printWriter);
//
//        // Verify that a success message is sent to the client
//        assertEquals("Done\n", writer.toString());
//
//        // Verify that the password has been changed
//        assertEquals("newpassword", testClient.getPassword());
//    }
//
//    @Test
//    void testChangePasswordInvalid() {
//        // Create a test client account
//        Account testClient = new CustomerAccount("testuser", "password", "profile");
//        accounts.add(testClient);
//
//     // Create PrintWriter for testing
//        ByteArrayInputStream in = new ByteArrayInputStream("testuser\nwrongpassword\n".getBytes());
//        System.setIn(in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//
//        // Call changePassword method
//        StoreThread.changePassword(testClient, reader, printWriter);
//
//        // Verify that an error message is sent to the client
//        assertEquals("Invalid Password Entered\n", writer.toString());
//
//        // Verify that the password has not been changed
//        assertEquals("password", testClient.getPassword());
//    }
//
//    @Test
//    void testReadAccounts() {
//        // Call readAccounts method to populate accounts list
//        StoreThread.readAccounts(accounts);
//        // Verify that the accounts list is populated correctly
//        assertEquals(1, accounts.size());
//        assertEquals("testuser", accounts.get(0).getUsername());
//        assertEquals("CustomerAccount", accounts.get(0).getAccountType());
//        assertEquals("profile", ((CustomerAccount) accounts.get(0)).getProfile());
//    }
//}

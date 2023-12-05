import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.control.Label;

public class StoreThread extends Thread {

	public HashMap<String, Account> accounts;
    public HashMap<String,String> inventory;
	private Account userAccount;
	private BufferedReader incoming;
	private PrintWriter outgoing;
	private Socket client;
	private boolean running = true;

	public void terminateClient() {

		running = false;
		try {
			incoming.close();
			outgoing.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StoreThread(Socket client) {
		this.client = client;
	}
	
	   public StoreThread() {
	    	
	    }


	public synchronized void run() {
		// Accept next connection request and handle it.
		try {
			accounts = AccountsReader.readFile("Accounts.xml");
			inventory = InventoryReader.readFile("Inventory.xml");
			incoming = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			outgoing = new PrintWriter(this.client.getOutputStream());

			while (client.isConnected()) {
				String lineFromClient = incoming.readLine();
				if (lineFromClient == null) {
					// A null from incoming.readLine() indicates that
					// end-of-stream was encountered.
					throw new IOException("Connection was opened, " + "but client did not send any input.");
				}
				System.out.println();
				System.out.println(lineFromClient);
				System.out.println();
				if (lineFromClient.equals("Quit")) {
					terminateClient();

				} else if (lineFromClient.equals("Login")) {
					login(accounts, incoming, outgoing);
				} else if (lineFromClient.equals("Send Account List")) {
					sendAccountList(outgoing);
				} else if (lineFromClient.equals("Send Profile")) {
					sendProfile(outgoing);
				} else if (lineFromClient.equals("Change Password")) {
					changePassword(userAccount, incoming, outgoing);
				} else if (lineFromClient.equals("Inventory")) {
					sendInventory(outgoing);
				} else if (lineFromClient.equals("Order")) {
					getOrder(incoming);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			terminateClient();
		}
	}

	public synchronized void login(HashMap<String, Account> accountList, BufferedReader incoming,
			PrintWriter outgoing) {

		try {
			String usernameInput = incoming.readLine();
			String passwordInput = incoming.readLine();
			boolean found = false;
			Iterator accounts = accountList.values().iterator();
			while (accounts.hasNext()) {
				Account account = (Account) accounts.next();
				if (account.getUsername().equals(usernameInput)) {
					boolean verifyPass = account.verifyPassword(passwordInput);
					if (verifyPass) {
						found = true;
						userAccount = account;
						if (account instanceof AdminAccount) {
							outgoing.println("AdminAccount");
						} else if (account instanceof CustomerAccount) {
							outgoing.println("ClientAccount");
						}
					} else {
						outgoing.println("invalid username or invalid password");
					}
					outgoing.flush();
					return; // Exit the loop once the account is found.
				}
			}
			if (found == false) {
				outgoing.println("invalid username or invalid password");
				outgoing.flush();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			terminateClient();
		}

	}

	public synchronized void sendAccountList(PrintWriter outgoing) {

		Iterator accs = accounts.values().iterator();
		while (accs.hasNext()) {
			Account account = (Account) accs.next();
			outgoing.println(account.getUsername() + "%" + account.getAccountType());
			outgoing.flush();
		}
		outgoing.println("Done");
		outgoing.flush();

	}

	public synchronized void sendProfile(PrintWriter outgoing) {

		if (userAccount instanceof CustomerAccount) {
			CustomerAccount c = (CustomerAccount) userAccount;
			outgoing.println(c.getProfile());
			outgoing.flush();
			return;
		}

	}

	public synchronized void changePassword(Account userAccount, BufferedReader incoming, PrintWriter outgoing) {
		try {
			String oldPass = incoming.readLine();
			String newPass = incoming.readLine();

			Boolean isCorrect = userAccount.verifyPassword(oldPass);
			if (isCorrect == true) {
				userAccount.setPassword(newPass);
				writeAccounts(accounts);
				outgoing.println("Done");
				outgoing.flush();
				return;
			} else {
				outgoing.println("Invalid Password Entered");
				outgoing.flush();
				return;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			terminateClient();
		}

	}

	public synchronized void writeAccounts(HashMap<String, Account> accounts) {
		try {
			PrintWriter writer = new PrintWriter("Accounts.xml");
			writer.println("<ACCOUNTS>");
			for (Account acc : accounts.values()) {
				if (acc instanceof AdminAccount) {
					writer.println("<ADMINISTRATOR>");
					writer.println("<id>");
					writer.println(acc.getId());
					writer.println("</id>");
					writer.println("<name>");
					writer.println(acc.getUsername());
					writer.println("</name>");
					writer.println("<password>");
					writer.println(acc.getPassword());
					writer.println("</password>");
					writer.println("</ADMINISTRATOR>");
				} else if (acc instanceof CustomerAccount) {
					writer.println("<CUSTOMER>");
					writer.println("<id>");
					writer.println(acc.getId());
					writer.println("</id>");
					writer.println("<name>");
					writer.println(acc.getUsername());
					writer.println("</name>");
					writer.println("<password>");
					writer.println(acc.getPassword());
					writer.println("</password>");
					writer.println("<profile>");
					writer.println(acc.getProfile());
					writer.println("</profile>");
					writer.println("</CUSTOMER>");
				}
			}
			writer.println("</ACCOUNTS>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("Error writing accounts: " + e);
			terminateClient();
		}

	}

	public synchronized static void sendInventory(PrintWriter outgoing) {

		HashMap<String, String> inventoryMap = InventoryReader.readFile("Inventory.xml");
		for (String key : inventoryMap.keySet()) {
			outgoing.println(key);
			System.out.println("Key" + key);
			outgoing.flush();
			outgoing.println(inventoryMap.get(key));
			System.out.println(inventoryMap.get(key));
			outgoing.flush();
		}
		outgoing.println("Done");
		outgoing.flush();

		outgoing.println("Done");
		outgoing.flush();
		return;

		// Cusromer Orders
//        a. ID of customer who placed the order
//        b. Stock number of the product
//        c. Quantity of the product
	}


    public synchronized void viewOrders(PrintWriter outgoing){
    	
  	   
        DataInputStream input;   

        String fileName = "data_file";

        try {
            input = new DataInputStream(new FileInputStream(fileName));
        }
        catch (IOException e) {
            System.out.println("Can't open input file \"" + fileName + "\".");
            return;
        }

        try {
        	int bytesRead = input.readInt();
        	while (bytesRead != -1)
        	{

                int stockNumber = input.readInt();
                int customerID = input.readInt();
                int quantity = input.readInt();
				if (inventory == null) {
                	inventory = InventoryReader.readFile("Inventory.xml");
                }
                String description = inventory.get(String.valueOf(stockNumber));
                outgoing.println(stockNumber);
                outgoing.println(description);
                outgoing.println(quantity);
                outgoing.flush();
            }
        }
        catch (EOFException e) {
        }
        catch (IOException e) {
            System.out.println("Error occurred during input/output.");
            System.out.println(e.toString());
        }
        finally {
            try {
                input.close();
            }
            catch (IOException e) {
                System.out.println("Error occurred while closing the input stream.");
                System.out.println(e.toString());
            }
        }

 


}

	public synchronized static void getOrder(BufferedReader incoming) {
		try {
			System.out.println("Server is waiting for order");
			
			String stockNumber = incoming.readLine();
						
			System.out.println("Stock Number" + stockNumber);
			String quantity = incoming.readLine();
			
			System.out.println("Quantity" + quantity);

			OutputStream copy = new FileOutputStream("binary.dat", true);
			copy.write(Integer.parseInt(stockNumber));
			copy.write(Integer.parseInt(quantity));
			copy.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

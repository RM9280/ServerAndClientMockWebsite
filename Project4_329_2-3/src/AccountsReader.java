import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AccountsReader {

	public static HashMap<String, Account> readFile(String fileName) {
		HashMap<String, Account> accounts = new HashMap<>();
		try { // Create the input stream.
			Scanner scanner = new Scanner(new File(fileName));
			String id = null;
			String name = null;
			String password = null;
			String profile = null;
			boolean adminAccount = false;
			ArrayList<Account> accountsList = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.startsWith("<ADMINISTRATOR>")) {
					adminAccount = true;
				} else if (line.startsWith("<CUSTOMER>")) {
					adminAccount = false;
				} else if (line.startsWith("<id>")) {
					id = scanner.nextLine().trim();
				} else if (line.startsWith("<name>")) {
					name = scanner.nextLine().trim();
				} else if (line.startsWith("<password>")) {
					password = scanner.nextLine().trim();
				} else if (line.startsWith("<profile>")) {
					profile = scanner.nextLine().trim();
				} else if (line.startsWith("</ADMINISTRATOR>")) {
					AdminAccount acc = new AdminAccount(name, password, accountsList, Integer.parseInt(id));
					accountsList.add(acc);
					accounts.put(id, acc);
				} else if (line.startsWith("</CUSTOMER>")) {
					CustomerAccount acc = new CustomerAccount(name, password, profile, Integer.parseInt(id));
					accountsList.add(acc);
					accounts.put(id, acc);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file data.dat!");
		}
		return accounts;
	}
}

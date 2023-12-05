//Rebecca Mantione: Reads the stock number and description out of the XML file and puts it into a hashmap

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryReader {

	public static HashMap<String, String> readFile(String filename) {
		String inventoryScanner; // Full String that has been read
		HashMap<String, String> inventory = new HashMap<String, String>();
		try {
			// Scans the XML file and puts the stocknumber and description into a hashmap
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				inventoryScanner = scanner.nextLine();
				System.out.println(inventoryScanner);
				if (inventoryScanner.equals("<PRODUCT>")) {
					inventoryScanner = scanner.nextLine();
					inventoryScanner = scanner.nextLine();
					String stockNumber = inventoryScanner;
					inventoryScanner = scanner.nextLine();
					inventoryScanner = scanner.nextLine();
					inventoryScanner = scanner.nextLine();
					String description = inventoryScanner;
					inventory.put(stockNumber, description);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file data.dat!");
		}
		return inventory;
	}
}

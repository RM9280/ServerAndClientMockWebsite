//Rebecca Mantione: tests to see if InventoryReader works

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class InventoryReaderTest {

	@Test
	// uses Inventory Reader to read in the XML file and then compares the
	// description
	// to make sure it stored the right data into the hashmap
	void test() throws FileNotFoundException {
		HashMap<String, String> inventory = new HashMap<String, String>();
		inventory = InventoryReader.readFile("inventory.xml");
		System.out.println(inventory.size());
		assert inventory.size() == 1 : "Wrong Size";
		for (String key : inventory.keySet()) {
			System.out.println(inventory.get(key));
			assert inventory.get(key).equals("Thingamajig") : "Test failed";

		}

	}
}

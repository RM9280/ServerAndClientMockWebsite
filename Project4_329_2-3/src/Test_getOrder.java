//Rebecca Mantione: Tests to make sure the login is working

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_getOrder {
//	
//	StoreServer server;	
//	
//	String signal;
//	int LISTENING_PORT = 32007;
//	private static PrintWriter outgoing;
//	static BufferedReader incoming;
//	
//	@Test
////	// Test to make sure it is sending it as a custome/r account
//	void test1() {
//	
//		try {
//			Socket socket = new Socket("localhost", LISTENING_PORT);
//			outgoing = new PrintWriter(socket.getOutputStream());
//			PrintWriter outgoing = new PrintWriter(socket.getOutputStream());
//			outgoing.println("Order");
//			System.out.println("WAITING...");
//			
//			String usernameInput = "6";
//			String passwordInput = "7";
//			
//			System.out.println(usernameInput);
//			System.out.println(passwordInput);
//			
//			outgoing.println(usernameInput);
//			outgoing.println(passwordInput);
//			outgoing.flush();
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	

	StoreServer server;

	String signal;
	int LISTENING_PORT = 32007;
	private static PrintWriter outgoing;
	static BufferedReader incoming;
	int storeID;
	int storeStock;

	@Test
	// Test to make sure it is sending it as a customer account
	void test1() {

		try {
			Socket socket = new Socket("localhost", LISTENING_PORT);
			outgoing = new PrintWriter(socket.getOutputStream());
			outgoing.println("9");
			System.out.println("sent 9");
			outgoing.println("8");
			System.out.println("sent 8");
			outgoing.flush();

			System.out.println("reach me");

			String sourceName = "binary.dat"; // name of the document being read
			InputStream source; // Stream for reading from the source file.

			try {
				Thread.sleep(500);
				source = new FileInputStream(sourceName); // Creates a file input stream
				while (true) {
					// reads three numbers in the file
					int id = source.read();
					int stock = source.read();
					if (id < 0 || stock < 0) {
						break;
					}
					System.out.println("id" + id);
					storeID = id;
					System.out.println("stock" + stock);
					storeStock = stock;
					System.out.println(storeID);
					System.out.println(storeStock);

				}
				source.close(); // closes the source
			} catch (FileNotFoundException | InterruptedException e) {
				System.out.println("Can't find file \"" + sourceName + "\"."); // prints out if error occurs
				return;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assert storeID == 9 &&  storeStock == 8: "Error: They do not match";
	}
//	}

}

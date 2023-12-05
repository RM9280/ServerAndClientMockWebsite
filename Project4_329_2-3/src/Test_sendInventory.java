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
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_sendInventory {

	StoreServer server;

	String signal;
	String signal2;
	int LISTENING_PORT = 43009;
	private static PrintWriter outgoing;
	static BufferedReader incoming;

	@Test
	// Test to make sure it is sending it as a customer account
	void test1() {

		try {
			Socket socket = new Socket("localhost", LISTENING_PORT);
			incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("reach me");

			String sourceName = "binary.dat"; // name of the document being read
			InputStream source; // Stream for reading from the source file.

			signal = incoming.readLine();
			signal2 = incoming.readLine();
			System.out.println("Signal:" + signal);
			System.out.println("Signal2:" + signal2);
			String total = signal+" " + signal2;
			System.out.println(total);
			assert total.equals("45 Thingamajig") : "Error: They do not match";
			if (signal.equals(null)) {

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


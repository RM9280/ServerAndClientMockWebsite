
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Test_getOrder_helper {

	public static final int LISTENING_PORT = 32007;
	static BufferedReader incoming;
	private static PrintWriter outgoing;

	public static void main(String[] args) {

		ServerSocket listener; // Listens for incoming connections.
		Socket connection; // For communication with the connecting program.

		try {

//				for (int i = 0; i < 3; i++) {
			listener = new ServerSocket(LISTENING_PORT);
			System.out.println("Listening on port " + LISTENING_PORT);

			// Accept next connection request and handle it.
			connection = listener.accept();

			incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			outgoing = new PrintWriter(connection.getOutputStream());

			StoreThread.getOrder(incoming);

//				}
		} catch (Exception e) {
			System.out.println("Sorry, the server has shut down.");
			System.out.println("Error:  " + e);
			return;
		}

	}

}

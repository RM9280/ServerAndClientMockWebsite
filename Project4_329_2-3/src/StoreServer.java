import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreServer {

    private static int LISTENING_PORT = 32007;

    StoreServer(){

    }
    public static void main(String[] args) {

        if (args.length > 0) {
            int port = Integer.parseInt(args[0]);
            LISTENING_PORT = port;
        }

        ServerSocket listener;  // Listens for incoming connections.
        Socket clientConnection;      // For communication with the connecting program.
        try {
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port " + LISTENING_PORT);
            int clientCount = 1;
            while(true) {
                clientConnection = listener.accept();
                System.out.println("New Client Listening No: " + clientCount + "IP Address : " + clientConnection.getInetAddress());
                clientCount++;
                StoreThread client = new StoreThread(clientConnection);
                client.start();
            }
        }
        catch (Exception e) {
            System.out.println("Sorry, the server has shut down.");
            System.out.println("Error:  " + e);
            return;
        }

    }
}
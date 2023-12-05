import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Author: Yashdeep Deswal
 * Represents the main class of the Store application.
 */
public class Store extends Application {

    private SceneManager scnMngr; // The scene manager for handling scenes.
    private Socket connection;

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Store str = new Store();
        launch(args);
    }

    /**
     * Constructor to initialize the Store.
     */
    public Store() {
        scnMngr = new SceneManager(); // Initialize the scene manager.
    }

    /**
     * Start method called when the JavaFX application is launched.
     *
     * @param primaryStage The primary stage of the application.
     * @throws Exception If an exception occurs.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        scnMngr.setStage(primaryStage); // Set the primary stage for the scene manager.
        scnMngr.setScene(SceneManager.SceneType.login);
        primaryStage.show(); // Show the primary stage.
    }

    /**
     * Stop method called when the application is closing.
     */
    @Override
    public void stop() {
        try {
            connection = SceneManager.getSocket(); // Get the socket connection from the scene manager.
            if (connection != null) {
                PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
                outgoing.println("Quit"); // Send a quit message to the server.
                outgoing.flush();
                connection.close(); // Close the socket connection.
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions that occur.
        }
    }
}

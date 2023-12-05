
/**
 * Author: Yashdeep Deswal
 * Represents the client menu scene.
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ClientScene extends SceneBasic {

	/**
	 * Initializes the ClientScene.
	 */
	public ClientScene() {
		super("Client Scene"); // Call the constructor of the superclass (SceneBasic)
		BorderPane root = new BorderPane();
		scene = new Scene(root, 500, 300); // Set scene dimensions

		Label title = new Label("Client Menu");
		title.setAlignment(Pos.CENTER);
		root.setTop(title); // Set the title label at the top of the scene

		VBox buttons = new VBox(20);

		// Button for viewing the user profile
		Button profile = new Button("Show Profile");
		profile.setAlignment(Pos.CENTER);
		profile.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.profile));

		// Button for changing the password
		Button changePass = new Button("Change Password");
		changePass.setAlignment(Pos.CENTER);
		changePass.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.changePassword));

		// Button for logging out
		Button logout = new Button("Log out");
		logout.setAlignment(Pos.CENTER);
		logout.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.login));

		// Add buttons to the VBox
		buttons.getChildren().addAll(profile, changePass, logout);
		root.setCenter(buttons); // Set the VBox as the center of the BorderPane
	}
}

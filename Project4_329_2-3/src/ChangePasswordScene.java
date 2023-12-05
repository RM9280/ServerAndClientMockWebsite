
/**
 * Author: Yashdeep Deswal
 * Represents a scene for changing the password.
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChangePasswordScene extends SceneBasic {
	private PasswordField oldPassword; // Field for entering the old password.
	private PasswordField newPassword; // Field for entering the new password.
	private Account currentAccount; // The current account for which the password is being changed.
	private BorderPane root; // Creates the instance of the borderpane

	/**
	 * Initializes the ChangePasswordScene.
	 */
	public ChangePasswordScene() {
		root = new BorderPane();
		scene = new Scene(root, 500, 300);

		Label title = new Label("Change Password");
		root.setTop(title);
		title.setFont(Font.font(40));
		root.setAlignment(title, Pos.TOP_CENTER);

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		Label oldPass = new Label("Enter Old Password");
		Label newPass = new Label("Enter New Password");

		oldPassword = new PasswordField();
		newPassword = new PasswordField();

		gridPane.add(oldPass, 0, 0);
		gridPane.add(newPass, 1, 0);
		gridPane.add(oldPassword, 0, 1);
		gridPane.add(newPassword, 1, 1);

		root.setCenter(gridPane);

		Button clientMenu = new Button("Client Menu");
		clientMenu.setAlignment(Pos.CENTER);
		clientMenu.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.customer));

		Button changePassBtn = new Button("Change Password");
		changePassBtn.setOnAction(e -> change());

		gridPane.add(changePassBtn, 1, 2);
		gridPane.add(clientMenu, 2, 2);

		BorderPane.setMargin(gridPane, new Insets(20, 20, 20, 20));

	}

	/**
	 * Performs the password change operation.
	 */
	public void change() {

		try {
			Socket connection = SceneManager.getSocket();
			BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
			outgoing.println("Change Password");
			outgoing.println(oldPassword.getText());
			outgoing.println(newPassword.getText());
			outgoing.flush();
			System.out.println("WAITING...");
			try {
				String serverRes = incoming.readLine();
				System.out.println("RECEIVED:  " + serverRes);
				if (serverRes.equals("Done")) {
					SceneManager.setScene(SceneManager.SceneType.login);
				} else {
					Label invalid = new Label(serverRes);
					root.setBottom(invalid);
				}

			} catch (IOException i) {
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the current account for password change.
	 *
	 * @param currentAccount The current account.
	 */
	public void setAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}
}

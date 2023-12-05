
/**
 * Author: Yashdeep Deswal
 * Represents a scene for user login.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScene extends SceneBasic {
	private TextField userText; // Field for entering the username.
	private PasswordField passText; // Field for entering the password.
	private ArrayList<Account> accounts; // List of user accounts.
	private Label statusLbl; // Label to display login status.

	/**
	 * Initializes the LoginScene with a list of accounts.
	 */
	public LoginScene() {
		super("Login Menu");
		this.accounts = new ArrayList();

		BorderPane root = new BorderPane();
		scene = new Scene(root, 500, 300);

		Label title = new Label("Login Menu");
		title.setFont(Font.font(40));
		statusLbl = new Label();
		root.setTop(title);
		root.setAlignment(title, Pos.TOP_CENTER);
		root.setBottom(statusLbl);

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		Label username = new Label("Username");
		Label password = new Label("Password");

		userText = new TextField();
		passText = new PasswordField();

		gridPane.add(username, 0, 0);
		gridPane.add(userText, 1, 0);
		gridPane.add(password, 0, 1);
		gridPane.add(passText, 1, 1);

		root.setCenter(gridPane);
		BorderPane.setMargin(gridPane, new Insets(20, 20, 20, 20));

		Button logInBtn = new Button("Login");
		logInBtn.setOnAction(e -> login());

		Button settingBtn = new Button("Settings");
		settingBtn.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.settings));

		Button chatBtn = new Button("Chat");
		chatBtn.setOnAction(e -> {
			CustomerChat chats = new CustomerChat();
			chats.start(new Stage());
		});

		gridPane.add(logInBtn, 0, 2);
		gridPane.add(settingBtn, 1, 2);
		gridPane.add(chatBtn, 2, 2);
	}

	/**
	 * Performs the login operation.
	 */

	public void login() {
		try {
			String usernameInput = userText.getText();
			String passwordInput = passText.getText();
			Socket connection = SceneManager.getSocket();
			if (connection == null) {
				connection = new Socket("localhost", 32007);
				SceneManager.setSocket(connection);
			}

			BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
			outgoing.println("Login");
			outgoing.println(usernameInput);
			outgoing.println(passwordInput);
			outgoing.flush();
			System.out.println("WAITING...");
			try {
				String serverRes = incoming.readLine();
				System.out.println("RECEIVED:  " + serverRes);
				if (serverRes.equals("AdminAccount")) {
					SceneManager.setScene(SceneManager.SceneType.admin);
				} else if (serverRes.equals("ClientAccount")) {
					SceneManager.setScene(SceneManager.SceneType.customer);
				} else {
					statusLbl.setText(serverRes);
				}
			} catch (IOException i) {
				System.out.println(i);
			}

		} catch (Exception e) {
			System.out.println("Sorry, an error has occurred.  Connection lost.");
			System.out.println(e.toString());
			System.exit(1);
		}

	}
}

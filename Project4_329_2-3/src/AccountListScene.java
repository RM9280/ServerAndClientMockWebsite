
/**
 * Author: Yashdeep Deswal
 * Represents a scene displaying a list of accounts.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AccountListScene extends SceneBasic {
	GridPane gridPane = null;
	Button adminMenu = null;
	Button logOut = null;
	int accountCount = 1;

	/**
	 * Constructor to initialize the AccountListScene with a list of accounts.
	 *
	 */
	public AccountListScene() {

		BorderPane root = new BorderPane();
		scene = new Scene(root, 500, 300);

		Label title = new Label("Account List");
		root.setTop(title);
		title.setFont(Font.font(40));
		root.setAlignment(title, Pos.TOP_CENTER);

		gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		Label userName = new Label("Username");
		Label accountType = new Label("Account Type");

		userName.setFont(Font.font(20));
		accountType.setFont(Font.font(20));

		gridPane.add(userName, 0, 0);
		gridPane.add(accountType, 1, 0);
		root.setCenter(gridPane);

		adminMenu = new Button("AdminMenu");
		adminMenu.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.admin));

		logOut = new Button("LogOut");
		logOut.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.login));

		gridPane.add(adminMenu, 2, accountCount);
		gridPane.add(logOut, 3, accountCount);

		BorderPane.setMargin(gridPane, new Insets(20, 20, 20, 20));

	}

	public void addAccountLabel(String userName, String accountType) {
		Label accName = new Label(userName);
		Label accType = new Label(accountType);
		gridPane.add(accName, 0, accountCount);
		gridPane.add(accType, 1, accountCount);
		accountCount++;
	}

	public void getAccountList() {
		try {
			Socket connection = SceneManager.getSocket();
			BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
			outgoing.println("Send Account List");
			outgoing.flush();
			System.out.println("WAITING...");

			boolean done = false;
			while (!done) {
				try {
					String serverRes = incoming.readLine();
					System.out.println("RECEIVED:  " + serverRes);
					if ("Done".equals(serverRes)) {
						done = true;
						break;
					}
					String[] result = serverRes.split("%");
					String username = result[0];
					String accountType = result[1];
					addAccountLabel(username, accountType);
				} catch (IOException i) {
					System.out.println(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

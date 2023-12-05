
/**
 * Author: Yashdeep Deswal
 * Represents the administrator menu scene.
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class AdminScene extends SceneBasic {

	private AdminAccount currentAccount; // The current administrator account.

	public AdminScene() {
		super("Administrator Menu");
		BorderPane root = new BorderPane();
		scene = new Scene(root, 500, 300);

		Label title = new Label("Administrator Menu");
		root.setTop(title);
		title.setFont(Font.font(40));
		root.setAlignment(title, Pos.TOP_CENTER);

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		Button listAccount = new Button("List Accounts");
		listAccount.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.accountList));

		Button changePass = new Button("Change Password");
		changePass.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.changePassword));

		Button logOut = new Button("Logout");
		logOut.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.login));

		gridPane.add(listAccount, 0, 0);
		gridPane.add(changePass, 0, 1);
		gridPane.add(logOut, 0, 2);

		BorderPane.setMargin(gridPane, new Insets(20, 20, 20, 20));

		root.setCenter(gridPane);
	}

}

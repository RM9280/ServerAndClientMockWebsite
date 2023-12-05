import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProfileScene extends SceneBasic {

	Label profileTxt;

	public ProfileScene() {
		super("Customer Menu");
		BorderPane root = new BorderPane();
		scene = new Scene(root, 500, 300);

		Label title = new Label("Customer Menu");
		root.setTop(title);
		title.setFont(Font.font(40));
		root.setAlignment(title, Pos.TOP_CENTER);

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		Label profile = new Label();
		profile.setText("Profile:");

		profileTxt = new Label();

		Button clientMenu = new Button("Client Menu");
		clientMenu.setAlignment(Pos.CENTER);
		clientMenu.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.customer));

		Button logout = new Button("Log out");
		logout.setAlignment(Pos.CENTER);
		logout.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.login));

		gridPane.add(profile, 0, 0);
		gridPane.add(profileTxt, 1, 0);
		gridPane.add(clientMenu, 0, 1);
		gridPane.add(logout, 1, 1);

		BorderPane.setMargin(gridPane, new Insets(20,20,20,20));

		root.setCenter(gridPane);
		
	}


	public void getProfile() {

		try {
			Socket connection = SceneManager.getSocket();
			BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
			outgoing.println("Send Profile");
			outgoing.flush();
			System.out.println("WAITING...");
			try {
				String serverRes = incoming.readLine();
				profileTxt.setText(serverRes);

			} catch (IOException i) {
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

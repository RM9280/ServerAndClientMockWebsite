//Rebecca Mantione: creates the scene for customer

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CustomerScene extends SceneBasic {

	public CustomerScene() {
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

		// Button for viewing the user profile
		Button viewOrders = new Button("View Orders");
		viewOrders.setAlignment(Pos.CENTER);
		viewOrders.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.viewOrders));

		// Button for viewing the user profile
		Button placeOrders = new Button("Place Orders");
		placeOrders.setAlignment(Pos.CENTER);
		placeOrders.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.placeOrder));

		Button profile = new Button("Show Profile");
		profile.setAlignment(Pos.CENTER);
		profile.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.profile));

		Button changePass = new Button("Change Password");
		changePass.setAlignment(Pos.CENTER);
		changePass.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.changePassword));

		Button logout = new Button("Log out");
		logout.setAlignment(Pos.CENTER);
		logout.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.login));

		gridPane.add(profile, 0, 0);
		gridPane.add(changePass, 0, 1);
		gridPane.add(logout, 0, 2);
		gridPane.add(viewOrders, 0, 3);
		gridPane.add(placeOrders, 0, 4);

		BorderPane.setMargin(gridPane, new Insets(20, 20, 20, 20));
		root.setCenter(gridPane);
	}

}

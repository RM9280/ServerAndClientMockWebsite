import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ViewOrdersScene extends SceneBasic {

	GridPane gridPane = null;
	Button menu = null;
	int orderCount = 1;

	public void ViewOrderScene() {

		System.out.println("Got here");
		BorderPane root = new BorderPane();
		scene = new Scene(root, 500, 300);

		Label title = new Label("Your Orders");
		root.setTop(title);
		title.setFont(Font.font(40));
		root.setAlignment(title, Pos.TOP_CENTER);

		gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		Label stock = new Label("Stock");
		Label description = new Label("Description");
		Label quantity = new Label("Quantity");

		stock.setFont(Font.font(20));
		description.setFont(Font.font(20));

		gridPane.add(stock, 0, 0);
		gridPane.add(description, 1, 0);
		gridPane.add(quantity, 2, 0);
		root.setCenter(gridPane);

		menu = new Button("Return To Menu");
		menu.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.customer));

		gridPane.add(menu, 2, orderCount);

		BorderPane.setMargin(gridPane, new Insets(20, 20, 20, 20));

	}

	public void addOrdersLabel(int stock, String description, int quantity) {
		Label stocklbl = new Label(String.valueOf(stock));
		Label descriptionlbl = new Label(description);
		Label quantitylbl = new Label(String.valueOf(quantity));
		gridPane.add(stocklbl, 0, orderCount);
		gridPane.add(descriptionlbl, 1, orderCount);
		gridPane.add(quantitylbl, 2, orderCount);
		orderCount++;
	}

	public void getOrders() {

	}
}

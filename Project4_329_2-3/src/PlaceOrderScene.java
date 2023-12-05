//Rebecca Mantione: Created the PlaceOrderScene

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlaceOrderScene extends SceneBasic {
	GridPane gridPaneInput;
	Button submit;
	Button cancel;
	int accountCount = 5;
	Label profileTxt;
	VBox root;
	GridPane gridInventory = new GridPane();

	// Created the GUI for PlaceOrderScene
	public PlaceOrderScene() {
		super("Ordering");

		root = new VBox();
		scene = new Scene(root, 500, 300);

		// Set the title
		Label title = new Label("Ordering");
		root.getChildren().addAll(title);
		title.setFont(Font.font(40));
		BorderPane.setAlignment(title, Pos.TOP_CENTER);

		root.getChildren().addAll(gridInventory);

		// Set the column titles
		gridPaneInput = new GridPane();
		gridPaneInput.setVgap(20);
		gridPaneInput.setHgap(20);
		Label stock = new Label("Stock #");
		Label description = new Label("Description");

		stock.setFont(Font.font(20));
		description.setFont(Font.font(20));

		// created the labels and textfields
		Label stockLabel = new Label("Stock #");
		TextField stockTextField = new TextField();

		Label quantityLabel = new Label("Quantity");
		TextField quantityTextField = new TextField();

		// create the buttons and set their action
		submit = new Button("Submit");
		submit.setOnAction(e -> sendOrder(stockTextField, quantityTextField));

		cancel = new Button("Cancel");
		cancel.setOnAction(e -> SceneManager.setScene(SceneManager.SceneType.customer));

		// Put it on the GUI
		profileTxt = new Label();

		gridInventory.add(stock, 0, 0);
		gridInventory.add(description, 2, 0);

		gridPaneInput.add(stockLabel, 0, 1);
		gridPaneInput.add(stockTextField, 1, 1);

		gridPaneInput.add(quantityLabel, 0, 2);
		gridPaneInput.add(quantityTextField, 1, 2);

		gridPaneInput.add(submit, 0, 4);
		gridPaneInput.add(cancel, 0, 5);

		root.getChildren().addAll(gridPaneInput);

		BorderPane.setMargin(gridPaneInput, new Insets(20, 20, 20, 20));
	}

	// Reads the incoming stocknumber and description and puts it onto the GUI
	public void getInventory() {
		System.out.println("Starting get Inventory");
		Socket connection = SceneManager.getSocket();
		try {

			BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
			outgoing.println("Inventory");// indication that it wants to call send inventory
			outgoing.flush();
			System.out.println("WAITING...");
			try {

				int row = 1;
				String stockNumber = incoming.readLine();// reads incoming line
				System.out.println("starting while loop");
				while (!stockNumber.equals("Done")) {// makes sure it is not the end
					System.out.println(stockNumber);

					gridInventory.add(new Label(stockNumber), 0, row);
					String description = incoming.readLine();
					gridInventory.add(new Label(description), 2, row);
					row++;// which row to place it on
					stockNumber = incoming.readLine();// reads incoming line

				}

			} catch (IOException i) {
				System.out.println(i);
			}
		} catch (IOException i) {
			System.out.println(i);
		}

	}

	// reads the orders in and saves it to a binary file
	public void sendOrder(TextField stockTextField, TextField quantityTextField) {
		Socket connection = SceneManager.getSocket();
		try {

			// gets the connection
			PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
			outgoing.println("Order");// indicates that it is calling getOrder
			System.out.println("WAITING...");

			// reads in from the TextFields
			String stockTextFieldInput = stockTextField.getText();
			String quantityTextFieldInput = quantityTextField.getText();

			System.out.println(stockTextFieldInput);
			System.out.println(quantityTextFieldInput);

			// sends them to be saved to the binary file
			outgoing.println(stockTextFieldInput);
			outgoing.println(quantityTextFieldInput);
			outgoing.flush();

		} catch (IOException i) {
			System.out.println(i);
		}

	}
}

/**
 * Author: Yashdeep Deswal
 * Represents a scene for user login.
 */
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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

public class SettingsScene extends SceneBasic {
    private TextField hostText; // Field for entering the username.
    private TextField portText; // Field for entering the password.
    private ArrayList<Account> accounts; // List of user accounts.
    private Label statusLbl; // Label to display login status.

    /**
     * Initializes the LoginScene with a list of accounts.

     */
    public SettingsScene() {
        super("Setting Scene");
        this.accounts = accounts;

        BorderPane root = new BorderPane();
        scene = new Scene(root, 500, 300);

        Label title = new Label("Setting Scene");
        title.setFont(Font.font(40));
        statusLbl = new Label();
        root.setTop(title);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setBottom(statusLbl);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Label host = new Label("Host");
        Label port = new Label("Port");

        hostText = new TextField();
        portText = new TextField();

        gridPane.add(host, 0, 0);
        gridPane.add(hostText, 1, 0);
        gridPane.add(port, 0, 1);
        gridPane.add(portText, 1, 1);

        root.setCenter(gridPane);
        BorderPane.setMargin(gridPane, new Insets(20,20,20,20));

        Button applyBtn = new Button("Apply");
        applyBtn.setOnAction(e -> apply());
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> cancel());

        Button chatBtn = new Button("Chat");
        //@TODO Sony
        chatBtn.setOnAction(e -> cancel());

        gridPane.add(applyBtn, 0, 2);
        gridPane.add(cancelBtn, 1, 2);
    }

    /**
     * Performs the login operation.
     */
    public void apply() {
    	int port = Integer.valueOf(portText.getText());
        String host = hostText.getText();
    	try {
            Socket connection =  new Socket(host, port);
            SceneManager.setSocket(connection);
            SceneManager.setScene(SceneManager.SceneType.login);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void cancel() {
    	SceneManager.setScene(SceneManager.SceneType.login);
    	
    }
}

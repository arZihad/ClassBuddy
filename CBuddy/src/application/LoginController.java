package application;

import java.io.IOException;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	JFXTextField name;
	@FXML
	JFXPasswordField pass;
	String nam;
	DataBase db = new DataBase();
	
	public void login(ActionEvent ev) {
		nam = name.getText();
		String pas = pass.getText();
		
		int ck = db.homelogin(nam, pas);
		if(ck==1){
			try {
				Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/application/Home.fxml")));
				Stage home = (Stage) (((Node) ev.getSource()).getScene().getWindow());
				home.setScene(sc);
				home.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("0");
		}
	}
	
	public void newAccount(ActionEvent ev) {
		try {
			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/application/SignUp.fxml")));
			Stage home = (Stage) (((Node) ev.getSource()).getScene().getWindow());
			home.setScene(sc);
			home.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

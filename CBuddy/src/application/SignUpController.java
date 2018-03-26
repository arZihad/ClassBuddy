package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SignUpController {
	DataBase db=new DataBase();
	@FXML
	JFXTextField name;
	@FXML
	JFXTextField id;
	@FXML
	JFXTextField email;
	@FXML
	JFXTextField phone;
	@FXML
	JFXButton btn;
	@FXML
	JFXPasswordField pass;
	@FXML
	JFXPasswordField cpass;
	@FXML
	JFXDatePicker date;
	@FXML
	JFXTextField username;
	
	String nam;
	String iD;
	String	phn;
	String emaiL;
	String path;
	String password;
	String uname;
	int con_pass = 0;
	String dt;
	public void getData() {
		nam=name.getText();;
		iD=id.getText();
		phn=phone.getText();
		emaiL=email.getText();
		dt=date.getEditor().getText();
		uname = username.getText();
	}
	
	public void passmatch() {
		
		password = pass.getText();
		String cpassword= cpass.getText();
		if(password.equals(cpassword)) {
			con_pass = 1;
		}
	}
	
	
	public void setata(ActionEvent event) {
		getData();
		passmatch();
		if(con_pass==1)
		{
			db.setData(nam,uname, iD,password ,phn,dt, emaiL);
			try {
				Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/application/Login.fxml")));
				Stage home = (Stage) (((Node) event.getSource()).getScene().getWindow());
				home.setScene(sc);
				home.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

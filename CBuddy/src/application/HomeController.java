package application;



import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {

	@FXML
	TextArea text;
	@FXML
	Label name;
	@FXML
	TextArea messege;
	@FXML
	Label id;
	@FXML
	Label phn;
	@FXML
	Button btn;
	
	

	Details dt = new Details();
	DataBase db = new DataBase();
	ResultSet rs ;
	String msg;
	String Name=Details.getuName() ;
	int checker = 0;
	

	public void detailSet() {
		name.setText(Details.fName);
		id.setText(Details.getuId());
		phn.setText(Details.getuPhn());
	}
	
	public void sendmsg() throws SQLException {
		db.sendms(Name , messege.getText());
		messege.setText("");
		
		refresh();
	}
	
	public void refresh() throws SQLException {
		rs = db.getmsg();
		msg = "";
		while(rs.next()) {
			msg = msg + rs.getString("name")+"\n-----------------------------------\n"+rs.getString("msg")+"\n\n";
			text.setText(msg);
		}
	}
	
	public void initialize() throws SQLException {
	    detailSet();
	    refresh();
	}
	

    public void pro(ActionEvent ev) throws Exception {
    	Stage stage = new Stage();
        // change LoginScene.fxml so it now has fx:controller="LoginController"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Profile.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

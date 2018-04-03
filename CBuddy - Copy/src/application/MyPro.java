package application;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class MyPro {
  @FXML
  JFXTextField name;
  @FXML
  JFXTextField uname;
  @FXML
  JFXTextField uid;
  @FXML
  JFXTextField email;
  @FXML
  JFXTextField phn;
  @FXML
  JFXTextField dob;
  @FXML
  JFXTextArea addr;
  @FXML
  JFXTextField bgrp;
  @FXML
  JFXPasswordField pass;
  DataBase db = new DataBase();
  Alert alt = new Alert(Alert.AlertType.ERROR);
  public void initialize()  {
      name.setText(Details.fName);
      uname.setText(Details.uName);
      uid.setText(Details.uId);
      email.setText(Details.email);
      phn.setText(Details.uPhn);
      dob.setText(Details.dob);
      addr.setText(Details.addr);
      bgrp.setText(Details.bg);
      pass.setText(Details.pass);
  }
//pass,phn,email,addr

    public void update(){
        try {
            db.updateprofile(pass.getText(),email.getText(),addr.getText(),phn.getText(),uid.getText());
        } catch (SQLException e) {
            alt.setTitle("Error");
            alt.setHeaderText("Something went wrong !");
            alt.setContentText("Please check every changes that you made.");
            alt.show();
        }
    }

    public void close(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}

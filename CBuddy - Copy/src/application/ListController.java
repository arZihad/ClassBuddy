package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    private TextField uname;

    @FXML
    private TextField bg;

    @FXML
    private TextField dob;

    @FXML
    private TextField phn;

    @FXML
    private TextField name;

    @FXML
    private TextField id;

    @FXML
    private TextArea addr;

    @FXML
    private TextField email;
    @FXML
    private TextField sText ;

    @FXML
    private TableColumn<FDetails,String> tname;
    @FXML
    private TableColumn<FDetails,String> tid;
    @FXML
    private TableColumn<FDetails,String> tbg;
    @FXML
    private TableView<FDetails> table;
    DataBase db = new DataBase();
    ResultSet r , rr;
    Alert alt2 = new Alert(Alert.AlertType.ERROR);
    public ObservableList<FDetails> list = FXCollections.observableArrayList(); ;


    public void flistprimary(){
        try {
            r = db.frndlst();
            while(r.next()){
                list.add(new FDetails(r.getString("Name"),r.getString("Username"),r.getString("ID"),
                        r.getString("Password"),r.getString("Phone"),r.getString("Email"),
                        r.getString("BG"),r.getString("Address"),r.getString("Dob")));
            }

        } catch (SQLException e) {
            System.out.println("primary");
        }
    }

    public void settable(){
        tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbg.setCellValueFactory(new PropertyValueFactory<>("bg"));
        table.setItems(list);
    }



    public void initialize(URL location, ResourceBundle resources){
        flistprimary();
        settable();

    }

    @FXML
    public void findfrnd(javafx.scene.input.MouseEvent ev){
        try {
            String x= table.getSelectionModel().getSelectedItem().getId();
            rr = db.Searchfrnd(x);
            while(rr.next()) {
                name.setText(rr.getString("Name"));
                uname.setText(rr.getString("Username"));
                phn.setText(rr.getString("Phone"));
                email.setText(rr.getString("Email"));
                addr.setText(rr.getString("Address"));
                bg.setText(rr.getString("BG"));
                dob.setText(rr.getString("Dob"));
                id.setText(rr.getString("ID"));
            }
        } catch (SQLException e) {
            System.out.println("findfrnd");
        }
    }

    public void searchfrnd(){
        try{
            String x= sText.getText();
                rr = db.Searchfrnd(x);
                int c=0;
                while(rr.next()){
                    c++;
                    name.setText(rr.getString("Name"));
                    uname.setText(rr.getString("Username"));
                    phn.setText(rr.getString("Phone"));
                    email.setText(rr.getString("Email"));
                    addr.setText(rr.getString("Address"));
                    bg.setText(rr.getString("BG"));
                    dob.setText(rr.getString("Dob"));
                    id.setText(rr.getString("ID"));
                }
                if(c==0){
                    alt2.setTitle("Error");
                    alt2.setHeaderText("This id is not in Database.");
                    alt2.setContentText("Please check the id and try again");
                    alt2.show();
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase extends Details{
	private Connection con;
	private Statement st;
	private ResultSet rs;

	
	
	public DataBase() {
		try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classbuddy","root","");
				st = con.createStatement();
			
			}catch(Exception e) {
				e.printStackTrace();
		}
	}
	
	
	
	
	public ResultSet getmsg() {						// Get messeges from database 
		try {
			rs = st.executeQuery("Select * from massage");
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int homelogin(String name,String pass) {				// Check username and password from database

		try {
			rs = st.executeQuery("SELECT * FROM buddies");
			while(rs.next()) {
				if(name.equals(rs.getString("Username")) && pass.equals(rs.getString("password"))) {
					loginsearch(rs);
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;
	}
	
	public void setData(String name,String uname,String id,String pass,String phone,String dob,String email) {			//store new account data into database
		try {
			st.executeUpdate("INSERT INTO buddies Values('"+name+"','"+uname+"','"+id+"','"+pass+"','"+phone+"','"+dob+"','"+email+"')");
			System.out.println("Value added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendms(String name ,String text) throws SQLException {			// Store msg in databse
		st.executeUpdate("INSERT INTO `massage` (`name`, `msg`) VALUES ('"+name+"', '"+text+"');");
	}
	
	
	public void loginsearch(ResultSet r) {		//Save user data while login
		try {
			super.setfName(r.getString("Name"));
			super.setuId(r.getString("ID"));
			super.setuPhn(r.getString("Phone"));
			super.setuName(r.getString("Username"));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public ResultSet proSearch(String name,String pass) throws SQLException { //User profile data searching
		rs = st.executeQuery("SELECT * FROM buddies");
		while(rs.next()) {
			if(name.equals(rs.getString("Name"))) {
				return rs;
			}
		}
		return rs ;
	}
}
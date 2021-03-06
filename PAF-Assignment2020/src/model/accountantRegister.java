package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class accountantRegister {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf-assignment", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


	//insert-----------------------------------------------------------------------------------------------------------------------------
	
	public String insertItem(String acode, String name, String nic, String phone, String email, String address,
			String pass)
    {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{    
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into accountant(`AID`,`Acode`,`AName`,`ANIC`,`PhoneNo`,`Email`,`Address`,`Password`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, acode);
						preparedStmt.setString(3, name);
						preparedStmt.setString(4, nic);
						preparedStmt.setString(5, phone);
						preparedStmt.setString(6, email);
						preparedStmt.setString(7, address);
						preparedStmt.setString(8, pass);
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			}
				catch (Exception e)
			{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
					System.err.println(e.getMessage());
			}
			return output;
    		}
	

	//view------------------------------------------------------------------------------------------------------------------------
		public String readItems(){
			
			String output = "";
				try{
				Connection con = connect();
				
				if (con == null){
					return "Error while connecting to the database for reading."; 
				}
				// Prepare the html table to be displayed
				output = "<table border=\"2\"><tr><th>Accountant code</th><th>Name</th><th>NIC NO</th><th>Phone No</th><th>Emaill</th>"
						+ "<th>Address</th><th>Password</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from accountant";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()){
					
					String AID = Integer.toString(rs.getInt("AID"));
					String Acode = rs.getString("Acode");
					String AName = rs.getString("AName");
					String ANIC = rs.getString("ANIC");
					String PhoneNo = rs.getString("PhoneNo");
					String Email = rs.getString("Email");
					String Address = rs.getString("Address");
					String Password = rs.getString("Password");

					
					// Add into the html table
					output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate' type='hidden' value='" + AID+ "'>" + Acode + "</td>";
					output += "<td>" + AName + "</td>";
					output += "<td>" + ANIC + "</td>";
					output += "<td>" + PhoneNo + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + Address + "</td>";
					output += "<td>" + Password + "</td>";
					 // buttons
					
					output += "<td><input name='btnUpdate'type='button' "
							+ "value='Update'class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove'type='button' "
							+ "value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ AID + "'>" + "</td></tr>";
				}
				
				con.close();
				// Complete the html table
				output += "</table>";
				}
				catch (Exception e){
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}
				
		return output;
		
		}
	
//Update--------------------------
	
	public String updateItem(String ID, String acode, String name, String nic, String phone, String email,
			String address, String pass) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query =  "UPDATE accountant SET Acode=?,AName=?,ANIC=?,PhoneNo=?,Email=?,Address=?,Password=?WHERE AID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, acode);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, nic);
			preparedStmt.setInt(4, Integer.parseInt(phone));
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, pass);
			preparedStmt.setInt(8, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";;
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
//Delete-------------------------------	
	public String deleteItem(String AID) {
		System.out.println(AID);
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from accountant where AID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(AID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

}

package model;

import java.sql.*;

public class Customer 
{
	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_db", "root", "");
			
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	public String insertCustomer(String name, String nic, String phone, String email, String addr, String pass)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into customers (`CustomerID`,`CustomerName`,`nic`,`phoneNo`,`email`,`cusAddress`,`cusPassword`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, addr);
			preparedStmt.setString(7, pass);
			
			// execute the statement3
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			
		}
		catch (Exception e)
		{
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readCustomer()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th><th>Customer NIC</th>" +
					"<th>Phone Number</th>" +
					"<th>Email</th>" +
					"<th>Address</th>" +
					"<th>Password</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from customers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String customerID = Integer.toString(rs.getInt("customerID"));
				String customerName = rs.getString("customerName");
				String nic = rs.getString("nic");
				String phoneNo = rs.getString("phoneNo");
				String email = rs.getString("email");
				String cusAddress = rs.getString("cusAddress");
				String cusPassword = rs.getString("cusPassword");
				
				// Add into the html table
				output += "<tr><td>" + customerName + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + cusAddress + "</td>";
				output += "<td>" + cusPassword + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
										+ "<input name='customerID' type='hidden' value='" + customerID
										+ "'>" + "</form></td></tr>";
										
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateCustomer(String ID, String name, String nic, String phone, String email, String addr, String pass)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE customers SET customerName=?,nic=?,phoneNo=?,email=?,cusAddress=?,cusPassword=? WHERE customerID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, addr);
			preparedStmt.setString(6, pass);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			
		}
		catch (Exception e)
		{
			
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
			
		}
		return output;
	}
	
	public String deleteCustomer(String customerID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from customers where customerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
		}
		catch (Exception e)
		{
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}

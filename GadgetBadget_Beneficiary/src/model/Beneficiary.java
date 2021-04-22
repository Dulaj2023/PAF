package model;

import java.sql.*;

public class Beneficiary 
{
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/beneficiary", "root", "");
		}
		
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	public String insertInventor(String id, String age, String address, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into beneficiares(`inventorCode`,`inventorID`,`inventorAge`,`inventorAddress`,`inventorPassword`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, id);
			preparedStmt.setString(3, age);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, password);
			
			// execute the statement3
			preparedStmt.execute();
			con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the beneficiary.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readInventors()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Inventor ID</th><th>Inventor Age</th>" +
					"<th>Inventor Address</th>" +
					"<th>Inventor Password</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from beneficiares";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String inventorCode = Integer.toString(rs.getInt("inventorCode"));
				String inventorID = rs.getString("inventorID");
				String inventorAge = rs.getString("inventorAge");
				String inventorAddress = rs.getString("inventorAddress");
				String inventorPassword = rs.getString("inventorPassword");
				
				// Add into the html table
				output += "<tr><td>" + inventorID + "</td>";
				output += "<td>" + inventorAge + "</td>";
				output += "<td>" + inventorAddress + "</td>";
				output += "<td>" + inventorPassword + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='beneficiary.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='inventorCode' type='hidden' value='" + inventorCode + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}
		catch (Exception e)
		{
			output = "Error while reading the beneficiaries.";
			System.err.println(e.getMessage());
		}
			return output;
	}
	
	public String updateInventor(String code, String id, String age, String address, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE beneficiares SET inventorID=?,inventorAge=?,inventorAddress=?,inventorPassword=? WHERE inventorCode=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, age);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, password);
			preparedStmt.setInt(5, Integer.parseInt(code));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the beneficiaries.";
			System.err.println(e.getMessage());
			
		}
		return output;
	}
	
	public String deleteInventor(String inventorCode)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from beneficiares where inventorCode=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inventorCode));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the beneficiaries.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	
	
	
	

}

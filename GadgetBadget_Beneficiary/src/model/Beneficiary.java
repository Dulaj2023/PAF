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
	
	public String insertBeneficiary(String code, String name, String type, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into beneficiares(`beneficiaryID`,`beneficairyCode`,`beneficiaryName`,`productType`,`beneficiaryDesc`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, type);
			preparedStmt.setString(5, desc);
			
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
	
	public String readBeneficiary()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Beneficiary Code</th><th>Beneficiary Name</th>" +
					"<th>Product Type</th>" +
					"<th>Beneficiary Description</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from beneficiares";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String beneficiaryID = Integer.toString(rs.getInt("beneficiaryID"));
				String beneficairyCode = rs.getString("beneficairyCode");
				String beneficiaryName = rs.getString("beneficiaryName");
				String productType = rs.getString("productType");
				String beneficiaryDesc = rs.getString("beneficiaryDesc");
				
				// Add into the html table
				output += "<tr><td>" + beneficairyCode + "</td>";
				output += "<td>" + beneficiaryName + "</td>";
				output += "<td>" + productType + "</td>";
				output += "<td>" + beneficiaryDesc + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='beneficiaryID' type='hidden' value='" + beneficiaryID+ "'>" + "</form></td></tr>";
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
	
	public String updateBeneficiary(String ID, String code, String name, String type, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE beneficiares SET beneficairyCode=?,beneficiaryName=?,productType=?,beneficiaryDesc=? WHERE beneficiaryID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
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
	
	public String deleteBeneficiary(String beneficiaryID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from beneficiares where beneficiaryID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(beneficiaryID));
			
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

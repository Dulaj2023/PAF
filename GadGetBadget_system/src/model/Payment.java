package model;

import java.sql.*;

public class Payment 
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
	
	public String insertPayment(String date, String amount, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into payments (`payment_id`,`date`,`payment_amount`,`payment_description`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, date);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, desc);
			
			// execute the statement3
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			
		}
		catch (Exception e)
		{
			
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String readPayment()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Paymont Date</th>"+ 
					"<th>Paymont Amount</th>" +
					"<th>Payment Description</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String payment_id = Integer.toString(rs.getInt("payment_id"));
				String date = rs.getString("date");
				String payment_amount = Double.toString(rs.getDouble("payment_amount"));
				String payment_description = rs.getString("payment_description");
				
				// Add into the html table
				output += "<tr><td>" + date + "</td>";
				output += "<td>" + payment_amount + "</td>";
				output += "<td>" + payment_description + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='payment_id' type='hidden' value='" + payment_id + "'>" + "</form></td></tr>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String updatePayment(String id, String date, String amount, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE payments SET date=?,payment_amount=?,payment_description=? WHERE payment_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
							
			// binding values
			preparedStmt.setString(1, date);
			preparedStmt.setDouble(2, Double.parseDouble(amount));
			preparedStmt.setString(3, desc);
			preparedStmt.setInt(4, Integer.parseInt(id));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String deletePayment(String payment_id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from payments where payment_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payment_id));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
	}
	return output;
	}

}

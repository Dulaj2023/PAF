package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funding {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gadgetbadget_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertFund(String fun_name, String fun_date, String fun_price, String fun_cate ,String fun_des) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into fundingtest1(`fundId`, `funderName`, `fundDate`, `fundPrice`, `fundCate`,`fundDesc`)" + " values ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fun_name);
			preparedStmt.setDate(3, Date.valueOf(fun_date));
			preparedStmt.setDouble(4, Double.parseDouble(fun_price));
			preparedStmt.setString(5, fun_cate);
			preparedStmt.setString(6, fun_des);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readFund() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Funder Name</th><th>Date</th><th>Price</th><th>Category</th><th>Description</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from fundingtest1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundId = Integer.toString(rs.getInt("fundId"));
				String funderName = rs.getString("funderName");
				String fundDate = rs.getString("fundDate");
				String fundPrice = Double.toString(rs.getDouble("fundPrice"));
				String fundCate = rs.getString("fundCate");
				String fundDesc = rs.getString("fundDesc");

				
				output += "<td>" + funderName + "</td>";
				output += "<td>" + fundDate + "</td>";
				output += "<td>" + fundPrice + "</td>";
				output += "<td>" + fundCate + "</td>";
				output += "<td>" + fundDesc + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='funding.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='fundId' type='hidden' value='" + fundId 
						 + "'>" + "</form></td></tr>"; 

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateFund(String fun_Id, String fun_name, String fun_date, String fun_price, String fun_cate ,String fun_des) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE fundingtest1 SET funderName=?,fundDate=?,fundPrice=?,fundCate=?,fundDesc=? WHERE fundId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, fun_name);
			preparedStmt.setDate(2, Date.valueOf(fun_date));
			preparedStmt.setDouble(3, Double.parseDouble(fun_price));
			preparedStmt.setString(4, fun_cate);
			preparedStmt.setString(5, fun_des);
			preparedStmt.setInt(6, Integer.parseInt(fun_Id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the fund.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deleteFund(String fundId) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from fundingtest1 where fundId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the funds.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}

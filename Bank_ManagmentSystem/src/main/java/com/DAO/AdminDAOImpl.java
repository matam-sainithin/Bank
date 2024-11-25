package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.exception.AdminException;

public class AdminDAOImpl implements  AdminDAO{
	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=12345";
	private static final String query ="select * from admin where Admin_Emailid=? and Admin_Password=?";
	private static final String query1 ="select * from bank_user_details";
	private static final String checkemailid ="select * from admin where Admin_Emailid=?";
	private static final String checkpassword ="select * from admin where Admin_Password=?";
	int count;
	
	@Override
	public boolean getAdminDetailsBYUsingEmailAndPassword(String emailid, String password) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, emailid);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}
	}
	@Override
	public void getAllUserDetails() {
		try {
			Connection connection = DriverManager.getConnection(url);
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query1);
			while (rs.next()) {
				System.out.print(rs.getInt(1)+" ");
				System.out.print(rs.getString(2)+" ");
				System.out.print(rs.getString(3)+" ");
				System.out.print(rs.getLong(4)+" ");
				System.out.print(rs.getLong(5)+" ");
				System.out.print(rs.getString(6)+" ");
				System.out.print(rs.getString(7)+" ");
				System.out.print(rs.getString(8)+" ");
				System.out.print(rs.getDouble(9)+" ");
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void removeColumn(long aadharNumber) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM bank_user_details WHERE Aadhar_Number=?");
			ps.setLong(1, aadharNumber);
			int resultSet =ps.executeUpdate();
			System.out.println(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
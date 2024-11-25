package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.BankUserDetails;
import com.service.BankServiceImpl;

public class BankUserDAOimpl implements BankUserDAO{
	
	private static final String insert=
"insert into bank_user_details (Name, Emailid, Aadhar_Number, Mobile_Number, PAN_Number, Address, Gender, Amount, Status) values(?,?,?,?,?,?,?,?,?)";
	
	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=12345";
	 
	String updatepin ="update bank_user_details set pin = ?,Account_Number=?,Status='Accepted' where Aadhar_Number=?";
	      
	@Override
	public void insertBankUserDetails(BankUserDetails bud) {
		
		try {
			Connection c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(insert);
			ps.setString(1, bud.getName());
			ps.setString(2, bud.getEmailid());
			ps.setLong(3, bud.getAadharnumber());
			ps.setLong(4, bud.getMobilenumber());
			ps.setString(5, bud.getPannumber());
			ps.setString(6, bud.getAddress());
			ps.setString(7, bud.getGender());
			ps.setDouble(8, bud.getAmount());
			ps.setString(9, "Pending");
			
			int result=ps.executeUpdate();
			System.out.println(result);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public List<BankUserDetails> getAllBankUserDetails() {
			Connection connection;
			try {
				connection = DriverManager.getConnection(url);
				PreparedStatement preparedStatement = connection.prepareStatement("select * from bank_user_details");
				ResultSet resultSet = preparedStatement.executeQuery();
				List<BankUserDetails> list = new ArrayList<BankUserDetails>();
				if (resultSet.isBeforeFirst()) {
					while (resultSet.next()) {
						BankUserDetails bankUserDetiles = new BankUserDetails();
						bankUserDetiles.setAadharnumber(resultSet.getLong("Aadhar_Number"));
						bankUserDetiles.setMobilenumber(resultSet.getLong("Mobile_Number"));
						bankUserDetiles.setPannumber(resultSet.getString("PAN_Number"));
						bankUserDetiles.setStatus(resultSet.getString("Status"));
						bankUserDetiles.setName(resultSet.getString("Name"));
						list.add(bankUserDetiles);
					}
					return list;

				} else {
					return null;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	@Override
	public int setPinAccountNumber(int pin,int accountnumber,long aadharnumber) {
		try {
			Connection c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(updatepin);
			ps.setInt(1, pin);
			ps.setInt(2, accountnumber);
			ps.setLong(3, aadharnumber);
			return ps.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}

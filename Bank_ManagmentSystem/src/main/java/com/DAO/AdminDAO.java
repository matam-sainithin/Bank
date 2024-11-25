package com.DAO;

public interface AdminDAO {
	boolean getAdminDetailsBYUsingEmailAndPassword(String emailid,String password);
	void getAllUserDetails();
//	void getAccReqDetails();
//	boolean getEmailid(String emailid);
//	boolean getPassword(String password);
	
	void removeColumn(long aadharNumber);
}

package com.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImpl;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOimpl;
import com.exception.AdminException;
import com.model.BankUserDetails;

public class AdminServiceImpl implements AdminService{
	
	Scanner sc = new Scanner(System.in);
	AdminDAO adminDAO = new AdminDAOImpl();
	BankUserDAO bankUserDAO = new BankUserDAOimpl();
	
	@Override
	public void adminLogin() {
//		boolean start = true;
//		while(start) {
//			try {
//				boolean s = true;
//				System.out.println("Enter your emailid");
//				while (s) {
//					try {
//						emailid=sc.next();
//						if (adminDAO.getEmailid(emailid)) {
//							s= false;
//						} else {
//							throw new AdminException("Enter valid Emailid");
//						}
//					}
//					catch(AdminException ae) {
//						System.out.println(ae.getMsg());
//					}
//				}
//				boolean s1 = true;
//				System.out.println("Enter your password");
//				while (s1) {
//					try {
//						password=sc.next();
//						if (adminDAO.getPassword(password)) {
//							s1= false;
//						} else {
//							throw new AdminException("Enter valid password");
//						}
//					}
//					catch(AdminException ae) {
//						System.out.println(ae.getMsg());
//					}
//				}
		System.out.println("Enter your emailid");
		String emailid=sc.next();
		System.out.println("Enter your password");
		String password=sc.next();
		try {
			if (adminDAO.getAdminDetailsBYUsingEmailAndPassword(emailid, password)) {
				
				System.out.println("Enter \n 1. To Get All User Details \n 2.To Get All Account Request Details");
				switch(sc.nextInt()) {
				case 1:
//						adminDAO.getAllUserDetails();
//						start=false;
						System.out.println("Get All User Details");
						break;
				case 2:
//						adminDAO.getAccReqDetails();
//						start=false;
//						System.out.println("Get All Account Request Details");
						allAccountRequestDetails();
						break;
				default:
					break;
				}
			} 
			else {
				throw new AdminException("Invalid Emailid And Password");
			}
		}
		catch(AdminException a) {
			System.out.println(a.getMsg());
		}
				
	
	}
	
	
	int count=0;
	public void allAccountRequestDetails() {
//		list.forEach((user)->{
//			int sno=sc.nextInt();
//			System.out.println(list.get(sno-1));	
//		});
		boolean start = true;
		while(start) {
			
			List<BankUserDetails> allBankUserDetails = bankUserDAO.getAllBankUserDetails();
			
			List<BankUserDetails> list = allBankUserDetails.stream()
			.filter((user -> user.getStatus().equalsIgnoreCase("Pending")))
			.collect(Collectors.toList());
			
			list.forEach((user)->{
				System.out.println("S.no :"+(count+1));
				System.out.println("User Name :"+user.getName());
				System.out.println("User Mobile Number :"+user.getMobilenumber());
				System.out.println("User Aadhar Number :"+user.getAadharnumber());
				System.out.println("User Status :"+user.getStatus());
				count++;
			});
			if(!list.isEmpty()) {
			System.out.println("Enter S.No to Select");
			BankUserDetails bankUserDetails= list.get(sc.nextInt()-1);
			System.out.println(bankUserDetails);
			
			System.out.println("Enter \n 1.To Accept \n 2.To Reject");
			switch (sc.nextInt()) {
			case 1:
					System.out.println("To Accept");
					acceptRequest(bankUserDetails.getAadharnumber());
				break;
			case 2:
				System.out.println("To Reject");
				adminDAO.removeColumn(bankUserDetails.getAadharnumber());
			break;
	
			default:
				break;
			}
			System.out.println("Do You Want To Continue \n YES \n NO");
			String s = sc.next();
			if (s.equalsIgnoreCase("no")) {
				start=false;
			}
			else {
				count=0;
			}
			}
			else {
				System.out.println("No Details found");
				break;
			}
				
		}
		
		
	}
	@Override
	public void acceptRequest(long Aadhar_Number) {
		Random random = new Random();
		int pin =  random.nextInt(10000);
		if(pin<1000)
		{
			pin +=1000;
		}
		int accountNumber = random.nextInt(1000000);
		if (accountNumber<1000000) {
			accountNumber += 1000000;
		}
		int count=bankUserDAO.setPinAccountNumber(pin, accountNumber, Aadhar_Number);
		if (count != 0) {
			System.out.println("User pin :"+pin);
			System.out.println("User Account Number :"+accountNumber);
		}
		
	}
	
}
//admin@gmail.com

package com.service;

import java.util.List;
import java.util.Scanner;

import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOimpl;
import com.exception.BankUserException;
import com.model.BankUserDetails;

public class BankServiceImpl implements BankService {
	Scanner sc = new Scanner(System.in);
//	BankUserDAO bankUserDAO = new BankUserDAOimpl();

	@Override
	public void forSleep(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			System.out.print(s.charAt(i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();

	}

	@Override
	public void userRegistration() {
//		BankUserDAO b = new BankUserDAOimpl();
		BankUserDAO b = new BankUserDAOimpl();
		List<BankUserDetails> allBankUserDetails = b.getAllBankUserDetails();
		BankUserDetails bankUserDetails = new BankUserDetails();

		System.out.println("Enter Your Name");
		String name = sc.next();
		bankUserDetails.setName(name);
		System.out.println("Enter your Emailid");
		String emailid = sc.next();
		bankUserDetails.setEmailid(emailid);
		
		boolean start = true;
		while (start) {

			try {
				System.out.println("Enter your Aadhar Number");
				long aadharnumber = sc.nextLong();
				long tempaadharnumber = aadharnumber;
				int count = 0;
				while (aadharnumber > 0) {
					count++;
					aadharnumber /= 10;
				}
				if (count == 12) {
					long aadharcount = allBankUserDetails.stream()
							.filter((bankuserDetails -> bankuserDetails.getAadharnumber() == tempaadharnumber)).count();
					if (aadharcount == 0) {
						bankUserDetails.setAadharnumber(tempaadharnumber);
						break;
					} else {
						throw new BankUserException("Aadhar number Already exist----");
					}
				} else {
					throw new BankUserException("Invalid Aadhar number");

				}
			} catch (BankUserException b1) {
				System.out.println(b1.getMsg());
			}
		}

//		System.out.println("Enter your Mobile Number");
//		long mobilenumber = sc.nextLong();
//		bankUserDetails.setMobilenumber(mobilenumber);
		boolean mstatus = true;
		while (mstatus) {
			System.out.println("Enter Your Mobile Number");
			long mobilenumber = sc.nextLong();
			try {
				if (mobilenumber >= 6000000000l && mobilenumber <= 9999999999l) {
					long mobilecount = allBankUserDetails.stream().filter((user -> user.getMobilenumber() == mobilenumber)).count();
					if (mobilecount == 0) {
						mstatus = false;
						bankUserDetails.setMobilenumber(mobilenumber);
					} else {
						throw new BankUserException("Already Mobile Number Exist");
					}
				} else {
					throw new BankUserException("Invalid Mobile Number");
				}
			}
			catch(BankUserException e)
			{
				System.out.println(e.getMsg());
			}
		}
		System.out.println("Enter your PAN Number");
		String pannumber = sc.next();
		bankUserDetails.setPannumber(pannumber);
		System.out.println("Enter your Address");
		String address = sc.next();
		bankUserDetails.setAddress(address);
		System.out.println("Enter your Gender");
		String gender = sc.next();
		bankUserDetails.setGender(gender);
		System.out.println("Enter your Amount");
		double amount = sc.nextDouble();
		bankUserDetails.setAmount(amount);

		BankUserDAO b2 = new BankUserDAOimpl();
		b2.insertBankUserDetails(bankUserDetails);
	}

}

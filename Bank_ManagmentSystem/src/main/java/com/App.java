package com;

import java.util.Scanner;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImpl;
import com.DAO.BankUserDAOimpl;
import com.service.AdminService;
import com.service.AdminServiceImpl;
import com.service.BankService;
import com.service.BankServiceImpl;

/**
 * Hello world!
 *
 */

public class App {
	static boolean start = true;
	
	public static void help() throws InterruptedException {
		BankServiceImpl b =new BankServiceImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Do You Want To Continue \n Yes \n No");
		String s = sc.next();
		if (s.equalsIgnoreCase("no")) {
			start=false;
			b.forSleep("****----- Thank You Visit Again -----****");
		}
		else if (s.equalsIgnoreCase("yes")) {
			start=true;
		}
		else {
			System.out.println("Enter valid option");
			s = sc.next();
			if (s.equalsIgnoreCase("no")) {
				start=false;
				b.forSleep("****----- Thank You Visit Again -----****");
			}
			else if (s.equalsIgnoreCase("yes")) {
				start=true;
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		BankService b = new BankServiceImpl();
		AdminService as =new AdminServiceImpl();
		
		b.forSleep("*****-----*****Welcome to Teca_63 Bank*****-----***** \n");
		
		
		while (start) {
	
			System.out.println("Enter \n 1 For User Login \n 2 For Admin Login \n 3 For User Registration");
			

			
			switch (sc.nextInt()) {
			case 1:
				b.forSleep("***----User Login----***");
				help();
				break;
			case 2:
				b.forSleep("***----Admin Login----***");
				as.adminLogin();
				help();
				break;
			case 3:
				b.forSleep("***----User Registration----***");
				b.userRegistration();
				help();
				break;
			default:
				b.forSleep("***----Invalid Option----***");
				break;
			}
			
		}
	}
}
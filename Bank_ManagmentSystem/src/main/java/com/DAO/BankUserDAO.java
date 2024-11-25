package com.DAO;

import java.util.List;

import com.model.BankUserDetails;

public interface BankUserDAO {

	void insertBankUserDetails(BankUserDetails bud);
	List<BankUserDetails> getAllBankUserDetails();
	int setPinAccountNumber(int pin,int accountnumber,long aadharnumber);
}

package com.example.demo.service;

import java.util.List;


//import javax.transaction.Transaction;

import com.example.demo.entities.Account;

public interface AccountService {
	public List<Account> getAllAccounts();
	public Account getAccountById(int accno);
	
	public  String FundTransfer(int acc1, int acc2,float amt);
	
	public Account getAccountByAccType(String acctype);
	
	
}

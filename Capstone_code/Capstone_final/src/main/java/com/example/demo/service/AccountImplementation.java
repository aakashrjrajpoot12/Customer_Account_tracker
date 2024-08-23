package com.example.demo.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Account;

import com.example.demo.repository.AccountRepository;

@Service                                                                                  //allows developers to add business functionalities.
public class AccountImplementation implements AccountService {
	
	@Autowired
	AccountRepository acc_repo;//injected  AccountRepository by using  @Autowired ,whenever i had to use method of class AccountRepository i can use it
	
	public List<Account> getAllAccounts() {
		List<Account> account = new ArrayList<Account>(); 
		
		acc_repo.findAll().forEach(account1 ->account.add(account1));                                                  //findAll() is present inside JpaRepository
		return account;
		
		                         
	}
	
	
	
	
	
	public Account getAccountById(int accno) {
		// TODO Auto-generated method stub
		return acc_repo.findById(accno).get();
	}
	
	
	public Account getAccountByAccType(String acctype)   //userdefined
	{
		
		return acc_repo.getByacctype(acctype);
	}



	@Transactional
	@Override
public String FundTransfer(int acc1, int acc2, float amt) {
		// TODO Auto-generated method stub
		Optional<Account> fromaccount= acc_repo.findById(acc1);                // fromaccount=acc1
		Optional<Account> toaccount= acc_repo.findById(acc2);                  // tooaccount=acc2
		
		if(fromaccount.isPresent() && toaccount.isPresent()) {
		Account fromAcc= fromaccount.get();                    // fromAcc=fromaccount
		Account toAcc= toaccount.get();                        // toAcc=toaccount
		if(fromAcc==toAcc)
		{
		return "From and To could not be same!!";
		}
		
		
		if(fromAcc.getBal()>amt) {                                                         //then only amt would be debited from fromAcc to tpAcc  ,let fromAcc=1000 (accountNO)     ,amt=100
		fromAcc.setBal(fromAcc.getBal()-amt);                                               //from acc se balance debited
		toAcc.setBal(amt+toAcc.getBal());                                                 //credited to to account
		acc_repo.save(fromAcc);
		acc_repo.save(toAcc);
		return "Congo , Transcation successful";
		}
		
		
		else {
		return "Sad , Not enough balance";
		}



		}
		else {
		return "oh!!  ,Incorrect Details ";
		}

		
	}



	
	

	
	

}

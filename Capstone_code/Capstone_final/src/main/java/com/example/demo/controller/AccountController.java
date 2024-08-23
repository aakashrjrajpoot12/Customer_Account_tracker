package com.example.demo.controller;

import java.util.Arrays;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Account;
import com.example.demo.entities.FundTransfer;
import com.example.demo.exception.ResourceNotFoundException;

//import com.example.demo.model.Employee;
import com.example.demo.service.AccountService;

@RestController                                                                                                                   //is used for making restful web services
public class AccountController {
	@Autowired
	AccountService service; //injected  AccountService by using  @Autowired ,whenever i had to use method of class AccountService i can use it

	List<String> list = Arrays.asList("saving", "current");

	@GetMapping("/account")                                                                                                    //for mapping HTTP GET requests onto specific handler methods.       //Hypertext Transfer Protocol (HTTP) 
	public ResponseEntity<List<Account>> getAllAccounts() throws ResourceNotFoundException {  //ResponseEntity is used when we are going to send any response as account details with  HttpStatus.FOUND
		List<Account> acc = service.getAllAccounts();
	
		
		if (acc.isEmpty()) {
			throw new ResourceNotFoundException("No Accounts Found, Add Account");
		}
		return new ResponseEntity<List<Account>>(acc, HttpStatus.FOUND);////ResponseEntity is used when we are going to send any response as account details with  HttpStatus.FOUND
	}
	
	
	@GetMapping("/account/search/{acctype}")  //for jpa repo userdefined 

	public ResponseEntity<Account> getAccountByAccType(@PathVariable String acctype)throws ResourceNotFoundException   
	{  	Account acc = service.getAccountByAccType(acctype);   ////userdefined
	if (acc == null) {
		throw new ResourceNotFoundException("No data Found with this ID");
	}
	return new ResponseEntity<Account>(acc, HttpStatus.FOUND); 
	}

	
	
	@GetMapping("/account/find/{accno}")  //                                                                                       if i don't give"/account/{accno}" then it will give error ,as it will confused by line 35,that's why i had given find in between
	public ResponseEntity<Account> getAccount(@PathVariable int accno) 	throws ResourceNotFoundException {
	
		Account acc = service.getAccountById(accno);
		if (acc == null) {
			throw new ResourceNotFoundException("No data Found with this ID");
		}
		return new ResponseEntity<Account>(acc, HttpStatus.FOUND);
	}
	

	
	
	

	@PutMapping("/FundTransfer")                                                                                                     //for mapping HTTP PUT requests onto specific handler methods.
	@Transactional
	public String transferFund(@RequestBody FundTransfer transact) {
		String message = service.FundTransfer(transact.getAcc1(), transact.getAcc2(), transact.getAmt());
		return message;

	}
}

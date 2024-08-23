package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entities.Account;


@SpringBootTest                                                                                                        //It applies on a Test Class that runs Spring Boot based tests.
public class AccountRepositoryTest {
	@Autowired
	AccountRepository repository;

	@Test

	void createAccount() {
		System.out.println("Test 1: Entering Create Account");
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setAccno(1001);
		account1.setAcctype("Savings");
		account1.setBal(110000);
		account2.setAccno(1002);
		account2.setAcctype("Current");
		account2.setBal(900000);

		Account acc1 = repository.save(account1);
		Account acc2 = repository.save(account2);     
		assertEquals(1001, account1.getAccno());
	}
	
	@Test
        void getAccounts() { 
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setAccno(1001);
		account1.setAcctype("Savings");
		account1.setBal(10000);
		account2.setAccno(1002);
		account2.setAcctype("Current");
		account2.setBal(20000);

		repository.save(account1);
		repository.save(account2);
		List<Account> accounts = (List<Account>) repository.findAll();             

		System.out.println("Test 2: Accounts Details");

		for (Account ac : accounts) {
			System.out.println(ac.getAccno() + "\t" + ac.getAcctype() + "\t" + ac.getBal());
		}
		assertEquals(2, accounts.size());

	}



	
@Test


void FindAccounts() {
	System.out.println("Executing find Account");
	                                                                           //we are using data which is already saved in repository se line 54and55
	List<Account> accounts = (List<Account>) repository.findAll();       
	for (Account account : accounts) {                                                                                     //traversing Account
		System.out.println("Account details:" + account);
	}
	
	
}                                                                                      

	@Test

	void DeleteAccount() {
		System.out.println("Executing Delete Account");
		Account account = null;
		Optional<Account> findById = repository.findById(4);                                                   //optional stores collection object used to contain not-null objects            
		if (findById.isPresent()) {
			account = findById.get();
			repository.delete(account);

		}
 
 }}
 

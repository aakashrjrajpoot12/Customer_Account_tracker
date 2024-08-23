package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.entities.Account;

import com.example.demo.repository.AccountRepository;
@TestMethodOrder(OrderAnnotation.class)                                                                                 //@TestMethod" refers to any method annotated with @Test,If @TestMethodOrder is not explicitly declared on a test class, inherited from a parent class, or declared on a test interface implemented by a test class, test methods will be ordered using a default algorithm that is deterministic but intentionally nonobvious.
@ExtendWith(MockitoExtension.class)                                                                                 //@ExtendWith annotation is used to load a JUnit 5 extension. JUnit defines an extension API, which allows a third-party vendor like Mockito to hook into the lifecycle of running test classes and add additional functionality.

class AccountServiceTest {
	@InjectMocks                                                                                                         //creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
	AccountImplementation service;
	@Mock                                                                                                                     //we need CustomerRepository class in our test but we do not want to invoke the original class. so, we 'Mock' that class which works the same as the original but is actually just a mock.
	private AccountRepository repository;

	@Test
	void transferFundsTest() {
		Account cust1 = new Account(131, "Savings", 500);//instance
		cust1.setBal(500);
		Account cust2 = new Account(132, "Savings", 500);
		cust2.setBal(500);
		float amt = 100;
		                                                                                                                  //Optional.ofNullable(cust1) method is used to get an instance of the Optional class with a specified value,If the value is null in place of cust1 , then an empty Optional object is returned.
		when(repository.findById(cust1.getAccno())).thenReturn(Optional.ofNullable(cust1));               //when we are passing cust1.getAccno()then Return cust1
		when(repository.findById(cust2.getAccno())).thenReturn(Optional.ofNullable(cust2));
        service.FundTransfer(cust1.getAccno(), cust2.getAccno(), amt);
		cust1.setBal(cust1.getBal()-amt); 
		repository.save(cust1);
	
		cust2.setBal(amt+cust2.getBal());  
		repository.save(cust2);
		assertEquals(600, cust2.getBal());

	}
		
		
	
	

	@Test

	void test_getAccount() {
		Account account = new Account(131, "Savings", 500);
		
		account.setAccno(1);
		when(repository.findById(account.getAccno())).thenReturn(Optional.ofNullable(account));
		service.getAccountById(1);

		
		repository.save(account);
		
		assertEquals(1, account.getAccno());
	}

	@Test
	void test_getAllAccounts() {
		List<Account> list = new ArrayList<Account>();
		Account a1=new Account(1, "Savings", 100000);
		Account a2=new Account(2, "Current", 200000);
		Account a3=new Account(3, "Demat", 400000);
		list.add(a1);
		list.add(a2);
		list.add(a3);
		when(repository.findAll()).thenReturn(list);

		List<Account> acclist = service.getAllAccounts();
		
		
		
		assertEquals(3,acclist.size());
		
		
	}
}

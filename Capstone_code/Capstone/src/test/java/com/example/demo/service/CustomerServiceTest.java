package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;





@TestMethodOrder(OrderAnnotation.class)                                                                                     //@TestMethod" refers to any method annotated with @Test,If @TestMethodOrder is not explicitly declared on a test class, inherited from a parent class, or declared on a test interface implemented by a test class, test methods will be ordered using a default algorithm that is deterministic but intentionally nonobvious.
@ExtendWith(MockitoExtension.class)                                                                                          //@ExtendWith annotation is used to load a JUnit 5 extension. JUnit defines an extension API, which allows a third-party vendor like Mockito to hook into the lifecycle of running test classes and add additional functionality.
public class CustomerServiceTest {
	@InjectMocks                                                                                                //creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
	CustomerImplementation service;
	
	
	@Mock                                                                                                         //we need CustomerRepository class in our test but we do not want to invoke the original class. so, we 'Mock' that class which works the same as the original but is actually just a mock.
	private CustomerRepository repository; //repository=mock(CustomerRepository);
	
	@Test
        void test_CreateCustomer() {
		Customer c1 = new Customer();
		c1.setName("Charan");

		c1.setEmail("charanp@gmail.com");

		Customer mockcustomer = new Customer();
		mockcustomer.setName("Charan");

		mockcustomer.setEmail("charanp@gmail.com");

		when(repository.save(any(Customer.class))).thenReturn(mockcustomer);
		Customer c3 = service.createCustomer(c1);
		System.out.println(c3.toString());

		
		assertEquals("charanp@gmail.com", c3.getEmail());
	}
	
	
	
	

	@Test

	void test_getCustomer1() {
		Optional<Customer> customer = Optional.of(new Customer(1,"Kiran","kiranram@wipro.com"));
		when(repository.findById(any(Integer.class))).thenReturn(customer);
		Customer cust = service.getCustomer(1);
		
		Customer savedCustomer=customer.get();
		
		
		System.out.println(savedCustomer.getId()+"\t"+savedCustomer.getName());
		//assertEquals(1, cust.getCustomerId());
		
	}
	
	@Test
	void test_getAllCustomer() {
		List<Customer> customersList=new ArrayList<>();
		customersList.add(new Customer(1,"Kiran","Kiranram@wipro.com"));
		customersList.add(new Customer(2,"Swapnil","swapnilk@wipro.com"));
		when(repository.findAll()).thenReturn(customersList);
		
		List<Customer> customers = service.getAllCustomers();
		
		for(Customer cust: customers) {
			
			System.out.println(cust.getId()+"\t"+cust.getName());
		}
		//assertEquals(2,customers.get(1).getCustomerId());

	}
	
	@Test
	void test_updateCustomer() {
	Customer customer = new Customer(1, "a","abc@wipro.com");
	Optional<Customer> optcustomer = Optional.of(customer);
	when(repository.save(any(Customer.class))).thenReturn(customer);
	when(repository.findById(any(Integer.class))).thenReturn(optcustomer);
	Customer updatedCustomer = service.updateCustomer(customer, 1);
	assertEquals(1, updatedCustomer.getId());



	}
	
}


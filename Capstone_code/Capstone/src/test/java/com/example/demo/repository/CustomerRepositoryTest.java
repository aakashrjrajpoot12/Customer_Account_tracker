package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entities.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository repository;

	@Test
	void createCustomer() {
		System.out.println("Test 1: Entering Create Employee");
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();

		customer1.setName("Chandana");

		customer1.setEmail("chandana.p79@wipro.com");

		customer2.setName("abc");

		customer2.setEmail("abcdef@wipro.com");

		Customer saveCustomer1 = repository.save(customer1);
		Customer saveCustomer2 = repository.save(customer2);

	}

	@Test
	void getAllCustomers() {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();

		customer1.setEmail("tom123@wipro.com");

		customer2.setName("abc");

		customer2.setEmail("pqr@wipro.com");

		repository.save(customer1);
		repository.save(customer2);
		List<Customer> customers = (List<Customer>) repository.findAll();

		System.out.println("Test 2: Customer Details");

		for (Customer c : customers) {
			//System.out.println(c.getCustomerId() + "\t" + c.getName() + "\t" + c.getEmail());
			System.out.println(c.getId() + "\t" + c.getName() + "\t" + c.getEmail());
		}
		assertEquals(2, customers.size());

	}

	@Test

	void SaveCustomer() {
		System.out.println("Executing save Customer");
		Customer customer = new Customer(1, "Satish", "satish.dongare@wipro.com");
		repository.save(customer);
		customer = new Customer(2, "Shruti", "shruti.kamle@wipro.com");
		repository.save(customer);
		customer = new Customer(3, "Nandini", "nandini.gedam@wipro.com");
		repository.save(customer);
		customer = new Customer(4, "Divya", "divya.samrit@wipro.com");
		repository.save(customer);                                                      
	}


	@Test

	void UpdateCustomer() {
		System.out.println("Executing Update Customer");
		Customer customer = null;
		Optional<Customer> findById = repository.findById(3);
		if (findById.isPresent()) {
			customer = findById.get();
			if (customer != null) {
				customer.setName("pooja");

				customer.setEmail("pooja.rawat@wipro.com");
			}
			repository.save(customer);
		}

	}

	@Test


	void DeleteCustomer() {
		System.out.println("Executing Delete Customer");
		Customer customer = null;
		Optional<Customer> findById = repository.findById(4);
		if (findById.isPresent()) {
			customer = findById.get();
			repository.delete(customer);

		}
	}
}

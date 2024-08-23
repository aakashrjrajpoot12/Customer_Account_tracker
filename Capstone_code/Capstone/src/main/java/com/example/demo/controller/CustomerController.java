package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping("/customer")                                                                                               //for mapping HTTP POST requests onto specific handler methods.
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)  {
		Customer cust = service.createCustomer(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);

	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable int id)
			throws ResourceNotFoundException {

		Customer cust = service.updateCustomer(customer, id);
		if (cust != null) {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		}

		throw new ResourceNotFoundException("NO CUSTOMER IS THERE TO UPDATE!");

	}

	@GetMapping("/customer/getall")
	public ResponseEntity<List<Customer>> getAllCustomers() throws ResourceNotFoundException{
		List<Customer> cust = service.getAllCustomers();
		if (cust.isEmpty()) {
			throw new ResourceNotFoundException("No Customers Found, Add customer !");
		}
		return new ResponseEntity<List<Customer>>(cust, HttpStatus.CREATED);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) throws ResourceNotFoundException {

		Customer cust = service.getCustomer(id);
		if (cust == null) {
			throw new ResourceNotFoundException("No Customer Found with This Id");
		}
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);

	}


	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity delete(@PathVariable int id) throws ResourceNotFoundException {
		Customer cust = service.getCustomer(id);
		if (cust == null) {
			throw new ResourceNotFoundException("No Customer Found with This Id");
		}

		service.deleteCustomer(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteall")
	public ResponseEntity<?> custDeleteAll() throws ResourceNotFoundException {
		String msg = service.deleteAll();
		if (msg != null) {
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		throw new ResourceNotFoundException("NO CUSTOMER IS THERE TO DELETE!");
	}

}

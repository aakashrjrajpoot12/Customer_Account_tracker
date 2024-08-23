package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;


@Service
public class CustomerImplementation implements CustomerService {
	@Autowired
	CustomerRepository cus_repo;
	
	
	
	
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return cus_repo.findAll();
	}
	

	
	@Override
	public Customer createCustomer(Customer customer)  {
		
		
		return cus_repo.save(customer);
	}

	@Override
	public void deleteCustomer(int id) {
   cus_repo.deleteById(id);
  
	
		
	}

	@Override
	public Customer updateCustomer(Customer customer, int id) {
	Customer cust = null;
	Optional<Customer> op1 = cus_repo.findById(id);
	if (op1.isPresent()) {
	cust = (Customer) op1.get();
	cust.setAccounts(customer.getAccounts());
	cust.setName(customer.getName());
	cust.setEmail(customer.getEmail());
	return cus_repo.save(cust);
	}
	return cus_repo.save(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Optional<Customer> findById = cus_repo.findById(id);
		Customer customer = findById.get();
		return customer;
	}

	@Override
	public String deleteAll() {

			List<Customer> custlist = new ArrayList<Customer>();
			custlist = (cus_repo.findAll());
			if (!(custlist.isEmpty())) {
			cus_repo.deleteAll();
			return "All Customer data deleted!!";
			}
			return null;
			}
		}
	



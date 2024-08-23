package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Customer;

public interface CustomerService {
	public void deleteCustomer(int id);
	public Customer createCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer updateCustomer(Customer customer ,int id);
	public Customer getCustomer(int id);
	public String deleteAll() ;
}

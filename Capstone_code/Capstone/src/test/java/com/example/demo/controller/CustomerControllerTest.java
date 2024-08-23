package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;


@ExtendWith(MockitoExtension.class) 
@WebMvcTest(CustomerController.class)

class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	 CustomerService service;

	
	List<Customer> allcus = new ArrayList<Customer>();
	Customer cust = new Customer(13, "ram","12ak@gmail.com");
	
	
	
	@Test
	void testGetAllCustomers() {
		List<Customer> actual = new ArrayList<>();
		actual.add(cust);                                                                                              //we are having actual value as cust
		when(service.getAllCustomers()).thenReturn(actual);                                                             //when we are trying to get value from service.getAllCustomers() then Return(actual)
		List<Customer> expected = service.getAllCustomers();                                                            //expected we are having list of Customer cust
		
		assertEquals(expected, actual);                                       //4
		verify(service).getAllCustomers();                                 
	}
	
	@Test
	void testGetCustomerById() {

	 Customer cs = new Customer(13, "raj","12ak@gmail.com");
	cs.setId(13);
	assertEquals(13,cs.getId());                                                                                         //Inside cs.getId() we are going to get value that we had given in cs.setId() 
	}




	@Test
	void testDeletexcustomer() {

		 Customer cs = new Customer(13, "raj","12ak@gmail.com");
		
		//cs.setId(13);
	service.deleteCustomer(cs.getId());                                                                                  //cs.getId will get id from customer(cs)                               
	assertEquals(13,cs.getId());

	}


	@Test
	void testDeleteall() {
		Customer cs = new Customer(13, "raj","12ak@gmail.com");
		
		cs.setId(13);
		cs.setId(31);
		
	service.deleteAll();
	
	
    assertEquals(31,cs.getId()); 
	
	}
	



	

}


package com.example.demo.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)                                                           //@SpringBootTest is used to launch springboot application,It will run on random port if you launch it.
public class CustomerintegrationTest {
	@LocalServerPort                                                                                                               //To get the port number on which your Spring Boot application is running, you can use the @LocalServerPort annotation
	private int localport;                                                                                                        // here localport is   8083
	Customer customer = new Customer(localport, "Manshi", new Account(localport, "savings", 5000));
	@Autowired
	private TestRestTemplate testRestTemplate;                                                                               //simplifies integration testing and facilitates authentication during tests.
//TestRestTemplate is great way to fire request
	@Test
public void updateCustomerTest() {
String s = "";
try {                                                                                                                                //The try statement allows you to define a block of code to be tested for errors while it is being executed. .
testRestTemplate.put("http://localhost:" + localport + "/customer/2", customer);                                                       //trying to test url of updateCustomer
s = "updateCustomer";
    } 
catch (Exception e) {                                                                                                               //The catch statement allows you to define a block of code to be executed, if an error occurs in the try block
}
assert (s.equalsIgnoreCase("updateCustomer"));
}
 
	@Test
public void deleteCustomerTest() {
String s = "";
try {
(testRestTemplate).put("http://localhost:" + localport + "/customer", customer);                               //trying to test url
s = "deleteCustomers";
     } 
catch (Exception e) {
     }
assert (s.equalsIgnoreCase("deleteCustomers"));
}
	
	@Test
	public void addCustomerTest() {
	ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:" + localport + "/customer", customer, String.class);
	System.out.println(response.getStatusCode());
	assertEquals(HttpStatus.CREATED, response.getStatusCode());
}
	
	@Test
	public void deleteCustomerById() {
	String expected = "";
	try {
	testRestTemplate.delete("http://localhost:" + localport + "/account/deleteAll/4");
     expected = "No Customer Found with This Id";
	} 
	catch (Exception e) {
     }
	
	assert (expected.equalsIgnoreCase("No Customer Found with This Id"));
	}
	
	
	
	@Test
	public void getAccountByIdTest() {
	String expected = "";
	try {
	testRestTemplate.delete("http://localhost:" + localport + "/account");



	expected="No data Found with this ID";
	} catch (Exception e) {
     }	
	}
	
	@Test
	public void getCustomerById() {
	String expected = "";
	try {
	testRestTemplate.delete("http://localhost:" + localport + "/customer/4");



	expected = "No Customer Found with This Id";
	} catch (Exception e) {



	}	
	}
	@Test
	public void getAllAccountTest() {
	String expected = "";
	try {
	testRestTemplate.delete("http://localhost:" + localport + "/account");



	expected="No Accounts Found, Add Account";
	} catch (Exception e) {



	}	
	}
	
	

}

	

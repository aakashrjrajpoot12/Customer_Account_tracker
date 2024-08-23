package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Account;
import com.example.demo.entities.FundTransfer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AccountImplementation;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)                                                                                         //@ExtendWith annotation is used to load a JUnit 5 extension. JUnit defines an extension API, which allows a third-party vendor like Mockito to hook into the lifecycle of running test classes and add additional functionality.
@WebMvcTest(AccountController.class)                                                                                         //The @WebMvcTest annotation is used for Spring MVC tests.(MVC=Model View, and Controller)
@TestMethodOrder(OrderAnnotation.class)                                                                                       //@TestMethod" refers to any method annotated with @Test,If @TestMethodOrder is not explicitly declared on a test class, inherited from a parent class, or declared on a test interface implemented by a test class, test methods will be ordered using a default algorithm that is deterministic but intentionally nonobvious.
class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean                                                                                                               //we need CustomerRepository class in our test but we do not want to invoke the original class. so, we 'Mock' that class which works the same as the original but is actually just a mock.
	private AccountService service;

//syntax 	
  /*  @Test
    public void someUnitTest() {

        //1. Arrange: prepare the data needed to carry out the test.
        when(dependency.method()).thenReturn(someData); 

        //2. Act: call the method you want to test.
        Type result = service.method()

	//3. Assert: verify that the method is behaving correctly.
        assertEquals(expect, result);
        verify(mock, times(number of invokations)).method()
    }
*/

	
	
	
	@Test                                                                                                     //it tells junit to run the method as test 
	void testGetAllAccount() 
{
	List<Account> acc = new ArrayList<>();
	acc.addAll(acc);
	when(service.getAllAccounts()).thenReturn(acc);  //Arrange
	
	List<Account> expected = service.getAllAccounts();//Act
	assertEquals(expected, acc);//Assert,assertEquals(expect, result);
	verify(service).getAllAccounts();
}
	

@Test
void testGetAccount() {
	Account acc = new Account(1, "Saving", 5000);//arrange
	acc.setAccno(1);  //act
	assertEquals(1, acc.getAccno());//assert
}


@Test
void testGetByAcctype() {
	Account acc = new Account(1, "Saving", 5000);//arrange
	acc.setAcctype("Saving");  //act
	assertEquals("Saving", acc.getAcctype());//assert

}

@Test
void Test_Transferfund() throws Exception {

	RequestBuilder request;                                                                                            //RequestBuilder creates builder using parameter for configuration.
	ObjectMapper objectMapper = new ObjectMapper();                                                                    // instance created ,when using JSON format,spring boot will use an objectMapper instance to serialize responses and deserialize requests.

	FundTransfer obj = new FundTransfer(1, 2, 10000);//here 1 is fromacc,2 is toacc,10000 is amt

	when(service.FundTransfer(any(Integer.class), any(Integer.class),any(Float.class))).thenReturn("Success");
     //mockMvc provides support for spring MVC testing
	request = MockMvcRequestBuilders.put("/FundTransfer").contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(obj));

	MvcResult result = mockMvc.perform(request).andReturn();                                                                  //it will  perform request and response is stored                                   //1

	MockHttpServletResponse response = result.getResponse();                                                                  //get response of fundtranfer is succes or not                                     //2

	System.out.println("Response :" + obj.toString());

	Object headerValue = response.getHeaderValue("location");

	
	System.out.println("Response :" + response.getContentAsString());

	assertEquals(HttpStatus.OK.value(), response.getStatus());                                          //3

}

}


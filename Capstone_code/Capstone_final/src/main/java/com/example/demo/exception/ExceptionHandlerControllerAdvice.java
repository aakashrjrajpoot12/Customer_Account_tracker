package com.example.demo.exception;

import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice                                                                                        //It handles exception at global level
public class ExceptionHandlerControllerAdvice {

	//here 1st and 2nd function are based on http whereas 3rd,4th,5th are implementation
	//here we are sending response we know that ResourceNotFoundException is given
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)//user defined                                                ,is used to handle specific exceptions and sending custom response to client
	@ResponseStatus(value = HttpStatus.NOT_FOUND)                       //we didn't get url and we don't got response
	@ResponseBody
	ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
		ExceptionResponse myresponse = new ExceptionResponse();
		myresponse.setErrorMessage(exception.getMessage());                                          //trying to set value
		return myresponse;
	}
	
	
                                                            //when any internal error is there,here we don't know which type of exception error is there ,that's why we had used Exception.class here
	
	
	   @ExceptionHandler(Exception.class)//built-in class
		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)            //we got HTTp don't know where error is in our server so we handled it universally by Exception.class)
		@ResponseBody
		ExceptionResponse handleException(Exception exception, HttpServletRequest request) {
			ExceptionResponse myresponse = new ExceptionResponse();
			myresponse.setErrorMessage(exception.getMessage());        
			myresponse.setRequestedURI(request.getRequestURI());                              //trying to set value
			return myresponse;
		}
	   
	
   
	 //when Message is Not Readable
	   @ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<?> handler(HttpMessageNotReadableException e) {                        //ResponseEntity<?> we don't know return it may be list,array
		return new ResponseEntity<>("!!No employee object is given!!", HttpStatus.NOT_ACCEPTABLE);//This exception is thrown inside customercontroller inside @PutMapping("/customer/{id}") where inside Postman when we are using PUT :http://localhost:8083/customer/1 ans we are not providing data that needed to be updated then we get output as "No employee object is given!"
	}

	
   
                                                                                        //when the user tries to search customer that not exists inside our database,this error is thrown  
                                                                                                                     //when we are using @GetMapping("/account/{id}") of controller Account
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<?> noDataHandler(NoSuchElementException c) {
		return new ResponseEntity<>("No data exist with this Id", HttpStatus.NOT_FOUND);
	}
	
	
	
                                                                                           //when the user tries to update details of a customer that doesn’t exist in the database.,Exception thrown when an attempt to insert or update data results in violation of an integrity constraint. 
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<?> noDataHandler(DataIntegrityViolationException c) { 
		return new ResponseEntity<>("NO CUSTOMER IS THERE TO UPDATE!", HttpStatus.NOT_FOUND);
	}

}

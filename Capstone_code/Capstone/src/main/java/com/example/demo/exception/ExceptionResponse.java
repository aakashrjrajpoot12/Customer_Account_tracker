package com.example.demo.exception;



public class ExceptionResponse {
private String errorMessage;
private String requestedURI;

//getters and setters
public String getErrorMessage() {
return errorMessage;
}
public void setErrorMessage(String errorMessage) {
this.errorMessage = errorMessage;
}
public String getRequestedURI() {
return requestedURI;
}
public void setRequestedURI(String requestedURI) {
this.requestedURI = requestedURI;
}
}



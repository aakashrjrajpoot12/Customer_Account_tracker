package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                                                                                                                                       //it specifies that table in the database with which entity is mapped
@Table                                                                        //
public class Account {
	@Id                                                                                                                                  //indicates that below mentioned field is primary key 
	private int accno;
	private String acctype;
	private float bal;
	
	 
	
	                                                                                                                                          //getters and setters:  A setter updates the value of a variable, while a getter reads the value of a variable.
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	
	public String getAcctype() {
		return acctype;
	}
	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}
	public float getBal() {
		return bal;
	}
	public void setBal(float bal) {
		this.bal = bal;
	}
	
	public Account(int i, String string, int j) {                 //we had used these line of code as we are getting error inside src/test/java>> inside account>>when i had tried to solve error by default system had generated this code
		// TODO Auto-generated constructor stub
	}
	
    public Account() {
		// TODO Auto-generated constructor stub
	}
	

}

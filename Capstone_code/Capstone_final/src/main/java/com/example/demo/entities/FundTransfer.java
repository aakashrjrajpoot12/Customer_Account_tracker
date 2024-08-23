package com.example.demo.entities;

public class FundTransfer {
	private int acc1;//here acc1 is from account
	private int acc2;//here acc2 is to account
	private float amt;
	
	public int getAcc1() {
		return acc1;
	}
   public void setAcc1(int acc1) {
		this.acc1 = acc1;
	}
	public int getAcc2() {
		return acc2;
	}
	public void setAcc2(int acc2) {
		this.acc2 = acc2;
	}
	public float getAmt() {
		return amt;
	}
	public void setAmt(float amt) {
		this.amt = amt;
	}
	
	//non-parameterized constructor
	public FundTransfer() { 
		super();
		// TODO Auto-generated constructor stub
	}
	//parameterized constructor
	public FundTransfer(int acc1, int acc2, float amt) {
		super();
		this.acc1 = acc1;
		this.acc2 = acc2;
		this.amt = amt;
	}
	@Override
	public String toString() {
		return "FundTransfer [acc1=" + acc1 + ", acc2=" + acc2 + ", amt=" + amt + "]";
	}
	
	

}

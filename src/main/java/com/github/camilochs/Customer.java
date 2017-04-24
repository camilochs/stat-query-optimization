package com.github.camilochs;
public class Customer {
	
	private Long key;
	public Long getKey(){
		return key;
	}
	public void setKey(Long key){
		this.key = key; 
	}
	
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name; 
	}
	
	private String address;
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address; 
	}
	
	private long nationKey;
	public long getNationKey(){
		return nationKey;
	}
	public void setNationKey(long nationKey){
		this.nationKey = nationKey; 
	}
	
	private String phone;
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone; 
	}
	
	private double accountBalance;
	public double getAccountBalance(){
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance){
		this.accountBalance = accountBalance; 
	}
	
	private String mktSegment;
	public String getMktSegment(){
		return mktSegment;
	}
	public void setMktSegment(String mktSegment){
		this.mktSegment = mktSegment; 
	}
	
	private String comment;
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment = comment; 
	}
	
}

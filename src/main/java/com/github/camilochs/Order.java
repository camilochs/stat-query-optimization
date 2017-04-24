package com.github.camilochs;
import java.util.Date;

public class Order {

	private Long key;
	public Long getKey(){
		return key;
	}
	public void setKey(Long key){
		this.key = key; 
	}
	
	private long customerKey;
	public long getCustomerKey(){
		return customerKey;
	}
	public void setCustomerKey(long customerKey){
		this.customerKey = customerKey;
	}
	
	private String orderStatus;
	public String getOrderStatus(){
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}
	
	private Double totalPrice;
	public Double getTotalPrice(){
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice){
		this.totalPrice = totalPrice;
	}
	
	private Date orderDate;
	public Date getOrderDate(){
		return orderDate;
	}
	public void setOrderDate(Date orderDate){
		this.orderDate = orderDate;
	}
	
	private String orderPriority;
	public String getOrderPriority(){
		return orderPriority;
	}
	public void setOrderPriority(String orderPriority){
		this.orderPriority = orderPriority;
	}
	
	
	private long clerkKey;
	public long getClerkKey(){
		return clerkKey;
	}
	public void setClerkKey(long clerkKey){
		this.clerkKey = clerkKey; 
	}
	
	private int shipPriority;
	public int getShipPriority(){
		return shipPriority;
	}
	public void setShipPriority(int shipPriority){
		this.shipPriority = shipPriority; 
	}
	
	private String comment;
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	
	
	
}

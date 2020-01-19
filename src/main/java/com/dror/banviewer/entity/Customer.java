package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TBCUSTOMER")
public class Customer 
{
	@Id
	@Column(name="CUSTOMER_ID")
	private String customerId;

	public String getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}
	
}

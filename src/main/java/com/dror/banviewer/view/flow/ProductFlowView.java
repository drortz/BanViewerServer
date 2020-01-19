package com.dror.banviewer.view.flow;

import org.springframework.stereotype.Component;

@Component
public class ProductFlowView 
{
	private String orderActionId;
	private String orderActionType;
	private String orderActionStatus;
	private String productName;
	
	public String getOrderActionStatus() 
	{
		return orderActionStatus;
	}

	public void setOrderActionStatus(String orderActionStatus) 
	{
		this.orderActionStatus = orderActionStatus;
	}
	
	public String getOrderActionId() 
	{
		return orderActionId;
	}
	
	public void setOrderActionId(String orderActionId) 
	{
		this.orderActionId = orderActionId;
	}
	
	public String getOrderActionType() 
	{
		return orderActionType;
	}
	
	public void setOrderActionType(String orderActionType) 
	{
		this.orderActionType = orderActionType;
	}
	
	public String getProductName() 
	{
		return productName;
	}
	
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	
	
}

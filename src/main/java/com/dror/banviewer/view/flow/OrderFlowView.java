package com.dror.banviewer.view.flow;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderFlowView 
{
	private String orderId;
	private List<ProductFlowView> productFlowView;
	
	public String getOrderId() 
	{
		return orderId;
	}
	
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}
	
	public List<ProductFlowView> getProductFlowView() 
	{
		return productFlowView;
	}
	
	public void setProductFlowView(List<ProductFlowView> productFlowView) 
	{
		this.productFlowView = productFlowView;
	}
	
	
}

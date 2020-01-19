package com.dror.banviewer.view;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ComponentsChangedInOrderAction 
{
	private String apId;
	private String version;
	private String serviceType;
	private String status;
	private String state;
	private List<AttributeView> attributes;
	
	public List<AttributeView> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeView> attributes) {
		this.attributes = attributes;
	}

	public String getApId() 
	{
		return apId;
	}
	
	public void setApId(String apId) 
	{
		this.apId = apId;
	}
	
	public String getVersion() 
	{
		return version;
	}
	
	public void setVersion(String version) 
	{
		this.version = version;
	}
	
	public String getServiceType() 
	{
		return serviceType;
	}
	
	public void setServiceType(String serviceType) 
	{
		this.serviceType = serviceType;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public String getState() 
	{
		return state;
	}
	
	public void setState(String state) 
	{
		this.state = state;
	}
	
}

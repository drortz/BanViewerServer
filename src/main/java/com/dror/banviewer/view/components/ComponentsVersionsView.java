package com.dror.banviewer.view.components;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ComponentsVersionsView 
{
	private String apId;
	private String serviceType;
	private List<ComponentSingleVersionView> singleVersion;
	
	public String getApId() 
	{
		return apId;
	}
	
	public List<ComponentSingleVersionView> getSingleVersion() {
		return singleVersion;
	}

	public void setSingleVersion(List<ComponentSingleVersionView> singleVersion) {
		this.singleVersion = singleVersion;
	}

	public void setApId(String apId) 
	{
		this.apId = apId;
	}
	
	public String getServiceType() 
	{
		return serviceType;
	}
	
	public void setServiceType(String serviceType) 
	{
		this.serviceType = serviceType;
	}
	
}

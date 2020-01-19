package com.dror.banviewer.view.components;

import java.util.List;

import com.dror.banviewer.view.AttributeView;

public class MainComponentsVersionsView 
{
	public String serviceType;
	public String apId;
	public List<ComponentsVersionsView> componentVersionView;
	
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getApId() {
		return apId;
	}
	public void setApId(String apId) {
		this.apId = apId;
	}
	public List<ComponentsVersionsView> getComponentVersionView() {
		return componentVersionView;
	}
	public void setComponentVersionView(List<ComponentsVersionsView> componentVersionView) {
		this.componentVersionView = componentVersionView;
	}
	
	
}

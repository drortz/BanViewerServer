package com.dror.banviewer.view;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ComponentAttrVersionsView 
{
	public String serviceType;
	public String apId;
	public boolean isMainComponent;
	public List<ComponentsChangedInOrderAction> componentVersionDetails;
	
	public boolean getIsMainComponent() {
		return isMainComponent;
	}
	public void setIsMainComponent(boolean isMainComponent) {
		this.isMainComponent = isMainComponent;
	}
	public String getApId() {
		return apId;
	}
	public void setApId(String apId) {
		this.apId = apId;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public List<ComponentsChangedInOrderAction> getComponentVersionDetails() {
		return componentVersionDetails;
	}
	public void setComponentVersionDetails(List<ComponentsChangedInOrderAction> componentVersionDetails) {
		this.componentVersionDetails = componentVersionDetails;
	}
	
	
}

package com.dror.banviewer.view;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InterfacesToOrderAction 
{
	String orderAction;
	String orderActionType;
	List<InterfacesData> interfacesData;
	
	public String getOrderActionType() {
		return orderActionType;
	}
	public void setOrderActionType(String orderActionType) {
		this.orderActionType = orderActionType;
	}
	public String getOrderAction() {
		return orderAction;
	}
	public void setOrderAction(String orderAction) {
		this.orderAction = orderAction;
	}
	public List<InterfacesData> getInterfacesData() {
		return interfacesData;
	}
	public void setInterfacesData(List<InterfacesData> interfacesData) {
		this.interfacesData = interfacesData;
	}
	
	
}

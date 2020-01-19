package com.dror.banviewer.view.components;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dror.banviewer.view.AttributeView;

@Component
public class ComponentSingleVersionView 
{
	private String version;
	private String serviceType;
	private String status;
	private String state;
	private List<AttributeView> attributes;
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<AttributeView> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<AttributeView> attributes) {
		this.attributes = attributes;
	}
	
	
}

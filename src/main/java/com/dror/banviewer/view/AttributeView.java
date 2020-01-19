package com.dror.banviewer.view;

import org.springframework.stereotype.Component;

@Component
public class AttributeView 
{
	private String name;
	private String value;
	private boolean wasChanged;
	
	public boolean isWasChanged() {
		return wasChanged;
	}
	public void setWasChanged(boolean wasChanged) {
		this.wasChanged = wasChanged;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

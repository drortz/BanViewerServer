package com.dror.banviewer.view;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AttributesView 
{
	private List<AttributeView> attributes;

	public List<AttributeView> getAttributes() 
	{
		return attributes;
	}

	public void setAttributes(List<AttributeView> attributes) 
	{
		this.attributes = attributes;
	} 
}

package com.dror.banviewer.view;

import org.springframework.stereotype.Component;

@Component
public class omsXmlView 
{
	private String stepInstanceId;
	private String xmlType;
	private String fileContent;
	private String interfaceName;
	private String time;

	public String getStepInstanceId() 
	{
		return stepInstanceId;
	}

	public void setStepInstanceId(String stepInstanceId) 
	{
		this.stepInstanceId = stepInstanceId;
	}

	public String getXmlType() 
	{
		return xmlType;
	}

	public void setXmlType(String xmlType) 
	{
		this.xmlType = xmlType;
	}

	public String getFileContent() 
	{
		return fileContent;
	}

	public void setFileContent(String fileContent) 
	{
		this.fileContent = fileContent;
	}

	public String getInterfaceName() 
	{
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) 
	{
		this.interfaceName = interfaceName;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}
}

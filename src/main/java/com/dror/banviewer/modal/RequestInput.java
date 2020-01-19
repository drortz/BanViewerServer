package com.dror.banviewer.modal;

import org.springframework.stereotype.Component;

@Component
public class RequestInput 
{
	private String dbUserName;
	private String dbPassword;
	private String dbInstance;
	private String banId;
	
	private boolean isDirectConnect;
	private String host;
	private String port;
	private String serviceName;
	
	public boolean getIsDirectConnect() {
		return isDirectConnect;
	}

	public void setIsDirectConnect(boolean isDirectConnect) {
		this.isDirectConnect = isDirectConnect;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDbUserName() 
	{
		return dbUserName;
	}
	
	public void setDbUserName(String dbUserName) 
	{
		this.dbUserName = dbUserName;
	}
	
	public String getDbPassword() 
	{
		return dbPassword;
	}
	
	public void setDbPassword(String dbPassword) 
	{
		this.dbPassword = dbPassword;
	}
	
	public String getDbInstance()
	{
		return dbInstance;
	}
	
	public void setDbInstance(String dbInstance)
	{
		this.dbInstance = dbInstance;
	}
	
	public String getBanId() 
	{
		return banId;
	}
	
	public void setBanId(String banId) 
	{
		this.banId = banId;
	}
	
}

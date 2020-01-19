package com.dror.banviewer.conf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.dror.banviewer.dal.DataSourceConectionContextHolder;

@Component
public class DataSourceSetter 
{
	@Autowired
	ApplicationContext context;
	
	public void setDataSourceConnection(String url, String password, String userName)
	{
		url = formatVappDbUrl(url);
		url = "jdbc:oracle:thin:@" + url;
		String key = url + "_" + password + "_" + userName;
		Map <Object, Object> targetDataSources = (Map<Object, Object>) context.getBean("targetDataSources");
		
		if (!targetDataSources.containsKey(key))
		{
			DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			driverManagerDataSource.setUrl(url);
			driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			driverManagerDataSource.setPassword(password);
			driverManagerDataSource.setUsername(userName);
			targetDataSources.put(key, driverManagerDataSource);
		}
		
		AbstractRoutingDataSource abstractRoutingDataSource = (AbstractRoutingDataSource) context.getBean("dataSource");
		abstractRoutingDataSource.setTargetDataSources(targetDataSources);
		abstractRoutingDataSource.afterPropertiesSet();
		
		DataSourceConectionContextHolder.setDataSourceConection(key);
	}
	
	public void setDataSourceConnection(String url, String password, String userName, 
			boolean isDirectConnect, String host, String port, String serviceName)
	{
		if(!isDirectConnect)
		{
			setDataSourceConnection(url, password, userName);
			return;
		}
		
		String dbUrl = "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=" + host + ")(port=" + port + "))(connect_data=(server=dedicated)(service_name=" + serviceName + ")(failover_mode=(type=select)(method=basic)(retries=180)(delay=5))))";
		
		
		String key = dbUrl + "_" + password + "_" + userName;
		
		Map <Object, Object> targetDataSources = (Map<Object, Object>) context.getBean("targetDataSources");
		
		if (!targetDataSources.containsKey(key))
		{
			DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			driverManagerDataSource.setUrl(dbUrl);
			driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			driverManagerDataSource.setPassword(password);
			driverManagerDataSource.setUsername(userName);
			
			targetDataSources.put(key, driverManagerDataSource);
		}
		
		AbstractRoutingDataSource abstractRoutingDataSource = (AbstractRoutingDataSource) context.getBean("dataSource");
		abstractRoutingDataSource.setTargetDataSources(targetDataSources);
		abstractRoutingDataSource.afterPropertiesSet();
		
		DataSourceConectionContextHolder.setDataSourceConection(key);
	}

	private String formatVappDbUrl(String url) 
	{
		if (url == null)
		{
			return url;
		}
		
		if (!url.startsWith("ATTCDB") || url.length() > 10)
		{
			return url;
		}
		
		String vappNumber = url.substring("ATTCDB".length());
		url = "illnqw" + vappNumber + ":1521:ATTCDB" + vappNumber;
		return url;
	}
}

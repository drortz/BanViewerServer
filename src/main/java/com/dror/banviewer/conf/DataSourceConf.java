package com.dror.banviewer.conf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.dror.banviewer.dal.CustomerRoutingDataSource;
import com.dror.banviewer.dal.DataSourceConectionContextHolder;

@Configuration
public class DataSourceConf 
{
	@Bean
	DataSource dataSource()
	{
		DataSourceConectionContextHolder.setDataSourceConection("dror");
		CustomerRoutingDataSource customerRoutingDataSource = new CustomerRoutingDataSource();
		customerRoutingDataSource.setTargetDataSources(targetDataSources());
		
		return customerRoutingDataSource;
	}
	
	@Bean
	Map <Object, Object> targetDataSources()
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@illnqw1645:1521:ATTCDB164");
		driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		driverManagerDataSource.setPassword("OMS1OMS");
		driverManagerDataSource.setUsername("OMS1OMS");
		
		Map <Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("dror", driverManagerDataSource);
		return targetDataSources;
	}
	
//	@Bean
//    DataSource dataSource() throws SQLException 
//	{
//        OracleDataSource dataSource = new OracleDataSource();
//        dataSource.setUser("oms1oms");
//        dataSource.setPassword("oms1oms");
//        dataSource.setURL("jdbc:oracle:thin:@illnqw1645:1521:ATTCDB1645");
//        dataSource.setImplicitCachingEnabled(true);
//        dataSource.setFastConnectionFailoverEnabled(true);
//        return dataSource;
//    }
}

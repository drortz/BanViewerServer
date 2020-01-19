package com.dror.banviewer.restcontroller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.view.TestDatabaseConnectionOutput;

@RestController
public class TestDatabaseConnectionRestController 
{
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@Autowired
	DataSource dataSource;
	
	@CrossOrigin
	@GetMapping("/test-database-connection/{databaseUrl}/{databaseUserName}/{databasePassword}")
	public  TestDatabaseConnectionOutput testDatabaseConnection(
			@PathVariable("databaseUrl") String databaseUrl,
			@PathVariable("databaseUserName") String databaseUserName,
			@PathVariable("databasePassword") String databasePassword)
	{
		TestDatabaseConnectionOutput testDatabaseConnectionOutput = new TestDatabaseConnectionOutput();
		
		if (isEmptyString(databaseUrl) ||  isEmptyString(databaseUserName) || isEmptyString(databasePassword))
		{
			testDatabaseConnectionOutput.setTestOutput("User, password and Instance are required.");
			return testDatabaseConnectionOutput;
		}
		
		try 
		{
			dataSourceSetter.setDataSourceConnection(databaseUrl, databasePassword, databaseUserName);
			dataSource.getConnection();
		} 
		catch (SQLException e) 
		{
			testDatabaseConnectionOutput.setTestOutput("Exception");
			return testDatabaseConnectionOutput;
		}
		
		testDatabaseConnectionOutput.setTestOutput("OK");
		return testDatabaseConnectionOutput;
	}
	
	private boolean isEmptyString(String string)
	{
		return string == null || string.trim().length() == 0;
	}
	
	@CrossOrigin
	@PostMapping("/test-database-connection")
	public TestDatabaseConnectionOutput testDatabaseConnection(@RequestBody RequestInput requestInput)
	{
		TestDatabaseConnectionOutput testDatabaseConnectionOutput = new TestDatabaseConnectionOutput();
		
		if(!requestInput.getIsDirectConnect())
		{
			if (isEmptyString(requestInput.getDbInstance()) ||  
					isEmptyString(requestInput.getDbUserName()) || 
					isEmptyString(requestInput.getDbPassword()))
			{
				testDatabaseConnectionOutput.setTestOutput("User, password and Instance are required.");
				return testDatabaseConnectionOutput;
			}
		}
		else
		{
			if(isEmptyString(requestInput.getDbUserName()) || 
					isEmptyString(requestInput.getDbPassword()) || 
					isEmptyString(requestInput.getHost()) ||
					isEmptyString(requestInput.getPort()) ||
					isEmptyString(requestInput.getServiceName()))
			{
				testDatabaseConnectionOutput.setTestOutput("User, password, Host, Port & Service Name are required.");
				return testDatabaseConnectionOutput;
			}
		}
		
		try 
		{
			dataSourceSetter.setDataSourceConnection(requestInput.getDbInstance(), 
					requestInput.getDbPassword(), 
					requestInput.getDbUserName(),
					requestInput.getIsDirectConnect(),
					requestInput.getHost(),
					requestInput.getPort(),
					requestInput.getServiceName());
			
			dataSource.getConnection();
		} 
		catch (SQLException e) 
		{
			testDatabaseConnectionOutput.setTestOutput("Exception");
			return testDatabaseConnectionOutput;
		}
		
		testDatabaseConnectionOutput.setTestOutput("OK");
		return testDatabaseConnectionOutput;
	}
}

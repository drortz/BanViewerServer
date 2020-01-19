package com.dror.banviewer.dal;



import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CustomerRoutingDataSource extends AbstractRoutingDataSource 
{

	@Override
	protected Object determineCurrentLookupKey() 
	{
		return DataSourceConectionContextHolder.getDataSourceConection();
	}

}

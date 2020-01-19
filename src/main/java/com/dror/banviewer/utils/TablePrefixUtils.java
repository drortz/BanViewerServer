package com.dror.banviewer.utils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dror.banviewer.dal.DataSourceConectionContextHolder;



@Component
public class TablePrefixUtils 
{

	@Autowired
	private GetTablesPrefix getTablesPrefix;
	
	public List<String> getTableNamesForPrefix()
	{
		return getTablesPrefix.getTableNamesForPrefix();
	}
	
	public String getTableNameWithPrefix(String tableName)
	{	
		Map <String, String> tablesPrefix = getTablesPrefix.getTablesPrefix(DataSourceConectionContextHolder.getDataSourceConection());
		String prefix = tablesPrefix.get(tableName.toUpperCase());
		
		if (prefix == null || prefix.trim().isEmpty()) 
		{
			return tableName;
		}
		
		return prefix + "." + tableName;
	}
}

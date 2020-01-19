package com.dror.banviewer.dal;



import org.hibernate.EmptyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dror.banviewer.utils.TablePrefixUtils;



@Component
public class CustomHibernatInterceptor extends EmptyInterceptor
{
	private static final long serialVersionUID = -8912799098266615245L;
	
	private TablePrefixUtils tablePrefixUtils;
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public String onPrepareStatement(String sql) 
	{
		if (sql.contains(" ALL_TABLES "))
		{
			return sql;
		}
		
		if (tablePrefixUtils == null)
		{
			tablePrefixUtils = context.getBean(TablePrefixUtils.class);
		}
		
		for (String table : tablePrefixUtils.getTableNamesForPrefix()) 
		{
			sql = formatSqlForPrefix(sql, table);
		}
		
		return sql;
	}

	private String formatSqlForPrefix(String sql, String table) 
	{
		String tableUpperCase = table.toUpperCase();
		String tableUpperCaseWithSpace = " " + tableUpperCase + " ";
		
		String tableLowerCase = table.toLowerCase();
		String tableLowerCaseWithSpace = " " + tableLowerCase + " ";
		
		String tableWithPrefix = tablePrefixUtils.getTableNameWithPrefix(tableUpperCase);
		String tableWithPrefixWithSpace = " " + tableWithPrefix + " ";
		
		sql = sql.replace(tableUpperCaseWithSpace, tableWithPrefixWithSpace);
		sql = sql.replace(tableLowerCaseWithSpace, tableWithPrefixWithSpace);
		return sql;
	}
}

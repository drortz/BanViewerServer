package com.dror.banviewer.dal;



public class DataSourceConectionContextHolder 
{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDataSourceConection(String dataSourceConection) 
	{
		contextHolder.set(dataSourceConection);
	}

	   public static String getDataSourceConection() 
	   {
	      return contextHolder.get();
	   }

	   public static void clearDataSourceConection() 
	   {
	      contextHolder.remove();
	   }
}

package com.dror.banviewer.utils;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import com.dror.banviewer.entity.AllTables;
import com.dror.banviewer.repo.AllTablesRepository;

@Component
public class GetTablesPrefix 
{
	@Autowired
	AllTablesRepository allTablesRepository;
	
	private List<String> tableNamesForPrefix;
	
	public List<String> getTableNamesForPrefix()
	{
		return tableNamesForPrefix;
	}
	
	@PostConstruct
	private void init()
	{
		initTableNamesForPrefix();
	}
	
	private void initTableNamesForPrefix()
	{
		tableNamesForPrefix = new ArrayList<>();
		//TODO: temp fix
		tableNamesForPrefix.add("TB_XML"); 
		try 
		{
			ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		    provider.addIncludeFilter(new AnnotationTypeFilter(Table.class));
		    
		    for (BeanDefinition beanDef : provider.findCandidateComponents("com.dror.banviewer.entity")) 
			{
		    	Class<?> cl = Class.forName(beanDef.getBeanClassName());
		    	Table table = cl.getAnnotation(Table.class);
		    	tableNamesForPrefix.add(table.name());
	        }
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Cacheable("TablesPrefix")
	public Map <String, String> getTablesPrefix(String connectionKey)
	{
		Map<String, String> tablePrefix = new HashMap<>();
		List<AllTables> allTables = allTablesRepository.findByTableNameIn(tableNamesForPrefix);
		
		for (AllTables allTable : allTables) 
		{
			tablePrefix.put(allTable.getTableName(), allTable.getOwner());
		}
		
		return tablePrefix;
	}
}

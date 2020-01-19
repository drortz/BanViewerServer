package com.dror.banviewer.conf;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Configuration
public class HibernateConfiguration
{
	@Autowired
    Interceptor interceptor;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	        EntityManagerFactoryBuilder factory, DataSource dataSource,
	        JpaProperties properties) {
	    Map<String, Object> jpaProperties = new HashMap<String, Object>();
	    jpaProperties.putAll(properties.getProperties());
	    jpaProperties.put("hibernate.ejb.interceptor", interceptor);
	    return factory.dataSource(dataSource).packages("com.dror.banviewer")
	            .properties((Map) jpaProperties).build();
	}
    
}

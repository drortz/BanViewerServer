package com.dror.banviewer.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.service.IsBanExistService;

@RestController
public class IsBanExistRestController 
{
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@Autowired
	IsBanExistService isBanExist;
	
	@CrossOrigin
	@GetMapping("/isBanExist/{databaseUrl}/{databaseUserName}/{databasePassword}/{banId}")
	public boolean isBanExist(
			@PathVariable("databaseUrl") String databaseUrl,
			@PathVariable("databaseUserName") String databaseUserName,
			@PathVariable("databasePassword") String databasePassword,
			@PathVariable("banId") String banId)
	{
		dataSourceSetter.setDataSourceConnection(databaseUrl, databasePassword, databaseUserName);
		return isBanExist.isBanExist(banId);
	}
	
	@CrossOrigin
	@PostMapping("/isBanExist")
	public boolean isBanExist(@RequestBody RequestInput request)
	{
		dataSourceSetter.setDataSourceConnection(request.getDbInstance(), 
				request.getDbPassword(), 
				request.getDbUserName(),
				request.getIsDirectConnect(),
				request.getHost(),
				request.getPort(),
				request.getServiceName());
		
		return isBanExist.isBanExist(request.getBanId());
	}
}

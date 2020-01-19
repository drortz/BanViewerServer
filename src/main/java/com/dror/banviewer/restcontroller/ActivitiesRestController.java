package com.dror.banviewer.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.service.ActivityService;
import com.dror.banviewer.view.AssignmentsView;
import com.dror.banviewer.view.omsXmlView;

@RestController
public class ActivitiesRestController 
{
	@Autowired
	ActivityService activityService;
	
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@CrossOrigin
	@GetMapping("/assignments/{databaseUrl}/{databaseUserName}/{databasePassword}/{banid}/{orderActionId}")
	public List<AssignmentsView> getAssignments(
			@PathVariable("databaseUrl") String databaseUrl,
			@PathVariable("databaseUserName") String databaseUserName,
			@PathVariable("databasePassword") String databasePassword,
			@PathVariable("banid") String banId,
			@PathVariable("orderActionId") String orderActionId)
	{
		dataSourceSetter.setDataSourceConnection(databaseUrl, databasePassword, databaseUserName);
		return activityService.getAssignments(banId, orderActionId);
	}
	
	@CrossOrigin
	@GetMapping("/omsxml/{databaseUrl}/{databaseUserName}/{databasePassword}/{stepInstanceId}")
	public List<omsXmlView> getConnectorLogs(
			@PathVariable("databaseUrl") String databaseUrl,
			@PathVariable("databaseUserName") String databaseUserName,
			@PathVariable("databasePassword") String databasePassword,
			@PathVariable("stepInstanceId") String stepInstanceId)
	{
		dataSourceSetter.setDataSourceConnection(databaseUrl, databasePassword, databaseUserName);
		return activityService.getConnectorLogs(stepInstanceId);
	}
}

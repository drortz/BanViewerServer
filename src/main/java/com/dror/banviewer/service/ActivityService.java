package com.dror.banviewer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dror.banviewer.entity.Assignments;
import com.dror.banviewer.entity.omsXml;
import com.dror.banviewer.mapper.AssignmentsMapper;
import com.dror.banviewer.mapper.OmsXmlMapper;
import com.dror.banviewer.repo.AssignmentsRepo;
import com.dror.banviewer.repo.OmsXmlRepo;
import com.dror.banviewer.repo.OrderActionRepo;
import com.dror.banviewer.view.AssignmentsView;
import com.dror.banviewer.view.omsXmlView;

@Service
public class ActivityService 
{
	@Autowired
	OrderActionRepo orderActionRepo;
	
	@Autowired
	AssignmentsRepo assignmentsRepo;
	
	@Autowired
	OmsXmlRepo omsXmlRepo;
	
	@Autowired
	AssignmentsMapper assignmentMapper;
	
	@Autowired
	OmsXmlMapper omsXmlMapper;
	
	public List<AssignmentsView> getAssignments(String banId, String orderActionId)
	{
		List<Assignments> assignments = assignmentsRepo.findByOrderActionId(orderActionId);
		List<AssignmentsView> returnVal = new ArrayList<>();
		assignmentMapper.mapAssignments(assignments, returnVal);
		return returnVal;
	}
	
	public List<omsXmlView> getConnectorLogs(String stepInstanceId)
	{
		List<omsXml> omsXmls = omsXmlRepo.findBystepInstanceId(stepInstanceId);
		List<omsXmlView> returnValue = new ArrayList<>();
		omsXmlMapper.mapAssignments(omsXmls, returnValue);
		return returnValue;
	}
}

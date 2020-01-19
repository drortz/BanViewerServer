package com.dror.banviewer.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.entity.Assignments;
import com.dror.banviewer.entity.OrderAction;
import com.dror.banviewer.entity.omsXml;
import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.repo.AssignmentsRepo;
import com.dror.banviewer.repo.OmsXmlRepo;
import com.dror.banviewer.repo.OrderActionRepo;
import com.dror.banviewer.view.InterfacesData;
import com.dror.banviewer.view.InterfacesToOrderAction;

@Service
public class InterfacesService 
{
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@Autowired
	OrderActionRepo orderActionRepo;
	
	@Autowired
	AssignmentsRepo assignmentsRepo;
	
	@Autowired
	OmsXmlRepo omsXmlRepo;
	
	public List<InterfacesToOrderAction> retrieveInterfaces(@RequestBody RequestInput request)
	{
		
		dataSourceSetter.setDataSourceConnection(request.getDbInstance(), 
				request.getDbPassword(), 
				request.getDbUserName(),
				request.getIsDirectConnect(),
				request.getHost(),
				request.getPort(),
				request.getServiceName());
		
		List<OrderAction> orderActions = orderActionRepo.findByCustomerId(request.getBanId());
		List<Assignments> assignments = assignmentsRepo.findByOrderActionIdIn(retrieveOrderActionsIDs(orderActions));
		List<omsXml> omsXmlBySteps = omsXmlRepo.findByStepInstanceIdIn(retrieveAllStepInstances(assignments));
		
		List<InterfacesToOrderAction> returnValue = new ArrayList<InterfacesToOrderAction>();
		for (OrderAction orderAction : orderActions) 
		{
			InterfacesToOrderAction interfacesToOrderAction = new InterfacesToOrderAction();
			interfacesToOrderAction.setOrderAction(orderAction.getOrderActionId());
			interfacesToOrderAction.setOrderActionType(orderAction.getActionType());
			
			List<InterfacesData> interfacesDataList = new ArrayList<InterfacesData>();
//			List<Assignments> assignments = assignmentsRepo.findByOrderActionId(orderAction.getOrderActionId());
			List<String> stepInstanceIdsForCurrentOA = new ArrayList<>();
			for (Assignments assignment : assignments) 
			{
				if(!assignment.getOrderActionId().equals(orderAction.getOrderActionId()))
				{
					continue;
				}
				stepInstanceIdsForCurrentOA.add(assignment.getStepInstanceId());
			}
			
//			List<omsXml> omsXmlBySteps = omsXmlRepo.findByStepInstanceIdIn(allStepInstanceIds);
			
			for (omsXml xml : omsXmlBySteps) 
			{
				if(!stepInstanceIdsForCurrentOA.contains(xml.getStepInstanceId()))
				{
					continue;
				}
				InterfacesData interfacesData = new InterfacesData();
				interfacesData.setInterfaceName(xml.getInterfaceName());
				interfacesData.setSentAt(xml.getTime());
				interfacesData.setSeqNo(xml.getSeqNo());
				interfacesData.setServerName(xml.getServerName());
				interfacesData.setStepInstanceId(xml.getStepInstanceId());
				interfacesData.setXmlType(xml.getXmlType());
				interfacesData.setFileContent(xml.getFileContent());
				
				interfacesDataList.add(interfacesData);
			}
			
			interfacesDataList.sort(Comparator.comparing(InterfacesData::getSeqNo));
			
			interfacesToOrderAction.setInterfacesData(interfacesDataList);
			returnValue.add(interfacesToOrderAction);
		}
		
		// Sort per OA ID
		returnValue.sort(Comparator.comparing(InterfacesToOrderAction::getOrderAction));
		
		for (OrderAction orderAction : orderActions) 
		{
			if(orderAction.getOwnerRole() != null && orderAction.getOwnerRole().equals("RETAIL_API")
					&& (orderAction.getXmlId() == null || orderAction.getXmlId().equals("")))
			{
				InterfacesToOrderAction interfacesToOrderAction = new InterfacesToOrderAction();
				interfacesToOrderAction.setOrderAction("MS");
				
				List<InterfacesData> interfacesDataList = new ArrayList<InterfacesData>();
				List<omsXml> omsXml = omsXmlRepo.findBystepInstanceIdContaining(orderAction.getCustomerId());
				
				for(omsXml currentOmsxml : omsXml)
				{
					InterfacesData interfacesData = new InterfacesData();
					
					interfacesData.setInterfaceName(currentOmsxml.getInterfaceName());
					interfacesData.setSentAt(currentOmsxml.getTime());
					interfacesData.setSeqNo(currentOmsxml.getSeqNo());
					interfacesData.setServerName(currentOmsxml.getServerName());
					interfacesData.setStepInstanceId(currentOmsxml.getStepInstanceId());
					interfacesData.setXmlType(currentOmsxml.getXmlType());
					interfacesData.setFileContent(currentOmsxml.getFileContent());
					
					interfacesDataList.add(interfacesData);
				}
				
				interfacesDataList.sort(Comparator.comparing(InterfacesData::getSentAt));
				interfacesToOrderAction.setInterfacesData(interfacesDataList);
				
				returnValue.add(interfacesToOrderAction);
				
				break;
			}
		}
		
		return returnValue;
	}
	
	private List<String> retrieveAllStepInstances(List<Assignments> assignments) 
	{
		List<String> returnValue = new ArrayList<>();
		for(Assignments ass : assignments)
		{
			returnValue.add(ass.getStepInstanceId());
		}
		return returnValue;
	}

	private List<String> retrieveOrderActionsIDs(List<OrderAction> inputList) 
	{
		List<String> returnValue = new ArrayList<>();
		for(OrderAction oa : inputList)
		{
			returnValue.add(oa.getOrderActionId());
		}
		
		return returnValue;
	}
}

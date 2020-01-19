package com.dror.banviewer.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.entity.ApItem;
import com.dror.banviewer.entity.OrderAction;
import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.repo.ApItemRepo;
import com.dror.banviewer.repo.OrderActionRepo;
import com.dror.banviewer.view.AttributeView;
import com.dror.banviewer.view.components.ComponentSingleVersionView;
import com.dror.banviewer.view.components.ComponentsVersionsView;
import com.dror.banviewer.view.components.MainComponentsVersionsView;

@Service
public class AttributesCompareService 
{
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@Autowired
	OrderActionRepo orderActionRepo;
	
	@Autowired
	ApItemRepo apItemRepo;
	
	public List<MainComponentsVersionsView> retrieveAttributesCompare(RequestInput request)
	{
		dataSourceSetter.setDataSourceConnection(request.getDbInstance(), 
				request.getDbPassword(), 
				request.getDbUserName(),
				request.getIsDirectConnect(),
				request.getHost(),
				request.getPort(),
				request.getServiceName());
		
		return createComponentsVersionsView(request.getBanId());
	}
	
	
	private List<MainComponentsVersionsView> createComponentsVersionsView(String banId) 
	{
		List<MainComponentsVersionsView> returnValue = new ArrayList<>();
		
		List<OrderAction> orderActions = orderActionRepo.findByCustomerId(banId);
		
		List<String> mainApIds = retrieveMainApIds(orderActions);
		
		List<ApItem> apItems = apItemRepo.findByMainItemIdIn(mainApIds);
		
		populateMainComponents(returnValue, apItems);
		populateComponents(returnValue, apItems);
		populateSingleVersions(returnValue, apItems);
		populateAttrChange(returnValue);
		
		return returnValue;
	}
	

	private void populateAttrChange(List<MainComponentsVersionsView> returnValue) 
	{
		for(MainComponentsVersionsView mainCom : returnValue)
		{
			for(ComponentsVersionsView compVer : mainCom.getComponentVersionView())
			{
				populateAttrChangeIndicator(compVer);
			}
		}
	}


	private void populateAttrChangeIndicator(ComponentsVersionsView compVer) 
	{
		if(compVer.getSingleVersion().size() == 1)
		{
			return;
		}
		
		for(ComponentSingleVersionView singleVer : compVer.getSingleVersion())
		{
			for(AttributeView attr : singleVer.getAttributes())
			{
				if(attr.isWasChanged())
				{
					continue;
				}
				
				for(ComponentSingleVersionView singleVer2 : compVer.getSingleVersion())
				{
					for(AttributeView attr2 : singleVer2.getAttributes())
					{
						if(attr2.getName() != null && attr.getName() != null && attr2.getName().equals(attr.getName())
								&& attr2.getValue()!= null && attr.getValue() != null && !attr2.getValue().equals(attr.getValue()))
						{
//							attr2.setWasChanged(true);
							attr.setWasChanged(true);
						}
					}
				}
			}
		}
	}


	private void populateSingleVersions(List<MainComponentsVersionsView> returnValue, List<ApItem> apItems) 
	{
		for(MainComponentsVersionsView mainCom : returnValue)
		{
			for(ApItem ap : apItems)
			{
				if(mainCom.getApId().equals(ap.getMainItemId()) && 
						ap.getServiceType() != null)
				{
					for(ComponentsVersionsView compVer : mainCom.getComponentVersionView())
					{
						if(ap.getApId().equals(compVer.getApId()))
						{
							ComponentSingleVersionView singleVer = new ComponentSingleVersionView();
							singleVer.setVersion(ap.getVersion());
							singleVer.setServiceType(ap.getServiceType());
							singleVer.setState(ap.getState());
							singleVer.setStatus(ap.getStatus());
							singleVer.setAttributes(createAttributesListView(ap.getAttributes()));
							
							compVer.getSingleVersion().add(singleVer);
							compVer.getSingleVersion().sort(Comparator.comparing(ComponentSingleVersionView::getVersion));
						}
					}
				}
			}
		}
		
		
	}


	private void populateComponents(List<MainComponentsVersionsView> returnValue, List<ApItem> apItems) 
	{
		for(MainComponentsVersionsView mainCom : returnValue)
		{
			for(ApItem ap : apItems)
			{
				if(mainCom.getApId().equals(ap.getMainItemId()) && ap.getServiceType() != null)
				{
					if(isApIdAdded(mainCom.getComponentVersionView(), ap.getApId()))
					{
						continue;
					}
					
					ComponentsVersionsView compVer = new ComponentsVersionsView();
					compVer.setApId(ap.getApId());
					compVer.setServiceType(ap.getServiceType());
					compVer.setSingleVersion(new ArrayList<>());
					
					mainCom.getComponentVersionView().add(compVer);
					
				}
			}
		}
	}


	private boolean isApIdAdded(List<ComponentsVersionsView> componentVersionView, String apId) 
	{
		for(ComponentsVersionsView compVer : componentVersionView)
		{
			if(compVer.getApId().equals(apId))
			{
				return true;
			}
		}
		
		return false;
	}


	private void populateMainComponents(List<MainComponentsVersionsView> returnValue, List<ApItem> apItems) 
	{
		List<String> addApIds = new ArrayList<>();
		for(ApItem ap : apItems)
		{
			if(ap.getMainIndicator().equals("1") &&
				!addApIds.contains(ap.getApId()))
			{
				addApIds.add(ap.getApId());
				
				MainComponentsVersionsView mainComp = new MainComponentsVersionsView();
				mainComp.setApId(ap.getApId());
				mainComp.setServiceType(ap.getServiceType());
				mainComp.setComponentVersionView(new ArrayList<>());
				
				returnValue.add(mainComp);
			}
		}
	}


	private List<String> retrieveMainApIds(List<OrderAction> orderActions) 
	{
		List<String> returnValue = new ArrayList<>();
		for(OrderAction oa : orderActions)
		{
			returnValue.add(oa.getApId());
		}
		
		return returnValue;
	}


	private List<AttributeView> createAttributesListView(String attributes) 
	{
		List<AttributeView> returnValue = new ArrayList<AttributeView>();
		String []splitted = attributes.split(";");
		
		for (int i=0; i<splitted.length; i++)
		{
			String attr = splitted[i];
			String[] attrNameVal = attr.split("\\+");
			AttributeView attributeView = new AttributeView();
			
			if(attrNameVal.length == 2)
			{
				attributeView.setName(attrNameVal[0]);
				attributeView.setValue(attrNameVal[1]);
			}
			else if(attrNameVal.length == 1)
			{
				attributeView.setName(attrNameVal[0]);
			}
			else
			{
				continue;
			}
			returnValue.add(attributeView);
		}
		
		returnValue.sort(Comparator.comparing(AttributeView::getName));
		
		return returnValue;
	}
}

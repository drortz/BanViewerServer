package com.dror.banviewer.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.entity.AddressLocal;
import com.dror.banviewer.entity.ApItem;
import com.dror.banviewer.entity.ApPricePlan;
import com.dror.banviewer.entity.AssignExc;
import com.dror.banviewer.entity.Assignments;
import com.dror.banviewer.entity.BillingCharge;
import com.dror.banviewer.entity.ExtSysReg;
import com.dror.banviewer.entity.OrderAction;
import com.dror.banviewer.entity.OrderActionImpl;
import com.dror.banviewer.entity.SoCustSelection;
import com.dror.banviewer.entity.Xml;
import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.repo.AddressLocalRepo;
import com.dror.banviewer.repo.ApItemRepo;
import com.dror.banviewer.repo.ApPricePlanRepo;
import com.dror.banviewer.repo.AssignExcRepo;
import com.dror.banviewer.repo.AssignmentsRepo;
import com.dror.banviewer.repo.BillingChargeRepo;
import com.dror.banviewer.repo.ExtSysRegRepo;
import com.dror.banviewer.repo.OrderActionImplRepo;
import com.dror.banviewer.repo.OrderActionRepo;
import com.dror.banviewer.repo.SoCustSelectionRepo;
import com.dror.banviewer.repo.XmlRepo;
import com.dror.banviewer.view.AssignmentsView;
import com.dror.banviewer.view.AttributeView;
import com.dror.banviewer.view.ComponentsChangedInOrderAction;
import com.dror.banviewer.view.OrderActionView;
import com.dror.banviewer.view.PricePlanesChangedInOrderAction;
import com.dror.banviewer.view.UserSkillsView;

@Service
public class OrderActionService 
{
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@Autowired
	OrderActionRepo orderActionRepo;
	
	@Autowired
	OrderActionImplRepo orderActionImplRepo;
	
	@Autowired
	SoCustSelectionRepo soCustSelectionRepo;
	
	@Autowired
	AssignmentsRepo assignmentsRepo;
	
	@Autowired
	XmlRepo xmlRepo;
	
	@Autowired
	ApPricePlanRepo apPricePlanRepo;
	
	@Autowired
	BillingChargeRepo billingChargeRepo;
	
	@Autowired
	ApItemRepo apItemRepo;
	
	@Autowired
	ExtSysRegRepo ExtSysRegRepo;
	
	@Autowired
	AssignExcRepo assignExcRepo;
	
	@Autowired
	AddressLocalRepo addressLocalRepo;
	
	public List<OrderActionView> retrieveOrderActionDetails(RequestInput request)
	{
		dataSourceSetter.setDataSourceConnection(request.getDbInstance(), 
				request.getDbPassword(), 
				request.getDbUserName(),
				request.getIsDirectConnect(),
				request.getHost(),
				request.getPort(),
				request.getServiceName());
		
		List<OrderAction> orderActionEntities = orderActionRepo.findByCustomerId(request.getBanId());	
		return convertToOrderActionView(orderActionEntities);
	}
	
	private List<OrderActionView> convertToOrderActionView(List<OrderAction> inputList)
	{
		List<OrderActionView> returnVal = new ArrayList<OrderActionView>();
		
		List<String> orderActionsIDs = retrieveOrderActionsIDs(inputList);
		List<String> apIds = retrieveApIDs(inputList);
		
		List<AddressLocal> address = retrieveAdress(apIds);
		List<Assignments> assignments = retrieveAssignments(orderActionsIDs);
		List<SoCustSelection> soCustSelections = retrieveSoCustSelection(orderActionsIDs);
		List<ApItem> apItemsChanged = retrieveApItemChanged(orderActionsIDs);
		List<ApPricePlan> changedPPList = retrieveChangedPP(orderActionsIDs);
		List<BillingCharge> billingCharge = retrieveBillingCharge(changedPPList);
		List<ExtSysReg> externalSystemRegOAs = retrieveExtSysReg(orderActionsIDs);
		
		List<Xml> xmls = null;
		List<Xml> crmLicXmls = null;
		List<OrderActionImpl> oaImpls = null;
		try
		{
			xmls = xmlRepo.findByXmlIdIn(retrieveXMLsIDs(inputList));
			
			oaImpls = orderActionImplRepo.findByOrderActionIdIn(retrieveOrderActionsIDs(inputList));
			List<String> crmLicXMLsIDs = retrieveCrmLicXMLsIDs(inputList, oaImpls);
			if(crmLicXMLsIDs != null)
			{
				crmLicXmls = xmlRepo.findByXmlIdIn(crmLicXMLsIDs);
			}
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}

		
		for (OrderAction orderActionEnt : inputList) 
		{
			OrderActionView orderActionView = new OrderActionView();
			orderActionView.setOrderActionId(orderActionEnt.getOrderActionId());
			orderActionView.setActionType(orderActionEnt.getActionType());
			orderActionView.setApId(orderActionEnt.getApId());
			orderActionView.setCreationDateTime(orderActionEnt.getCreationDateTime());
			orderActionView.setParentOrder(orderActionEnt.getParentOrder());
			orderActionView.setStatus(orderActionEnt.getStatus());
			orderActionView.setOwnerRole(orderActionEnt.getOwnerRole());
			orderActionView.setOrderId(orderActionEnt.getOrderId());
			
			setDynamicAttributes(orderActionView, orderActionEnt);
			setAssignments(orderActionView, assignments);
			setSoCustSelection(orderActionView, soCustSelections);				
			setUpcReq(orderActionView, orderActionEnt, xmls);
			setIcsReq(orderActionView, orderActionEnt, xmls);
			setCrmLic(orderActionView, oaImpls, crmLicXmls);
			setComponentsChanged(orderActionView, apItemsChanged);
			setPricePlanesChanged(orderActionView, changedPPList, billingCharge);
			setUserSkills(orderActionView, externalSystemRegOAs);
			setAddress(orderActionView, apItemsChanged, address);
			
			returnVal.add(orderActionView);
		}
		
		//Sorting per Creation date.
		returnVal.sort(Comparator.comparing(OrderActionView::getCreationDateTime));
		
		return returnVal;
	}

	private List<ExtSysReg> retrieveExtSysReg(List<String> orderActionsIDs) 
	{
		try 
		{
			return ExtSysRegRepo.findByOrderActionIdInAndExtSystem(orderActionsIDs, "USER_DETAILS");		
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private List<BillingCharge> retrieveBillingCharge(List<ApPricePlan> changedPPList) 
	{
		try 
		{
			return billingChargeRepo.findByApIdIn(retrievePPAPIds(changedPPList));			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private List<ApPricePlan> retrieveChangedPP(List<String> orderActionsIDs) 
	{
		try 
		{
			return apPricePlanRepo.findByOrderActionIdIn(orderActionsIDs);			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private List<ApItem> retrieveApItemChanged(List<String> orderActionsIDs) 
	{
		try 
		{
			return apItemRepo.findByOrderActionIdIn(orderActionsIDs);			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private List<SoCustSelection> retrieveSoCustSelection(List<String> orderActionsIDs) 
	{
		try 
		{
			return soCustSelectionRepo.findByOrderActionIdInAndStatus(orderActionsIDs, "Accepted");			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private List<Assignments> retrieveAssignments(List<String> orderActionsIDs) 
	{
		try 
		{
			return assignmentsRepo.findByOrderActionIdIn(orderActionsIDs);			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private List<AddressLocal> retrieveAdress(List<String> apIds) 
	{
		try 
		{
			return addressLocalRepo.findByApItemIdIn(apIds);			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private void setAddress(OrderActionView orderActionView, List<ApItem> apItemsChanged, List<AddressLocal> address) 
	{
		if(address == null)
		{
			return;
		}
		
		List<AddressLocal> addressLocalViewList = new ArrayList<>();

		String mainItemVersionPerOA = retrieveMainItemVersionPerOA(apItemsChanged, orderActionView.getOrderActionId());
		String mainItemApId = retrieveMainItemApIdPerOA(apItemsChanged, orderActionView.getOrderActionId());
		
		if(mainItemVersionPerOA == null)
		{
			return;
		}
		
		for(AddressLocal addr : address)
		{
			if(addr.getVersion().equals(mainItemVersionPerOA) && addr.getApItemId().equals(mainItemApId))
			{
				addressLocalViewList.add(addr);
			}
		}
		
		
		orderActionView.setAddressLocalViewList(addressLocalViewList);
	}
	
	private String retrieveMainItemApIdPerOA(List<ApItem> apItemsChanged, String orderActionId) 
	{
		for(ApItem item : apItemsChanged)
		{
			if(item.getOrderActionId().equals(orderActionId) && item.getMainIndicator().equals("1"))
			{
				return item.getApId();
			}
		}
		
		return null;
	}

	private String retrieveMainItemVersionPerOA(List<ApItem> apItemsChanged, String orderActionId) 
	{
		for(ApItem item : apItemsChanged)
		{
			if(item.getOrderActionId().equals(orderActionId) && item.getMainIndicator().equals("1"))
			{
				return item.getVersion();
			}
		}
		
		return null;
	}

	private List<String> retrieveApIDs(List<OrderAction> inputList) 
	{
		List<String> returnValue = new ArrayList<String>();
		
		for(OrderAction oa : inputList)
		{
			if(!returnValue.contains(oa.getApId()))
			{
				returnValue.add(oa.getApId());
			}
		}
		
		return returnValue;
	}

	private void setUserSkills(OrderActionView orderActionView, List<ExtSysReg> externalSystemRegOAs) 
	{
		for(ExtSysReg reg : externalSystemRegOAs)
		{
			if(orderActionView.getOrderActionId().equals(reg.getOrderActionId()) &&
					reg.getExtSystem().equals("USER_DETAILS"))
			{
				orderActionView.setUserSkills(createUserSkills(reg));
				break;
			}
		}
	}

	private UserSkillsView createUserSkills(ExtSysReg reg) 
	{
		if(reg == null || reg.getExtSystem() == null)
		{
			return null;
		}
		
		UserSkillsView userSkills = new UserSkillsView();
		
		String skills[] = reg.getRequestData().split(";");
		if(skills == null)
		{
			return null;
		}
		
		if(skills.length == 2)
		{
			userSkills.setSkills(getSkills(skills[0]));
			userSkills.setSecurityProfiles(getSecurityProfile(skills[1]));
		}
		if(skills.length == 1)
		{
			userSkills.setSkills(getSkills(skills[0]));
		}
		
		return userSkills;
	}

	private List<String> getSecurityProfile(String scurityProfile) 
	{
		List<String> returnValue = new ArrayList<>();
		
		if(scurityProfile.startsWith("SECURITY_PROFILE="))
		{
			scurityProfile = scurityProfile.substring("SECURITY_PROFILE=".length());
		}
		String skillsArr[] = scurityProfile.split("$");
		
		for(int i=0; i<skillsArr.length; i++)
		{
			returnValue.add(skillsArr[i]);
		}
		return returnValue;
	}

	private List<String> getSkills(String skills) 
	{
		List<String> returnValue = new ArrayList<>();
		
		if(skills.startsWith("SKILLS="))
		{
			skills = skills.substring("SKILLS=".length());
		}
		String skillsArr[] = skills.split("\\$");
		for(int i=0; i<skillsArr.length; i++)
		{
			returnValue.add(skillsArr[i]);
		}
		return returnValue;
	}

	private List<String> retrieveCrmLicXMLsIDs(List<OrderAction> inputList, List<OrderActionImpl> oaImpls) 
	{	
		if(oaImpls == null || oaImpls.isEmpty())
		{
			return null;
		}
		
		List<String> returnValue = new ArrayList<>();
		for(OrderActionImpl oa : oaImpls)
		{
			returnValue.add(oa.getClarifyXmlId());
		}
		
		return returnValue;
	}

	private List<String> retrieveXMLsIDs(List<OrderAction> inputList) 
	{
		List<String> returnValue = new ArrayList<>();
		for(OrderAction oa : inputList) 
		{
			returnValue.add(oa.getXmlId());
		}
		return returnValue;
	}

	private List<String> retrievePPAPIds(List<ApPricePlan> changedPPList) 
	{
		List<String> returnValue = new ArrayList<>();
		for(ApPricePlan apPP : changedPPList)
		{
			returnValue.add(apPP.getApId());
		}
		return returnValue;
	}

	private List<String> retrieveXmlsIDs(List<OrderAction> inputList) 
	{
		List<String> returnValue = new ArrayList<>();
		for(OrderAction oa : inputList)
		{
			returnValue.add(oa.getXmlId());
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

	private void setDynamicAttributes(OrderActionView orderActionView, OrderAction orderActionEnt) 
	{
		List<AttributeView> dynamicAttributes = new ArrayList<AttributeView>();
		String dynamicAttr = orderActionEnt.getDynamicAttributes();
		
		if(dynamicAttr == null || dynamicAttr.equals(""))
		{
			return;
		}
		
		
		String []splitted = dynamicAttr.split(";");
		for (int i=0; i<splitted.length; i++)
		{
			String attr = splitted[i];
			String[] attrNameVal = attr.split("\\=");
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
			dynamicAttributes.add(attributeView);
		}
		
		
		orderActionView.setDynamicAttributes(dynamicAttributes);
	}

	private void setAssignments(OrderActionView orderActionView, List<Assignments> assignments) 
	{
		if(assignments == null)
		{
			return;
		}
		
		List<AssignmentsView> assignmentView = new ArrayList<AssignmentsView>();
		List<AssignmentsView> assignmentViewEmptyTime = new ArrayList<AssignmentsView>();
		
		for (Assignments currAssignment : assignments) 
		{
			if(!currAssignment.getOrderActionId().equals(orderActionView.getOrderActionId()))
			{
				continue;
			}
			AssignmentsView assView = new AssignmentsView();
			
			boolean isTimeEmpty = currAssignment.getTime() == null || currAssignment.getTime().equals("");
			if(isTimeEmpty)
			{
				assView.setStepInstanceId(currAssignment.getStepInstanceId());
				assView.setDisaplyName(currAssignment.getDescription().replaceFirst("ACTIVITY_DESCRIPTION_", ""));
				assView.setState(currAssignment.getState());
				assView.setOrderActionId(orderActionView.getOrderActionId());
				if(currAssignment.getIsException().equals("1"))
				{
					Optional<AssignExc> assExc = assignExcRepo.findById(currAssignment.getStepInstanceId());
					if(assExc.isPresent())
					{
						assView.setException(assExc.get().getExceptionText());
					}
				}
				assignmentViewEmptyTime.add(assView);
				continue;
			}
			
			assView.setStepInstanceId(currAssignment.getStepInstanceId());
			assView.setDisaplyName(currAssignment.getDescription().replaceFirst("ACTIVITY_DESCRIPTION_", ""));
			assView.setState(currAssignment.getState());
			assView.setTime(currAssignment.getTime());
			assView.setOrderActionId(orderActionView.getOrderActionId());
			if(currAssignment.getIsException().equals("1"))
			{
				Optional<AssignExc> assExc = assignExcRepo.findById(currAssignment.getStepInstanceId());
				if(assExc.isPresent())
				{
					assView.setException(assExc.get().getExceptionText());
				}
			}
			
			assignmentView.add(assView);
		}
		
		assignmentView.sort(Comparator.comparing(AssignmentsView::getTime));
		
		for(AssignmentsView assView: assignmentViewEmptyTime)
		{
			assignmentView.add(assView);
		}
		
		orderActionView.setAssignments(assignmentView);
	}

	private void setSoCustSelection(OrderActionView orderActionView, List<SoCustSelection> soCustSelections) 
	{
		if(soCustSelections == null || soCustSelections.isEmpty())
		{
			return;
		}
		
		List<String> acceptedSalesOffersIds = new ArrayList<String>();
		for (SoCustSelection soCustSelection : soCustSelections) 
		{
			if(!soCustSelection.getOrderActionId().equals(orderActionView.getOrderActionId()))
			{
				continue;
			}
			acceptedSalesOffersIds.add(soCustSelection.getSalesOfferId());
		}
		
		Collections.sort(acceptedSalesOffersIds);
		orderActionView.setAcceptedSalesOfferInOrderAction(acceptedSalesOffersIds);
	}

	private void setIcsReq(OrderActionView orderActionView, OrderAction orderActionEnt, List<Xml> xmls) 
	{
		try 
		{
			boolean isIcsFlow = (orderActionEnt.getOwnerRole() != null && orderActionEnt.getOwnerRole().equals("ICS")) 
					&& (orderActionEnt.getXmlId() != null || !orderActionEnt.getXmlId().equals(""));
			
			if(!isIcsFlow || xmls == null)
			{
				return;
			}
			
			for(Xml xml : xmls)
			{
				if(xml.getXmlId().equals(orderActionEnt.getXmlId()))
				{
					InputStream bis = xml.getXmlContent().getBinaryStream();
					ObjectInputStream ois = new ObjectInputStream(bis);
					String retrieveBlobAsString = (String) ois.readObject();
					orderActionView.setIcsReq(retrieveBlobAsString);
					
					break;
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setUpcReq(OrderActionView orderActionView, OrderAction orderActionEnt, List<Xml> xmls) 
	{
		try 
		{
			// OPUS BAU = not MS flow.
			boolean isOpusBAU = (orderActionEnt.getOwnerRole() != null && orderActionEnt.getOwnerRole().equals("RETAIL_API")) &&
					!(orderActionEnt.getXmlId() == null || orderActionEnt.getXmlId().equals(""));
			if(!isOpusBAU || xmls == null)
			{
				return;
			}
			
			for(Xml xml : xmls)
			{
				if(xml.getXmlId().equals(orderActionEnt.getXmlId()))
				{
					InputStream bis = xml.getXmlContent().getBinaryStream();
					ObjectInputStream ois = new ObjectInputStream(bis);
					String retrieveBlobAsString = (String) ois.readObject();
					orderActionView.setUpcReq(retrieveBlobAsString);
					break;
				}
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setCrmLic(OrderActionView orderActionView, List<OrderActionImpl> oaImpls, List<Xml> xmls) 
	{
		try 
		{
			if(orderActionView.getUpcReq() != null ||
					orderActionView.getIcsReq() != null || xmls == null || oaImpls == null)
			{
				return;
			}
			
			for(OrderActionImpl oaImpl : oaImpls)
			{
				if(!oaImpl.getOrderActionId().equals(orderActionView.getOrderActionId()))
				{
					continue;
				}
				
				for(Xml xml : xmls)
				{
					if(xml.getXmlId().equals(oaImpl.getClarifyXmlId()))
					{
						InputStream bis = xml.getXmlContent().getBinaryStream();
						ObjectInputStream ois = new ObjectInputStream(bis);
						String retrieveBlobAsString = (String) ois.readObject();
						orderActionView.setCrmLic(retrieveBlobAsString);
						
						break;
					}
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setPricePlanesChanged(OrderActionView orderActionView, List<ApPricePlan> apPricePlanes, List<BillingCharge> billingCharge) 
	{
		if(apPricePlanes == null)
		{
			return;
		}
		
		List<PricePlanesChangedInOrderAction> changedPPList = new ArrayList<PricePlanesChangedInOrderAction>();
		
		for (ApPricePlan apPricePlan : apPricePlanes) 
		{
			if(!apPricePlan.getOrderActionId().equals(orderActionView.getOrderActionId()))
			{
				continue;
			}
			PricePlanesChangedInOrderAction ppChanged = new PricePlanesChangedInOrderAction();
			
			ppChanged.setApId(apPricePlan.getApId());
			ppChanged.setBillingId(apPricePlan.getBillingId());
			ppChanged.setState(apPricePlan.getState());
			ppChanged.setStatus(apPricePlan.getStatus());
			ppChanged.setVersion(apPricePlan.getVersion());
			ppChanged.setItemDefId(apPricePlan.getItemDefId());
			
			if(billingCharge == null)
			{
				continue;
			}
			
			for(BillingCharge charge : billingCharge) 
			{
				if(charge != null && charge.getApId().equals(apPricePlan.getApId()) && 
						charge.getVersion().equals(apPricePlan.getVersion()))
				{
					ppChanged.setActualPrice(charge.getActualPrice());
					ppChanged.setDescription(charge.getDescription());
					ppChanged.setProratedAmount(charge.getProratedAmount());
					ppChanged.setType(charge.getType());
				}
			}
//			BillingCharge billingCharge = billingChargeRepo.findByApIdAndVersion(apPricePlan.getApId(), apPricePlan.getVersion());

			
			changedPPList.add(ppChanged);
		}
		
		orderActionView.setPricePlanesChangedInOrderAction(changedPPList);
	}

	private void setComponentsChanged(OrderActionView orderActionView, List<ApItem> apItemsChanged) 
	{
		if(apItemsChanged == null)
		{
			return;
		}
		
		List<ComponentsChangedInOrderAction> changedAps = new ArrayList<ComponentsChangedInOrderAction>();
		for (ApItem apItem : apItemsChanged) 
		{
			if(!apItem.getOrderActionId().equals(orderActionView.getOrderActionId()))
			{
				continue;
			}
			ComponentsChangedInOrderAction apChange = new ComponentsChangedInOrderAction(); 
			
			apChange.setApId(apItem.getApId());
			apChange.setServiceType(apItem.getServiceType());
			apChange.setState(apItem.getState());
			apChange.setStatus(apItem.getStatus());
			apChange.setVersion(apItem.getVersion());
			
			apChange.setAttributes(createAttributesListView(apItem.getAttributes()));
			
			changedAps.add(apChange);
		}
		
		orderActionView.setComponentsChangedInOrderAction(changedAps);
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
		
		return returnValue;
	}
	
}

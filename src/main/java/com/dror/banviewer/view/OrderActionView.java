package com.dror.banviewer.view;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dror.banviewer.entity.AddressLocal;

@Component
public class OrderActionView 
{
	private String orderActionId;
	private String creationDateTime;
	private String orderId;
	private String parentOrder;
	private String status;
	private String actionType;
	private String ownerRole;
	private String apId;
	private String crmLic;
	private String upcReq;
	private String icsReq;
	private UserSkillsView userSkills;
	private List<AttributeView> dynamicAttributes;
	private List<AssignmentsView> assignments;
	private List<String> acceptedSalesOfferInOrderAction;
	private List<ComponentsChangedInOrderAction> ComponentsChangedInOrderAction;
	private List<PricePlanesChangedInOrderAction> pricePlanesChangedInOrderAction;
	private List<AddressLocal> addressLocalViewList;
	
	public List<AddressLocal> getAddressLocalViewList() 
	{
		return addressLocalViewList;
	}

	public void setAddressLocalViewList(List<AddressLocal> addressLocalViewList) 
	{
		this.addressLocalViewList = addressLocalViewList;
	}

	public UserSkillsView getUserSkills() 
	{
		return userSkills;
	}

	public void setUserSkills(UserSkillsView userSkills) 
	{
		this.userSkills = userSkills;
	}
	
	public String getOrderId() 
	{
		return orderId;
	}

	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}
	
	public List<AttributeView> getDynamicAttributes() 
	{
		return dynamicAttributes;
	}

	public void setDynamicAttributes(List<AttributeView> dynamicAttributes) 
	{
		this.dynamicAttributes = dynamicAttributes;
	}
	
	public List<AssignmentsView> getAssignments() 
	{
		return assignments;
	}

	public void setAssignments(List<AssignmentsView> assignments) 
	{
		this.assignments = assignments;
	}
	
	public List<String> getAcceptedSalesOfferInOrderAction() 
	{
		return acceptedSalesOfferInOrderAction;
	}

	public void setAcceptedSalesOfferInOrderAction(List<String> acceptedSalesOfferInOrderAction) 
	{
		this.acceptedSalesOfferInOrderAction = acceptedSalesOfferInOrderAction;
	}
	
	public String getIcsReq() 
	{
		return icsReq;
	}

	public void setIcsReq(String icsReq) 
	{
		this.icsReq = icsReq;
	}
	
	public String getUpcReq() 
	{
		return upcReq;
	}

	public void setUpcReq(String upcReq) 
	{
		this.upcReq = upcReq;
	}
	
	public String getOwnerRole() 
	{
		return ownerRole;
	}

	public void setOwnerRole(String ownerRole) 
	{
		this.ownerRole = ownerRole;
	}
	
	public String getCrmLic() 
	{
		return crmLic;
	}

	public void setCrmLic(String crmLic) 
	{
		this.crmLic = crmLic;
	}
	
	public List<PricePlanesChangedInOrderAction> getPricePlanesChangedInOrderAction() 
	{
		return pricePlanesChangedInOrderAction;
	}

	public void setPricePlanesChangedInOrderAction(List<PricePlanesChangedInOrderAction> pricePlanesChangedInOrderAction) 
	{
		this.pricePlanesChangedInOrderAction = pricePlanesChangedInOrderAction;
	}

	public List<ComponentsChangedInOrderAction> getComponentsChangedInOrderAction() 
	{
		return ComponentsChangedInOrderAction;
	}

	public void setComponentsChangedInOrderAction(List<ComponentsChangedInOrderAction> componentsChangedInOrderAction) 
	{
		ComponentsChangedInOrderAction = componentsChangedInOrderAction;
	}

	public String getOrderActionId() 
	{
		return orderActionId;
	}
	
	public void setOrderActionId(String orderActionId) 
	{
		this.orderActionId = orderActionId;
	}
	
	public String getCreationDateTime() 
	{
		return creationDateTime;
	}
	
	public void setCreationDateTime(String creationDateTime) 
	{
		this.creationDateTime = creationDateTime;
	}
	
	public String getParentOrder() 
	{
		return parentOrder;
	}
	
	public void setParentOrder(String parentOrder) 
	{
		this.parentOrder = parentOrder;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public String getActionType() 
	{
		return actionType;
	}
	
	public void setActionType(String actionType) 
	{
		this.actionType = actionType;
	}
	
	public String getApId() 
	{
		return apId;
	}
	
	public void setApId(String apId) 
	{
		this.apId = apId;
	}
	
}

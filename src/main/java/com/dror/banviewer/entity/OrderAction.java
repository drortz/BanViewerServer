package com.dror.banviewer.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TBORDER_ACTION")
public class OrderAction 
{
	@Id
	@Column(name="order_unit_id")
	private String orderActionId;
	
	@Column(name="ORDER_ID")
	private String orderId;

	@Column(name="CTDB_CRE_DATETIME")
	private String creationDateTime;
	
	@Column(name="CREATION_DATE")
	private Date actualCreationDate;

	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="PARENT_ORDER_UNIT")
	private String parentOrder;
	
	@Column(name="action_type")
	private String actionType;
	
	@Column(name="OWNER_ROLE")
	private String ownerRole;
	
	@Column(name="XML_ID")
	private String xmlId;
	
	@Column(name="DYNAMIC_ATTRS")
	private String dynamicAttributes;
	
	public Date getActualCreationDate() 
	{
		return actualCreationDate;
	}

	public void setActualCreationDate(Date actualCreationDate) 
	{
		this.actualCreationDate = actualCreationDate;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getDynamicAttributes() 
	{
		return dynamicAttributes;
	}

	public void setDynamicAttributes(String dynamicAttributes) 
	{
		this.dynamicAttributes = dynamicAttributes;
	}

	public String getXmlId() 
	{
		return xmlId;
	}

	public void setXmlId(String xmlId) 
	{
		this.xmlId = xmlId;
	}
	
	public String getOwnerRole() 
	{
		return ownerRole;
	}

	public void setOwnerRole(String ownerRole) 
	{
		this.ownerRole = ownerRole;
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

	@Column(name="status")
	private String status;
	
	@Column(name="ap_id")
	private String apId;

	public String getOrderActionId() 
	{
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) 
	{
		this.orderActionId = orderActionId;
	}

	public String getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public String getActionType() 
	{
		return actionType;
	}

	public void setActionType(String actionType) 
	{
		this.actionType = actionType;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) 
	{
		this.apId = apId;
	}
	
	
}

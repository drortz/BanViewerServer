package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name="TBAP_PRICE_PLAN")
@IdClass(ApItemKey.class)
public class ApPricePlan 
{
	@Id
	@Column(name="AP_ID")
	String apId;
	
	@Id
	@Column(name="AP_VERSION_ID")
	String version;
	
	@Column(name="BILLING_ID")
	String billingId;
	
	@Column(name="ITEM_DEF_ID")
	String itemDefId;
	
	@Column(name="STATE")
	String state;
	
	@Column(name="STATUS")
	String status;
	
	@Column(name="ORDER_ACTION_ID")
	String orderActionId;

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	public String getItemDefId() {
		return itemDefId;
	}

	public void setItemDefId(String itemDefId) {
		this.itemDefId = itemDefId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderActionId() {
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) {
		this.orderActionId = orderActionId;
	}
	
	
}

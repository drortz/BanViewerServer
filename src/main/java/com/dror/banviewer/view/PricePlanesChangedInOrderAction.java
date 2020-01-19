package com.dror.banviewer.view;

import org.springframework.stereotype.Component;

@Component
public class PricePlanesChangedInOrderAction 
{
	String apId; 
	String version;
	String billingId; 
	String itemDefId; 
	String type; 
	String description; 
	String actualPrice; 
	String proratedAmount; 
	String state; 
	String status;
	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getProratedAmount() {
		return proratedAmount;
	}
	public void setProratedAmount(String proratedAmount) {
		this.proratedAmount = proratedAmount;
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
	
	
}

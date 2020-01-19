package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name="TBBILLING_CHARGE")
@IdClass(ApItemKey.class)
public class BillingCharge 
{
	@Id
	@Column(name="AP_ITEM_ID")
	String apId;
	
	@Id
	@Column(name="MAIN_ITEM_VERSION")
	private String version;
	
	@Column(name="TYPE")
	String type;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@Column(name="ACTUAL_PRICE")
	String actualPrice;
	
	@Column(name="PRORATED_AMOUNT")
	String proratedAmount;



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
	
	
}

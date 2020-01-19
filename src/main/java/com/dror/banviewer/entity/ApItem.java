package com.dror.banviewer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name="TBAP_ITEM")
@IdClass(ApItemKey.class)
public class ApItem
{
	@Id
	@Column(name="AP_ID")
	private String apId;
	
	@Id
	@Column(name="AP_VERSION_ID")
	private String version;
	
	@Column(name="SERVICE_TYPE")
	private String serviceType;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="ORDER_ACTION_ID")
	private String orderActionId;
	
	@Column(name="ITEM_ATRS_LIST")
	private String attributes;
	
	@Column(name="MAIN_ITEM_ID")
	private String mainItemId;
	
	@Column(name="MAIN_IND")
	private String mainIndicator;
	
	@Column(name="PC_RELATION_VER")
	private String pcVersion;
	
	@Column(name="ITEM_DEF_ID")
	private String cid;
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPcVersion() {
		return pcVersion;
	}

	public void setPcVersion(String pcVersion) {
		this.pcVersion = pcVersion;
	}

	public String getMainIndicator() {
		return mainIndicator;
	}

	public void setMainIndicator(String mainIndicator) {
		this.mainIndicator = mainIndicator;
	}

	public String getMainItemId() {
		return mainItemId;
	}

	public void setMainItemId(String mainItemId) {
		this.mainItemId = mainItemId;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderActionId() {
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) {
		this.orderActionId = orderActionId;
	}
	
	
}

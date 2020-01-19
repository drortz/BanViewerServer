package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name="EXT_SYS_REG")
@IdClass(ExtSystemKey.class)
public class ExtSysReg 
{
	@Column(name="OA_ID")
	@Id
	private String orderActionId;
	
	@Id
	@Column(name="EXT_SYSTEM")
	private String extSystem;
	
	@Column(name="REQUEST_DATA")
	private String requestData;

	public String getOrderActionId() {
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) {
		this.orderActionId = orderActionId;
	}

	public String getExtSystem() {
		return extSystem;
	}

	public void setExtSystem(String extSystem) {
		extSystem = extSystem;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	
	
}

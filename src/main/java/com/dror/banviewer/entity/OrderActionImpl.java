package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TBORDER_ACTION_IMPL")
public class OrderActionImpl 
{
	@Id
	@Column(name="order_unit_id")
	private String orderActionId;
	
	@Column(name="clarify_xml_id")
	private String clarifyXmlId;

	public String getOrderActionId() {
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) {
		this.orderActionId = orderActionId;
	}

	public String getClarifyXmlId() {
		return clarifyXmlId;
	}

	public void setClarifyXmlId(String clarifyXmlId) {
		this.clarifyXmlId = clarifyXmlId;
	}
	
	
}

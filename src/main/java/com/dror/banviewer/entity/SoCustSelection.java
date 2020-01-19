package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="ORD9_TBSO_CUST_SELECTION")
@IdClass(SoCustSelectionKey.class)
public class SoCustSelection 
{
	@Column(name="CUSTOMER_ID")
	private String customerId;
	
	@Column(name="SALES_OFFER_STATUS")
	private String status;
	
	@Id
	@Column(name="CARD_ID")
	private String cardId;
	
	@Id
	@Column(name="ORDER_ACTION_ID")
	private String orderActionId;
	
	@Id
	@Column(name="SALES_OFFER_ID")
	private String salesOfferId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getOrderActionId() {
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) {
		this.orderActionId = orderActionId;
	}

	public String getSalesOfferId() {
		return salesOfferId;
	}

	public void setSalesOfferId(String salesOfferId) {
		this.salesOfferId = salesOfferId;
	}
	
	
}

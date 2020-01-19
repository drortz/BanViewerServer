package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name= "TBASSIGNMENT_V")
public class Assignments 
{
	@Id
	@Column(name = "STEP_INSTANCE_ID")
	private String stepInstanceId;
	
	@Column(name="order_action_id")
	private String orderActionId;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="CTDB_CRE_DATETIME")
	private String time;
	
	@Column(name="IS_EXCEPTION")
	private String isException;

	public String getIsException() 
	{
		return isException;
	}

	public void setIsException(String isException) 
	{
		this.isException = isException;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getStepInstanceId() 
	{
		return stepInstanceId;
	}

	public void setStepInstanceId(String stepInstanceId) 
	{
		this.stepInstanceId = stepInstanceId;
	}

	public String getOrderActionId() 
	{
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) 
	{
		this.orderActionId = orderActionId;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}
	
	
}

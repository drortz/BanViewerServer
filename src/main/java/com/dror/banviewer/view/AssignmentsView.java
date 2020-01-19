package com.dror.banviewer.view;

import org.springframework.stereotype.Component;

@Component
public class AssignmentsView
{
	private String stepInstanceId;
	private String orderActionId;
	private String disaplyName;
	private String state;
	private String time;
	private String exception;

	public String getException() 
	{
		return exception;
	}

	public void setException(String exception) 
	{
		this.exception = exception;
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

	public String getDisaplyName() 
	{
		return disaplyName;
	}

	public void setDisaplyName(String disaplyName) 
	{
		this.disaplyName = disaplyName;
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

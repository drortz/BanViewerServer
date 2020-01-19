package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TBASSIGN_EXC")
public class AssignExc 
{
	@Id
	@Column(name="STEP_INSTANCE_ID")
	private String stepInstance;
	
	@Column(name="MESSAGE_TEXT")
	private String exceptionText;

	public String getStepInstance() 
	{
		return stepInstance;
	}

	public void setStepInstance(String stepInstance) 
	{
		this.stepInstance = stepInstance;
	}

	public String getExceptionText() 
	{
		return exceptionText;
	}

	public void setExceptionText(String exceptionText) 
	{
		this.exceptionText = exceptionText;
	}
	
	
}

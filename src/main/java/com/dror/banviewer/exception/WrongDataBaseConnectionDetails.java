package com.dror.banviewer.exception;



public class WrongDataBaseConnectionDetails extends RuntimeException
{
	private static final long serialVersionUID = -1868199508794564669L;
	public WrongDataBaseConnectionDetails(String exception) 
	{
		super(exception);
	}
}

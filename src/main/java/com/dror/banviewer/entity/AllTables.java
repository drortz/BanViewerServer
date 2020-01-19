package com.dror.banviewer.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ALL_TABLES")
public class AllTables 
{
	@Id
	@Column(name="TABLE_NAME")
	String tableName;
	

	@Column(name="OWNER")
	String owner;
	
	public String getTableName() 
	{
		return tableName;
	}

	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}

	public String getOwner() 
	{
		return owner;
	}

	public void setOwner(String owner) 
	{
		this.owner = owner;
	}
}

package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="TBNAME")
@IdClass(NameKey.class)
public class Name 
{
	@Id
	@Column(name="CID")
	private String cid;
	
	@Id
	@Column(name="PCVERSION_ID")
	private String pcVersion;
	
	@Column(name="NAME_TEXT")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
	
}

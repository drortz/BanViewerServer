package com.dror.banviewer.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name="TB_XML")
public class Xml 
{
	@Id
	@Column(name="XML_ID")
	private String xmlId;
	
	@Column(name="XML_EXTERNAL_ID")
	private String xmlExternalId;
	
	@Lob
	@Column(name="XML_CONTENT")
	private Blob xmlContent;

	public String getXmlId() {
		return xmlId;
	}

	public void setXmlId(String xmlId) {
		this.xmlId = xmlId;
	}

	public String getXmlExternalId() {
		return xmlExternalId;
	}

	public void setXmlExternalId(String xmlExternalId) {
		this.xmlExternalId = xmlExternalId;
	}

	public Blob getXmlContent() {
		return xmlContent;
	}

	public void setXmlContent(Blob xmlContent) {
		this.xmlContent = xmlContent;
	}
	
	
}

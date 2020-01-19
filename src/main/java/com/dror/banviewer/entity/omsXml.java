package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TB_OMS_XML")
public class omsXml 
{
	@Column(name="STEP_INSTANCE_ID")
	private String stepInstanceId;
	
	@Column(name="XML_TYPE")
	private String xmlType;
	
	@Column(name="FILE_CONTENTS")
	private String fileContent;
	
	@Column(name="INTERFACE_NAME")
	private String interfaceName;
	
	@Column(name="CTDB_CRE_DATETIME")
	private String time;
	
	@Column(name="LOGICAL_SERVER_NAME")
	private String serverName;

	@Id
	@Column(name="SEQ_NO")
	private String seqNo;
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getStepInstanceId() 
	{
		return stepInstanceId;
	}

	public void setStepInstanceId(String stepInstanceId) 
	{
		this.stepInstanceId = stepInstanceId;
	}

	public String getXmlType() 
	{
		return xmlType;
	}

	public void setXmlType(String xmlType) 
	{
		this.xmlType = xmlType;
	}

	public String getFileContent() 
	{
		return fileContent;
	}

	public void setFileContent(String fileContent) 
	{
		this.fileContent = fileContent;
	}

	public String getInterfaceName() 
	{
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) 
	{
		this.interfaceName = interfaceName;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}
	
	
}

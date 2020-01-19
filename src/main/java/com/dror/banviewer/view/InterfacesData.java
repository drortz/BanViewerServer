package com.dror.banviewer.view;

import org.springframework.stereotype.Component;

@Component
public class InterfacesData 
{
	String interfaceName;
	String stepInstanceId;
	String xmlType;
	String seqNo;
	String sentAt;
	String serverName;
	String fileContent;
	
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getStepInstanceId() {
		return stepInstanceId;
	}
	public void setStepInstanceId(String stepInstanceId) {
		this.stepInstanceId = stepInstanceId;
	}
	public String getXmlType() {
		return xmlType;
	}
	public void setXmlType(String xmlType) {
		this.xmlType = xmlType;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getSentAt() {
		return sentAt;
	}
	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	
	
}

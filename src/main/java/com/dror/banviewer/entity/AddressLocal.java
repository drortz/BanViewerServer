package com.dror.banviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TBADDRESS_LOCAL")
public class AddressLocal 
{
	@Column(name="AP_ITEM_ID")
	private String apItemId;
	
	@Column(name="TYPE")
	private String type;
	
	@Id
	@Column(name="ADDRESS_ID")
	private String id;
	
	@Column(name="STREET_NAME")
	private String streetName;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	@Column(name="SITE_ID")
	private String siteId;
	
	@Column(name="AP_VERSION_ID")
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApItemId() {
		return apItemId;
	}

	public void setApItemId(String apItemId) {
		this.apItemId = apItemId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	
}

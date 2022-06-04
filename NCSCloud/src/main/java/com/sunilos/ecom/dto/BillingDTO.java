package com.sunilos.ecom.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;
@Entity
@Table(name = "NCS_BILLING")
public class BillingDTO extends BaseDTO {

	
	
	@Column(name = "NAME", length = 50)
	protected String name = null;
	
	@Column(name = "LASTNAME", length = 50)
	protected String lastname = null;
	
	@Column(name = "ADDRESS", length = 50)
	protected String address = null;
	
	@Column(name = "MOBILENO", length = 50)
	protected String mobileno = null;
	
	@Column(name = "PINCODE", length = 50)
	protected Integer pincode = 0;
	
	@Column(name = "CITY", length = 50)
	protected String city = null;
	
	@Column(name = "STATE", length = 50)
	protected String state = null;

	@Column(name = "SID", length = 50)
	protected Long sid ;
	
	
	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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
	
	


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}

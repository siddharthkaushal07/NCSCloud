package com.sunilos.ecom.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * SellerRegistration POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_SELLERREGISTRATION")
public class SellerRegistrationDTO extends BaseDTO {

	@Column(name = "NAME", length = 30)
	protected String name = null;

	@Column(name = "LOGIN", length = 50)
	protected String login;
	
	@Column(name = "PASSWORD", length = 30)
	protected String password;
	
	@Column(name = "BUSINESS_NAME", length = 30)
	protected String BusinessName;
	
	@Column(name = "ADDRESS", length = 50)
	protected String address;
	
	@Column(name = "CITY", length = 20)
	protected String city;
	
	@Column(name = "PINCODE", length = 10)
	protected Integer pincode;
	
	@Column(name = "MOBILE", length = 15)
	protected String mobile;
	
	@Column(name = "STATE", length = 20)
	protected String state;
	
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return name;
	}
	
	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("name", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("login", login);
		return map;
	}	

}



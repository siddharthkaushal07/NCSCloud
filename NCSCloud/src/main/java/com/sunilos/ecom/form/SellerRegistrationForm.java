package com.sunilos.ecom.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;
import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.ecom.dto.SellerRegistrationDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class SellerRegistrationForm extends BaseForm {

	@NotEmpty
	private String name = null;
	
	@NotEmpty
	protected String login;
	
	@NotEmpty
	protected String password;
	
	@NotEmpty
	protected String businessName;
	
	@NotEmpty
	protected String address;
	
	@NotEmpty
	protected String city;
	
	@NotNull
	protected Integer pincode;
	
	@NotEmpty
	protected String mobile;
	
	@NotEmpty
	protected String state;
	
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public BaseDTO getDto() {
		SellerRegistrationDTO dto = initDTO(new SellerRegistrationDTO());
		dto.setName(name);
		dto.setLogin(login);
		dto.setPassword(password);
		dto.setBusinessName(businessName);
		dto.setMobile(mobile);
		dto.setAddress(address);
		dto.setCity(city);
		dto.setPincode(pincode);
		dto.setState(state);
		
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		SellerRegistrationDTO dto = (SellerRegistrationDTO) bDto;
		name = dto.getName();	
	}
	
	
}

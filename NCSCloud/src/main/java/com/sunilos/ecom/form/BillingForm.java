package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;

import com.sunilos.ecom.dto.BillingDTO;

public class BillingForm extends BaseForm {

	
	
	@NotEmpty
	private String name = null;
	private String lastname = null;
	private String address = null;
	private String mobileno = null;
	private Integer pincode = 0;
	private String state = null;
	private String city = null;
	private Long sid ;
	
	
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	@Override
	public BaseDTO getDto() {
	
		BillingDTO dto=initDTO(new BillingDTO());
		
		System.out.println("in bill form get dto ---------"+name+" =====sid====>>"+sid);
		dto.setName(name);
		dto.setLastname(lastname);
		dto.setAddress(address);
		dto.setMobileno(mobileno);
		dto.setPincode(pincode);
		dto.setCity(city);
		dto.setState(state);
		dto.setSid(sid);
		return dto;	 
	
	}
	
	@Override
	public void populate(BaseDTO bDto) {
		
		super.populate(bDto);
		BillingDTO dto=(BillingDTO) bDto;
		name = dto.getName();	
	}
}

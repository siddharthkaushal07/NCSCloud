package com.sunilos.ecom.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * enquiry POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_ENQUIRY")
public class EnquiryDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	protected String name = null;

	@Column(name = "LASTNAME", length = 50)
	protected String lastname = null;

	@Column(name = "MOBILE", length = 50)
	protected String mobile = null;

	@Column(name = "EMAILID", length = 50)
	protected String emailid = null;

	@Column(name = "ALTERNATEMOB", length = 50)
	protected String alternatemob = null;

	@Column(name = "STATUS", length = 50)
	protected String status = null;

	@Column(name = "GENDER", length = 50)
	protected String gender = null;

	@Column(name = "ADDRESS", length = 50)
	protected String address = null;

	@Column(name = "DOB", length = 50)
	protected Date dob = null;

	@Column(name = "REFBY", length = 50)
	protected String refby = null;

	@Column(name = "NFUDATE", length = 50)
	protected Date nfudate = null;

	@Column(name = "ENQDATE", length = 50)
	protected Date enqdate = null;

	@Column(name = "ENQMODE", length = 50)
	protected String enqmode = null;

	@Column(name = "TRAINING", length = 50)
	protected String training = null;

	@Column(name = "ATTENDBY", length = 50)
	protected String attendby = null;

	@Column(name = "REMARK", length = 50)
	protected String remark = null;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAlternatemob() {
		return alternatemob;
	}

	public void setAlternatemob(String alternatemob) {
		this.alternatemob = alternatemob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getRefby() {
		return refby;
	}

	public void setRefby(String refby) {
		this.refby = refby;
	}

	public Date getNfudate() {
		return nfudate;
	}

	public void setNfudate(Date nfudate) {
		this.nfudate = nfudate;
	}

	public Date getEnqdate() {
		return enqdate;
	}

	public void setEnqdate(Date enqdate) {
		this.enqdate = enqdate;
	}

	public String getEnqmode() {
		return enqmode;
	}

	public void setEnqmode(String enqmode) {
		this.enqmode = enqmode;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getAttendby() {
		return attendby;
	}

	public void setAttendby(String attendby) {
		this.attendby = attendby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		map.put("name", name);
		return map;
	}

}

package com.sunilos.ecom.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.EnquiryDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class EnquiryForm extends BaseForm {

	@NotEmpty
	private String name = null;
	private String lastname = null;
	private String enqmode = null;
	private String training = null;
	private Date enqdate = null;
	private String mobile = null;
	private String emailid = null;
	private String alternatemob = null;
	private String status = null;
	private String refby = null;
	private Date nfudate = null;
	private String gender = null;
	private Date dob = null;
	private String address = null;
	private String attendby = null;
	private String remark = null;

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

	public Date getEnqdate() {
		return enqdate;
	}

	public void setEnqdate(Date enqdate) {
		this.enqdate = enqdate;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public BaseDTO getDto() {
		EnquiryDTO dto = initDTO(new EnquiryDTO());
		dto.setName(name);
		dto.setLastname(lastname);
		dto.setEnqdate(enqdate);
		dto.setEnqmode(enqmode);
		dto.setTraining(training);
		dto.setEmailid(emailid);
		dto.setMobile(mobile);
		dto.setAlternatemob(alternatemob);
		dto.setStatus(status);
		dto.setRefby(refby);
		dto.setNfudate(nfudate);
		dto.setGender(gender);
		dto.setDob(dob);
		dto.setAddress(address);
		dto.setAttendby(attendby);
		dto.setRemark(remark);

		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		EnquiryDTO dto = (EnquiryDTO) bDto;
		name = dto.getName();
		lastname=dto.getLastname();
	}
}

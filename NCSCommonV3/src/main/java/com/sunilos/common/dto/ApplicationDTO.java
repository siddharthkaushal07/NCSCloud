package com.sunilos.common.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;

/**
 * Email/SMS Message class. Message subject and body contains place holders.
 * Place holders defined inside subject/body by defined by {0}, {1}, {3},.. etc.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_APPLICATION")
public class ApplicationDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String ACTIVE = "Active";
	public static String INACTIVE = "Inactive";

	@Column(name = "NAME", length = 50)
	private String name = null;

	@Column(name = "APPLICATION_CODE")
	private Long applicationCode;

	@Column(name = "ADDRESS", length = 500)
	private String address = null;

	@Column(name = "PHONE", length = 50)
	private String phone = null;

	@Column(name = "EMAIL", length = 100)
	private String email = null;

	@Column(name = "STATUS", length = 15)
	private String status = ACTIVE;

	@Column(name = "LOGO_ID")
	private Long logoId = null;

	@Column(name = "APP_URL")
	private String appUrl;

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(Long applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
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
		// map.put("orgCode", orgCode);
		return map;
	}

	@Override
	public String getValue() {
		return name;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public boolean isGroupFilter() {
		return false;
	}

}

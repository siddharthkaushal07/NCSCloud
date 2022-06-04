package com.sunilos.common.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * Role POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_ROLE")
public class RoleDTO extends BaseDTO {

	// ROLE ID 1 == SUPER ADMIN
	@Transient
	public static final long SUPERADMIN_ROLE_ID = 1;

	// ROLE ID 2 == HR
	@Transient
	public static final long HR_ROLE_ID = 2;

	// ROLE ID 3 == ACCOUNTANT
	@Transient
	public static final long ACCOUNTANT_ROLE_ID = 3;

	// ROLE ID 4 == STAFF
	@Transient
	public static final long STAFF_ROLE_ID = 4;

	@Column(name = "CODE", length = 20)
	private String code = null;

	@Column(name = "NAME", length = 50)
	private String name = null;

	@Column(name = "DISCRIPTION", length = 100)
	private String discription = null;

	@Column(name = "CAN_READ", length = 1)
	private String canRead = YES;

	@Column(name = "CAN_WRITE", length = 1)
	private String canWrite = YES;

	@Column(name = "CAN_UPDATE", length = 1)
	private String canUpdate = YES;

	@Column(name = "CAN_DELETE", length = 1)
	private String canDelete = YES;

	@Column(name = "STATUS", length = 15)
	private String status = ACTIVE;

	@Column(name = "APPLICATION_NAME")
	private String applicationName = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getCanRead() {
		return canRead;
	}

	public void setCanRead(String canRead) {
		this.canRead = canRead;
	}

	public String getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(String canWrite) {
		this.canWrite = canWrite;
	}

	public String getCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(String canUpdate) {
		this.canUpdate = canUpdate;
	}

	public String getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(String canDelete) {
		this.canDelete = canDelete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("applicationName", "asc");
		map.put("name", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		// map.put("name", name);
		return map;
	}

	@Override
	public boolean isGroupFilter() {
		return false;
	}
	
	
	

}

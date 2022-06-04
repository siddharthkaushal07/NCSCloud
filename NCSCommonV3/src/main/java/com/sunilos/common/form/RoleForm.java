package com.sunilos.common.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.common.dto.RoleDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class RoleForm extends BaseForm {

	@NotEmpty
	private String name = null;

	@NotEmpty
	private String discription = null;

	private String canRead = null;

	private String canWrite = null;

	private String canUpdate = null;

	private String canDelete = null;

	private String status = null;

	private String applicationName = null;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public BaseDTO getDto() {
		RoleDTO dto = initDTO(new RoleDTO());
		dto.setName(name);
		dto.setDiscription(discription);
		dto.setStatus(status);
		dto.setAppId(appId);
		dto.setCanDelete(canDelete);
		dto.setCanRead(canRead);
		dto.setCanUpdate(canUpdate);
		dto.setCanWrite(canWrite);
		dto.setApplicationName(applicationName);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdDto) {
	}
}

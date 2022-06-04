package com.sunilos.common.form;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.common.dto.JobLogDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class JobLogForm extends BaseForm {

	@NotEmpty
	private String name = null;

	protected String error = null;

	protected String status = null;

	protected Timestamp startTime = null;

	protected Timestamp endTime = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public BaseDTO getDto() {
		JobLogDTO dto = initDTO(new JobLogDTO());
		dto.setName(name);
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		dto.setStatus(status);
		dto.setError(error);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		JobLogDTO dto = (JobLogDTO) bDto;
		name = dto.getName();
		error = dto.getError();
		status = dto.getStatus();
		startTime = dto.getStartTime();
		endTime = dto.getEndTime();
	}
}

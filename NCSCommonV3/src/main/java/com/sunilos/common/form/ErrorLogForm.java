package com.sunilos.common.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.common.dto.ErrorLogDTO;

/**
 * Contains Error form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class ErrorLogForm extends BaseForm {

	@NotEmpty
	protected String message = null;

	protected String type = null;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return message;
	}

	@Override
	public BaseDTO getDto() {
		ErrorLogDTO dto = initDTO(new ErrorLogDTO());
		dto.setMessage(message);
		dto.setType(type);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		ErrorLogDTO dto = (ErrorLogDTO) bDto;
		message = dto.getMessage();
		type = dto.getType();
	}
}

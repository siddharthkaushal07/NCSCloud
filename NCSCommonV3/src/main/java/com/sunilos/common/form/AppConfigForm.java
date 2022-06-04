package com.sunilos.common.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.common.dto.AppConfigDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class AppConfigForm extends BaseForm {

	@NotEmpty
	protected String paramName = null;

	@NotEmpty
	protected String paramValue = null;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public BaseDTO getDto() {
		AppConfigDTO dto = initDTO(new AppConfigDTO());
		dto.setParamName(paramName);
		dto.setParamValue(paramValue);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		AppConfigDTO dto = (AppConfigDTO) bDto;
		paramName = dto.getParamName();
		paramValue = dto.getParamValue();
	}
}

package com.sunilos.common.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.common.dto.AppJobDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class AppJobForm extends BaseForm {

	@NotEmpty
	private String name = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BaseDTO getDto() {
		AppJobDTO dto = initDTO(new AppJobDTO());
		dto.setName(name);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		AppJobDTO dto = (AppJobDTO) bDto;
		name = dto.getName();	
	}
}

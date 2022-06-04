package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ComplainDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class ComplainForm extends BaseForm {

	@NotEmpty
	private String name = null;
	private String subject = null;
	private String description = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BaseDTO getDto() {
		ComplainDTO dto = initDTO(new ComplainDTO());
		dto.setName(name);
		dto.setSubject(subject);
		dto.setDescription(description);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		ComplainDTO dto = (ComplainDTO) bDto;
		name = dto.getName();	
	}
}

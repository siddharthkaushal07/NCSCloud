package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.SupplierDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class SupplierForm extends BaseForm {

	@NotEmpty
	private String name = null;
	private String lastname = null;
	
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

	@Override
	public BaseDTO getDto() {
		SupplierDTO dto = initDTO(new SupplierDTO());
		dto.setName(name);
		dto.setLastname(lastname);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		SupplierDTO dto = (SupplierDTO) bDto;
		name = dto.getName();	
	}
}

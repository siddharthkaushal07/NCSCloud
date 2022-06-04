package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.ecom.dto.GSTRegistrationDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class GSTRegistrationForm extends BaseForm {

	
	@NotEmpty
	private String gstNo;
	
	
	
	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	@Override
	public BaseDTO getDto() {
		GSTRegistrationDTO dto = initDTO(new GSTRegistrationDTO());
		dto.setGstNo(gstNo);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		GSTRegistrationDTO dto = (GSTRegistrationDTO) bDto;
		gstNo = dto.getGstNo();	
	}
}

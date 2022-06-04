package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.TaxDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class TaxForm extends BaseForm {

	@NotEmpty
	private String name = null;
	private Integer rate = 0;

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BaseDTO getDto() {
		TaxDTO dto = initDTO(new TaxDTO());
		dto.setName(name);
		dto.setRate(rate);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		TaxDTO dto = (TaxDTO) bDto;
		name = dto.getName();
		rate = dto.getRate();
	}
}

package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CategoryDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class CategoryForm extends BaseForm {

	@NotEmpty
	private String name = null;
	private Integer domain_Id = null;
	private String domain_name = null;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getDomain_Id() {
		return domain_Id;
	}

	public void setDomain_Id(Integer domain_Id) {
		this.domain_Id = domain_Id;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	@Override
	public BaseDTO getDto() {
		CategoryDTO dto = initDTO(new CategoryDTO());
		dto.setName(name);
		dto.setDomain_Id(domain_Id);
		dto.setDomain_name(domain_name);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		CategoryDTO dto = (CategoryDTO) bDto;
		name = dto.getName();
	}
}

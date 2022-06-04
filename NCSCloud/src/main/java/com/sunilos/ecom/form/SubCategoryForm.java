package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.SubCategoryDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class SubCategoryForm extends BaseForm {

	@NotEmpty
	private String name = null;
	private Integer category_id = null;
	private String category_name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Override
	public BaseDTO getDto() {
		SubCategoryDTO dto = initDTO(new SubCategoryDTO());
		dto.setName(name);
		dto.setCategory_id(category_id);
		dto.setCategory_name(category_name);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		SubCategoryDTO dto = (SubCategoryDTO) bDto;
		name = dto.getName();
	}
}

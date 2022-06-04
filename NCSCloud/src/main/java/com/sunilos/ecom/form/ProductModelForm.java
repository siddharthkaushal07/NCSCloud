package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ProductModelDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class ProductModelForm extends BaseForm {

	
	private String name = null;
	private Integer model_year = 0;
	private String model_no = null;
	private Integer product_id = 0;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getModel_year() {
		return model_year;
	}

	public void setModel_year(Integer model_year) {
		this.model_year = model_year;
	}

	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}

	
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	@Override
	public BaseDTO getDto() {
		ProductModelDTO dto = initDTO(new ProductModelDTO());
		dto.setName(name);
		dto.setModel_no(model_no);
		dto.setModel_year(model_year);
		dto.setProduct_id(product_id);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		ProductModelDTO dto = (ProductModelDTO) bDto;
		name = dto.getName();	
	}
}

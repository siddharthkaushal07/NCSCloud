	package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.CatalogueDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class CatalogueForm extends BaseForm {

	
	private String name = null;
	private Long image_id;
	private Long product_id;
	private String description = null;
	private Integer category_id = 0;
	private Integer subcategory_id = 0;
	private String category_name = null;
	private String subcategory_name = null;
	private String specification = null;
	private Integer quantity = 0;

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Long getImage_id() {
		return image_id;
	}

	public void setImage_id(Long image_id) {
		this.image_id = image_id;
	}

	

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(Integer subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getSubcategory_name() {
		return subcategory_name;
	}

	public void setSubcategory_name(String subcategory_name) {
		this.subcategory_name = subcategory_name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public BaseDTO getDto() {
		CatalogueDTO dto = initDTO(new CatalogueDTO());
		dto.setName(name);
		dto.setImage_id(image_id);
		dto.setProduct_id(product_id);
		dto.setDescription(description);
		dto.setCategory_id(category_id);
		dto.setSubcategory_id(subcategory_id);
		dto.setSpecification(specification);
		dto.setCategory_name(category_name);
		dto.setSubcategory_name(subcategory_name);
		dto.setQuantity(quantity);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		CatalogueDTO dto = (CatalogueDTO) bDto;
		name = dto.getName();	
	}
}

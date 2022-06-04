package com.sunilos.ecom.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * ProductModel POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_PRODUCTMODEL")
public class ProductModelDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	protected String name = null;
	
	@Column(name = "MODEL_NO", length = 50)
	protected String model_no = null;
	
	@Column(name = "MODEL_YEAR", length = 50)
	protected Integer model_year = 0;
	
	@Column(name = "PRODUCT_ID", length = 50)
	protected Integer product_id = 0;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}

	public Integer getModel_year() {
		return model_year;
	}

	public void setModel_year(Integer model_year) {
		this.model_year = model_year;
	}

	
	
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getValue() {
		return name;
	}
	
	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("name", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", name);
		return map;
	}	

}



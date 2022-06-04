package com.sunilos.ecom.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * CategoryManagement POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_CATEGORYMANAGEMENT")
public class CategoryDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	protected String name = null;
	
	@Column(name = "DOMAIN_ID", length = 50)
	protected Integer domain_Id = null;
	
	@Column(name = "DOMAIN_NAME", length = 50)
	protected String domain_name = null;
	
	

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

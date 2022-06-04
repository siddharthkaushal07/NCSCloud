package com.sunilos.common.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;

/**
 * AppConfig POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_APP_CONFIG")
public class AppConfigDTO extends BaseDTO {

	@Column(name = "PARAM_NAME", length = 100)
	protected String paramName = null;

	@Column(name = "PARAM_VALUE", length = 500)
	protected String paramValue = null;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("paramName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("paramName", paramName);
		return map;
	}

	@Override
	public String getValue() {
		return paramName;
	}

	@Override
	public boolean isGroupFilter() {
		return false;
	}
	
	

}

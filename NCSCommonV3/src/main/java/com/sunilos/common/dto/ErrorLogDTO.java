package com.sunilos.common.dto;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;

/**
 * ErrorLog POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_ERRORLOG")
public class ErrorLogDTO extends BaseDTO {

	public static final String DEBUG = "DEBUG";
	public static final String INFO = "INFO";
	public static final String WARN = "WARN";
	public static final String ERROR = "ERROR";
	public static final String FATAL = "FATAL";

	public ErrorLogDTO() {
	}

	public ErrorLogDTO(String m) {
		type = INFO;
		message = m;
	}

	public ErrorLogDTO(String type, String m) {
		this.type = type;
		message = m;
	}

	public ErrorLogDTO(Exception e) {
		type = ERROR;
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		message = errors.toString();
	}

	@Column(name = "MESSAGE", length = 5000)
	protected String message = null;

	@Column(name = "TYPE", length = 10)
	protected String type = null;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return message;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("id", "desc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		// map.put("name", name);
		return map;
	}

}

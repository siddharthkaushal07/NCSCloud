package com.sunilos.common.dto;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * AppJob POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_APPJOB")
public class AppJobDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	protected String name = null;

	@Column(name = "SCHEDULE", length = 50)
	protected String schdeule = null;

	@Column(name = "LAST_RUN")
	protected Timestamp lastRun = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchdeule() {
		return schdeule;
	}

	public void setSchdeule(String schdeule) {
		this.schdeule = schdeule;
	}

	public String getValue() {
		return name;
	}

	public Timestamp getLastRun() {
		return lastRun;
	}

	public void setLastRun(Timestamp lastRun) {
		this.lastRun = lastRun;
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

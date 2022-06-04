package com.sunilos.common.dto;

import java.util.HashMap;

public class EmailParams {

	public static final String AUTH_KEY = "authKey";
	public static final String EMAIL_KEY = "emailKey";
	public static final String TEMPLATE_KEY = "templateKey";
	public static final String TO = "to";
	public static final String CC = "cc";
	public static final String SUBJECT = "subject";
	public static final String BODY = "body";
	public static final String TYPE = "type";
	public static final String HTML = "html";

	HashMap<String, String> params = new HashMap<String, String>();

	public void add(String key, Object value) {
		params.put(key, value + "");
	}

	public void add(String key, String value) {
		params.put(key, value);
	}

	public void remove(String key) {
		params.remove(key);
	}

	public String get(String key) {
		return params.get(key);
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	

}

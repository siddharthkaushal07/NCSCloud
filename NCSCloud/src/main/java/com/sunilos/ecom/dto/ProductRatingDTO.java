package com.sunilos.ecom.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * ProductRating POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_PRODUCTRATING")
public class ProductRatingDTO extends BaseDTO {

	@Column(name = "RATING")
	protected Integer rating = 0;

	@Column(name = "COMMENT", length = 1000)
	protected String comment = null;

	@Column(name = "NAME", length = 100)
	protected String name = null;

	@Column(name = "EMAILID", length = 100)
	protected String emailId = null;


	@Column(name = "IP_ADDRESS", length = 20)
	protected String ip_address = null;

	@Column(name = "PRODUCT_ID", length = 20)
	protected Integer product_id = 0;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
		public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
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



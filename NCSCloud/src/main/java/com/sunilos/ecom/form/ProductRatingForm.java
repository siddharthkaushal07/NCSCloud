package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ProductRatingDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class ProductRatingForm extends BaseForm {

	
	private Integer rating = 0;
	private String comment = null;
	private String name = null;
	private String emailId = null;
	private String ip_address = null;
	private Integer product_id = 0;
	
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

	@Override
	public BaseDTO getDto() {
		ProductRatingDTO dto = initDTO(new ProductRatingDTO());
		dto.setRating(rating);
		dto.setName(name);
		dto.setComment(comment);
		dto.setEmailId(emailId);
		dto.setIp_address(ip_address);
		dto.setProduct_id(product_id);
		
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		ProductRatingDTO dto = (ProductRatingDTO) bDto;
		name = dto.getName();	
	}
}

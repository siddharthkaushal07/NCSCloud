package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.OfferDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class OfferForm extends BaseForm {

	private String name = null;
	private Integer price = 0;
	private Integer discount = 0;
	protected Long productId = null;

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BaseDTO getDto() {
		OfferDTO dto = initDTO(new OfferDTO());
		dto.setName(name);
		dto.setPrice(price);
		dto.setDiscount(discount);
		dto.setProductId(productId);

		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		OfferDTO dto = (OfferDTO) bDto;
		name = dto.getName();
	}
}

package com.sunilos.ecom.form;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.ecom.dto.WishListDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class WishListForm extends BaseForm {

//	@NotEmpty
	private String name = null;
	private String productName = null;
	private String model_no = null;
	private Long image_id;
	private Integer price;
	private Integer gst;
	private Integer quantity;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}

	public Long getImage_id() {
		return image_id;
	}

	public void setImage_id(Long image_id) {
		this.image_id = image_id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getGst() {
		return gst;
	}

	public void setGst(Integer gst) {
		this.gst = gst;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public BaseDTO getDto() {
		WishListDTO dto=initDTO(new WishListDTO());
		dto.setId(id);
		System.out.println("Id..............................................." + dto.getId());
		dto.setName(name);
		dto.setProductName(productName);
		System.out.println("Name..............................................." + dto.getProductName());
		dto.setPrice(price);
		dto.setGst(gst);
		System.out.println("Price..............................................." + dto.getPrice());
		dto.setQuantity(quantity);
		dto.setImage_id(image_id);
		System.out.println("imageId..............................................." + dto.getImage_id());
		dto.setModel_no(model_no);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		WishListDTO dto = (WishListDTO) bDto;
		name = dto.getName();	
	}
}

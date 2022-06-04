package com.sunilos.ecom.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * ShoppingCart POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_SHOPPINGCART")
public class ShoppingCartDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	protected String name = null;

	@Column(name = "PRODUCTNAME", length = 50)
	protected String productName = null;
	
	@Column(name = "MODEL_NO", length = 50)
	protected String model_no = null;
	
	
	@Column(name = "PRICE", length = 50)
	protected Integer price = 0;
	
	@Column(name = "GST", length = 50)
	protected Integer gst = 0;

	@Column(name = "QUANTITY", length = 50)
	protected Integer Quantity = 0;
	
	@Column(name = "IMAGE_ID")
	protected Long image_id;
	
	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return productName;
	}

	public Integer getPrice() {
		return price;
	}

	
	
	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	
	public Long getImage_id() {
		return image_id;
	}

	public void setImage_id(Long image_id) {
		this.image_id = image_id;
	}
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getGst() {
		return gst;
	}

	public void setGst(Integer gst) {
		this.gst = gst;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("productName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("productName", productName);
		return map;
	}
}

package com.sunilos.ecom.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * ProductPrice POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_PRODUCTPRICE")
public class ProductPriceDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	protected String name = null;
	
	@Column(name = "MRP", length = 50)
	protected Double mrp = 0.0;

	@Column(name = "DISCOUNT", length = 50)
	protected Integer discount = 0;

	@Column(name = "PRICE", length = 50)
	protected Integer price = 0;

	@Column(name = "GST", length = 50)
	protected Integer gst = 0;
	

	@Column(name = "PRODUCT_ID", length = 50)
	protected Integer product_id = 0;
	
	@Column(name = "START_DATE", length = 50)
	protected Date start_date = null;
	
	@Column(name = "END_DATE", length = 50)
	protected Date end_date = null;
	
	@Column(name = "MODEL_ID", length = 50)
	protected Integer model_id = 0;
	
	@Column(name = "MODEL_NO", length = 50)
	protected String model_no = null;
	
//	@Column(name = "MODEL_NAME", length = 50)
//	protected String model_name = null;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
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
	
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
		public Integer getModel_id() {
		return model_id;
	}

	public void setModel_id(Integer model_id) {
		this.model_id = model_id;
	}
	
	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	
	

//	public String getModel_name() {
//		return model_name;
//	}
//
//	public void setModel_name(String model_name) {
//		this.model_name = model_name;
//	}

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



package com.sunilos.ecom.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ProductPriceDTO;


/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class ProductPriceForm extends BaseForm {


	private String name = null;
	private Double mrp = 0.0;
	private Integer discount = 0;
	private Integer price = 0;
	private Integer gst = 0;
	private Integer product_id = 0;
	private Date start_date = null;
	private Date end_date = null;
	private Integer model_id = 0;
	private String model_no = null;
	private String model_name = null;
	
	
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

	@Override
	public BaseDTO getDto() {
		ProductPriceDTO dto = initDTO(new ProductPriceDTO());
		dto.setName(name);
		dto.setMrp(mrp);
		dto.setDiscount(discount);
		dto.setPrice(price);
		dto.setGst(gst);
		dto.setProduct_id(product_id);
		dto.setStart_date(start_date);
		dto.setEnd_date(end_date);
		dto.setModel_id(model_id);
		dto.setModel_no(model_no);
	//dto.setModel_name(model_name);
		return dto;		
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		ProductPriceDTO dto = (ProductPriceDTO) bDto;
		name = dto.getName();	
	}
}

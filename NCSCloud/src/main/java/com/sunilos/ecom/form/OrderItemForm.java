package com.sunilos.ecom.form;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.ecom.dto.OrderItemDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class OrderItemForm extends BaseForm {

	private static final String DATE_FORMATTER = "dd-MM-yyyy";

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

	//protected long total_price;
	
	public long quantity;
	
	protected String productName;

	protected String modelNo;

	protected long price;

	protected Timestamp order_date;

//protected long no_of_items;

	protected String delivery_date;

	protected long billing_Address_Id;

	protected long shipping_Address_Id;

	private Integer gst;

	protected String payment_mode;

	protected long order_id;

	protected long image_id;
	
	//protected long total_quantity;
	
	
//OrderNoDTO orderNo;

	
	
	
	public String getProductName() {
		return productName;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public long getBilling_Address_Id() {
		return billing_Address_Id;
	}

	public void setBilling_Address_Id(long billing_Address_Id) {
		this.billing_Address_Id = billing_Address_Id;
	}

	public long getShipping_Address_Id() {
		return shipping_Address_Id;
	}

	public void setShipping_Address_Id(long shipping_Address_Id) {
		this.shipping_Address_Id = shipping_Address_Id;
	}

	public Integer getGst() {
		return gst;
	}

	public void setGst(Integer gst) {
		this.gst = gst;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public long getImage_id() {
		return image_id;
	}

	public void setImage_id(long image_id) {
		this.image_id = image_id;
	}


	
	
	
	

	@Override
	public BaseDTO getDto() {
		OrderItemDTO dto = initDTO(new OrderItemDTO());

		dto.setProductName(productName);
		dto.setModelNo(modelNo);

		dto.setOrder_date(new Timestamp(new Date().getTime()));

		dto.setPayment_mode(payment_mode);

		LocalDateTime ldt = LocalDateTime.now().plusDays(7);
		String localDateFormate = ldt.format(formatter);
		dto.setDelivery_date(localDateFormate);

		dto.setBilling_Address_Id(billing_Address_Id);
		dto.setShipping_Address_Id(shipping_Address_Id);

		dto.setGst(gst);
		dto.setImage_id(image_id);
		dto.setOrder_id(order_id);
		dto.setPrice(price);
		//dto.setTotal_price(total_price);
		dto.setQuantity(quantity);
		//dto.setTotal_quantity(total_quantity);
		
		return dto;
	}
	
//	@Override
//	public BaseForm getForm2() {
//		// TODO Auto-generated method stub
//		
//		OrderItemForm form=new OrderItemForm();
//		
//		form.setTotal_quantity(total_quantity);
//		
//		return form;
//	}

		@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		OrderItemDTO dto = (OrderItemDTO) bDto;
		
	}
}

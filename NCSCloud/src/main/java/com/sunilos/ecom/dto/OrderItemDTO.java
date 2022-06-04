package com.sunilos.ecom.dto;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * OrderItem POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_ORDERITEM")
public class OrderItemDTO extends BaseDTO {

	
	
	
//	@Column(name = "TOTAL_PRICE")
//	protected long total_price;
//	
//	@Column(name = "TOTAL_QUANTITY")
//	protected long total_quantity;

	
	
	@Column(name = "PRICE", length = 50)
	protected long price;
	
	@Column(name = "PRODUCT_NAME", length = 50)
	protected String productName;
	
	@Column(name = "MODEL_NO", length = 50)
	protected String modelNo;
	
	@Column(name = "ORDER_DATE", length = 50)
	protected Timestamp order_date;
	
//	@Column(name = "NO_OF_ITEMS", length = 50)
//	protected long no_of_items;
	
	@Column(name = "DELIVERY_DATE", length = 50)
	protected String delivery_date;
	
	@Column(name = "BILLING_ADDRESS_ID", length = 50)
	protected long billing_Address_Id;
	
	@Column(name = "SHIPPING_ADDRESS_ID", length = 50)
	protected long shipping_Address_Id;
	
	@Column(name = "QUANTITY", length = 50)
	public long quantity;
	
	
	
	
	@Column(name = "GST", length = 50)
	protected Integer gst = 0;

		
	@Column(name = "PAYMENT_MODE")
	protected String payment_mode;
	
	@Column(name = "IMAGE_ID")
	protected long image_id;
	
	@Column(name = "ORDER_ID")
	protected long order_id;
	
	
	
	//////////////////
	
	
	
	
	
	

	
	public long getQuantity() {
		return quantity;
	}

	
	

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	
	
		public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
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

	public long getImage_id() {
		return image_id;
	}

	public void setImage_id(long image_id) {
		this.image_id = image_id;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getValue() {
		return order_id + "";
	}
	
	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("order_id", "desc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("order_id", null);
		return map;
	}	

}



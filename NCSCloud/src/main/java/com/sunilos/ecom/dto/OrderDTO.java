package com.sunilos.ecom.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunilos.common.BaseDTO;

/**
 * OrderManagement POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_ORDERMANAGEMENT")
public class OrderDTO extends BaseDTO {
	
		
//	@ManyToOne
//	@Column(name = "ORDER_NO")
//	protected OrderNoDTO orderNo;
	
	
//	public OrderNoDTO getOrderNo() {
//		return orderNo;
//	}
//
//	public void setOrderNo(OrderNoDTO orderNo) {
//		this.orderNo = orderNo;
//	}
	

	
	@Column(name = "ORDER_ID", length = 50)
	protected long order_id;
	@Column(name = "TOTAL_PRICE", length = 50)
	public Long total_price;
	@Column(name = "QUANTITY", length = 50)
	public long quantity;
	@Column(name = "DISCOUNT", length = 50)
	public String discount;
	@Column(name = "DESCRIPTION", length = 50)
	public String description;

	@Column(name = "ORDER_DATE", length = 50)
	protected Timestamp order_date;

	@Column(name = "DELIVERY_DATE", length = 50)
	protected String delivery_date;
	
	@Column(name = "BILLING_ADDRESS_ID", length = 50)
	protected long billing_Address_Id;
	
	@Column(name = "SHIPPING_ADDRESS_ID", length = 50)
	protected long shipping_Address_Id;

	
		
	
	
	
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

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		map.put("order_id", order_id);
		return map;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return order_id+"";
	}

}

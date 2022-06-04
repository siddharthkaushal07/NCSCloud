package com.sunilos.ecom.form;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.ecom.dto.OrderDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
// * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class OrderForm extends BaseForm {

	
	
	private static final String DATE_FORMATTER = "dd-MM-yyyy";

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

	
	

//	public OrderNoDTO getOrderNo() {
//		return orderNo;
//	}
//
//	public void setOrderNo(OrderNoDTO orderNo) {
//		this.orderNo = orderNo;
//	}
	
	

protected long order_id;
	
	public Long total_price;
	
	public long quantity;
	
	public String discount;
	
	public String description;
	
	protected Timestamp order_date;

		protected String delivery_date;
		
		protected long billing_Address_Id;

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
	public BaseDTO getDto() {
		OrderDTO dto = initDTO(new OrderDTO());
		dto.setOrder_id(order_id);
		dto.setTotal_price(total_price);
		dto.setQuantity(quantity);
		dto.setDiscount(discount);
		dto.setDescription(description);
		LocalDateTime ldt = LocalDateTime.now().plusDays(7);
		String localDateFormate = ldt.format(formatter);
		dto.setDelivery_date(localDateFormate);
		
		dto.setOrder_date(new Timestamp(new Date().getTime()));
		dto.setBilling_Address_Id(billing_Address_Id);
		dto.setShipping_Address_Id(shipping_Address_Id);
		
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		OrderDTO dto = (OrderDTO) bDto;

	}
}

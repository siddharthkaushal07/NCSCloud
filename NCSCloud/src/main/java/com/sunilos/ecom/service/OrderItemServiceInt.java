package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.dto.OrderItemDTO;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface OrderItemServiceInt extends BaseServiceInt<OrderItemDTO> {

	/**
	 * Finds OrderItem by name.
	 * 
	 * @param name
	 * @return
	 */
	public OrderItemDTO findByName(String name, UserContext userContext);

	
	public long placeOrder(OrderItemDTO dto, UserContext userContext);

	public List<OrderItemDTO> getOrderNo();
}

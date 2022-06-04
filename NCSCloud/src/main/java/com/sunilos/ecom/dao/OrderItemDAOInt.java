package com.sunilos.ecom.dao;
import java.util.List;

import com.sunilos.common.BaseDAOInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.dto.OrderItemDTO;


/**
 * OrderItem DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface OrderItemDAOInt extends BaseDAOInt<OrderItemDTO> {

	
	public long placeOrder(OrderItemDTO dto, UserContext userContext);

	 public List<OrderItemDTO> getOrderNo();
}

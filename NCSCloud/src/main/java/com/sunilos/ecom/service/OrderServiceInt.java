package com.sunilos.ecom.service;

import java.util.List;

import javax.persistence.TypedQuery;

import com.sunilos.common.*;
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

public interface OrderServiceInt extends BaseServiceInt<OrderDTO> {

	/**
	 * Finds OrderManagement by name.
	 * 
	 * @param name
	 * @return
	 */
	
	
	public long addOrder(OrderDTO dto, UserContext userContext);
	public OrderDTO findByName(String name, UserContext userContext);

	
	 public TypedQuery<OrderDTO> getMaxOrder();
	 
	 public  String findHighestSalary();
	 
	 
}

package com.sunilos.ecom.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.Query;

import com.sunilos.common.BaseDAOInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.dto.OrderItemDTO;

/**
 * OrderManagement DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface OrderDAOInt extends BaseDAOInt<OrderDTO> {


	
	public long addOrder(OrderDTO dto, UserContext userContext);

	
	@Query(value = "SELECT max(order_no) FROM NCS_ORDERMANAGEMENT")
	public int max();
	
	 public TypedQuery<OrderDTO> getMaxOrder();
	 public  String findHighestSalary();
	 
	

}

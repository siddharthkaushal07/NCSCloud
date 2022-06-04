package com.sunilos.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dao.OrderItemDAOInt;
import com.sunilos.ecom.dto.OrderItemDTO;


/**
 * Session facade of Role Service. It is transactional, apply declarative
 * transactions with help of Spring AOP.
 * 
 * If unchecked exception is propagated from a method then transaction is rolled
 * back.
 * 
 * Default propagation value is Propagation.REQUIRED and readOnly = false
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service
@Transactional
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemDTO, OrderItemDAOInt> implements OrderItemServiceInt {

	private static Logger log = LoggerFactory.getLogger(OrderItemServiceImpl.class);

	@Autowired
	OrderItemDAOInt orderItemDaoInt;
		
	@Transactional(readOnly = true)
	public OrderItemDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}

	@Override
	@Transactional(readOnly = false)
	public long placeOrder(OrderItemDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		long pk = orderItemDaoInt.placeOrder(dto, userContext);
		
		return pk;
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<OrderItemDTO> getOrderNo() {


	return orderItemDaoInt.getOrderNo();
	}

}

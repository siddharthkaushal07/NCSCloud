package com.sunilos.ecom.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.*;
//import com.sunilos.exception.DuplicateRecordException;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dao.OrderDAOImpl;
import com.sunilos.ecom.dao.OrderDAOInt;
import com.sunilos.ecom.dto.OrderDTO;


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
public class OrderServiceImpl extends BaseServiceImpl<OrderDTO, OrderDAOInt> implements OrderServiceInt {

	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderDAOInt orderDaoInt;
	
	
	
	
	@Transactional(readOnly = true)
	public OrderDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}

	
	
	
	
	public int max() {
			return baseDao.max();
	}


	@Override
	public TypedQuery<OrderDTO> getMaxOrder() {
		// TODO Auto-generated method stub
		return orderDaoInt.getMaxOrder();
	}
	
	
	
	

	@Override
	public String findHighestSalary() {
		
		System.out.println("inside service order highsalary------------------------");
		return orderDaoInt.findHighestSalary();
		
	}





	@Override
	public long addOrder(OrderDTO dto, UserContext userContext) {
				long pk = orderDaoInt.addOrder (dto, userContext);
		
		return pk;
	}


	
	

}

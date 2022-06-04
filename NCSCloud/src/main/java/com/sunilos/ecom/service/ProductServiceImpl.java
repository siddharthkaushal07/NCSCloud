package com.sunilos.ecom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dao.ProductDAOInt;
import com.sunilos.ecom.dto.ProductDTO;

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
public class ProductServiceImpl extends BaseServiceImpl<ProductDTO, ProductDAOInt> implements ProductServiceInt {

	private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Transactional(readOnly = true)
	public ProductDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);

	}

	@Override
	public ProductDTO findByDescription(String description, UserContext userContext) {
		return baseDao.findByUniqueKey("description", description, userContext);

	}

	
	
	
}

package com.sunilos.ecom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dao.SellerRegistrationDAOInt;
import com.sunilos.ecom.dto.SellerRegistrationDTO;


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
public class SellerRegistrationServiceImpl extends BaseServiceImpl<SellerRegistrationDTO, SellerRegistrationDAOInt> implements SellerRegistrationServiceInt {

	private static Logger log = LoggerFactory.getLogger(SellerRegistrationServiceImpl.class);

	@Transactional(readOnly = true)
	public SellerRegistrationDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}

	@Override
	public SellerRegistrationDTO authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}

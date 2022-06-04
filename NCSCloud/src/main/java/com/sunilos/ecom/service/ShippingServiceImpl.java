package com.sunilos.ecom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sunilos.common.*;
import com.sunilos.ecom.dao.ShippingDAOInt;
import com.sunilos.ecom.dto.ShippingDTO;
import com.sunilos.common.UserContext;


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
public class ShippingServiceImpl extends BaseServiceImpl<ShippingDTO, ShippingDAOInt> implements ShippingServiceInt {

	private static Logger log = LoggerFactory.getLogger(ShippingServiceImpl.class);

	@Transactional(readOnly = true)
	public ShippingDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}

}

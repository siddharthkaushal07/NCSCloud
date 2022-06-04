package com.sunilos.ecom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dao.GSTRegistrationDAOInt;
import com.sunilos.ecom.dto.GSTRegistrationDTO;


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
public class GSTRegistrationServiceImpl extends BaseServiceImpl<GSTRegistrationDTO, GSTRegistrationDAOInt> implements GSTRegistrationServiceInt {

	private static Logger log = LoggerFactory.getLogger(GSTRegistrationServiceImpl.class);

	@Transactional(readOnly = true)
	public GSTRegistrationDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}

}

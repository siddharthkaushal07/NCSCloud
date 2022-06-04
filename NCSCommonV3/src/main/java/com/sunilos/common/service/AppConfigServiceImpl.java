package com.sunilos.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.common.UserContext;
import com.sunilos.common.dao.AppConfigDAOInt;
import com.sunilos.common.dto.AppConfigDTO;
import com.sunilos.common.dto.ErrorLogDTO;

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
public class AppConfigServiceImpl extends BaseServiceImpl<AppConfigDTO, AppConfigDAOInt>
		implements AppConfigServiceInt {

	private static Logger log = LoggerFactory.getLogger(AppConfigServiceImpl.class);
	
	@Autowired
	ErrorLogServiceInt erroLog =null;

	@Transactional(readOnly = true)
	public AppConfigDTO findByParamName(String paramName, UserContext userContext) {
		return baseDao.findByUniqueKey("paramName", paramName, userContext);
	}

	public String getValue(String paramName, UserContext userContext) {
		String val = null;
		AppConfigDTO dto = baseDao.findByUniqueKey("paramName", paramName, userContext);
		if (dto != null) {
			val = dto.getParamValue();
		}else{
			val = paramName + " does not exist";
			ErrorLogDTO errorDto = new ErrorLogDTO();
			errorDto.setAppId(userContext.getAppId());
			errorDto.setType(ErrorLogDTO.WARN);
			errorDto.setMessage(val);
			erroLog.add(errorDto, userContext);
		}
		return val;
	}

}

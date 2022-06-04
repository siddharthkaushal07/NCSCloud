package com.sunilos.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.common.UserContext;
import com.sunilos.common.dao.ErrorLogDAOInt;
import com.sunilos.common.dto.ErrorLogDTO;

/**
 * Logs messages in the database
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service
@Transactional
public class ErrorLogServiceImpl extends BaseServiceImpl<ErrorLogDTO, ErrorLogDAOInt> implements ErrorLogServiceInt {

	private static Logger log = LoggerFactory.getLogger(ErrorLogServiceImpl.class);

	public void log(ErrorLogDTO dto, UserContext userContext) {
		add(dto, userContext);
	}

	public void error(Exception e, UserContext userContext) {
		ErrorLogDTO dto = new ErrorLogDTO(e);
		add(dto, userContext);
	}

	public void error(String msg, Exception e, UserContext userContext) {
		ErrorLogDTO dto = new ErrorLogDTO(e);
		dto.setMessage(msg + "\n" + dto.getMessage());
		add(dto, userContext);
	}

	public void info(String msg, UserContext userContext) {
		ErrorLogDTO dto = new ErrorLogDTO(ErrorLogDTO.INFO, msg);
		add(dto, userContext);
	}

	public void warn(String msg, UserContext userContext) {
		ErrorLogDTO dto = new ErrorLogDTO(ErrorLogDTO.WARN, msg);
		add(dto, userContext);
	}

	public void fatat(String msg, UserContext userContext) {
		ErrorLogDTO dto = new ErrorLogDTO(ErrorLogDTO.FATAL, msg);
		add(dto, userContext);
	}
}

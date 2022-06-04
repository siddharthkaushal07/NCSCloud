package com.sunilos.common.service;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.common.dto.ErrorLogDTO;

/**
 * Log the application errors in database table
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface ErrorLogServiceInt extends BaseServiceInt<ErrorLogDTO> {
	public void log(ErrorLogDTO dto, UserContext userContext);

	public void error(Exception e, UserContext userContext);

	public void error(String msg, Exception e, UserContext userContext);

	public void info(String msg, UserContext userContext);

	public void warn(String msg, UserContext userContext);

	public void fatat(String msg, UserContext userContext);

}

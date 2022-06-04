package com.sunilos.common.service;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.common.dto.AppConfigDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface AppConfigServiceInt extends BaseServiceInt<AppConfigDTO> {

	/**
	 * Finds AppConfig by name.
	 * 
	 * @param name
	 * @return
	 */
	public AppConfigDTO findByParamName(String paramName, UserContext userContext);

	public String getValue(String paramName, UserContext userContext);
}

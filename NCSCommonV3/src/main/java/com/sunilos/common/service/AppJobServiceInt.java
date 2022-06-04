package com.sunilos.common.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.common.dto.AppJobDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface AppJobServiceInt extends BaseServiceInt<AppJobDTO> {

	/**
	 * Finds AppJob by name.
	 * 
	 * @param name
	 * @return
	 */
	public AppJobDTO findByName(String name, UserContext userContext);

}

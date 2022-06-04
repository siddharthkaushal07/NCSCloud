package com.sunilos.common.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.common.dto.JobLogDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface JobLogServiceInt extends BaseServiceInt<JobLogDTO> {

	/**
	 * Finds JobLog by name.
	 * 
	 * @param name
	 * @return
	 */
	public JobLogDTO findByName(String name, UserContext userContext);

}

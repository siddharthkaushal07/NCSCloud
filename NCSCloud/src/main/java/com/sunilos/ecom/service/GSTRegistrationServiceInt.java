package com.sunilos.ecom.service;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.GSTRegistrationDTO;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface GSTRegistrationServiceInt extends BaseServiceInt<GSTRegistrationDTO> {

	/**
	 * Finds GSTRegistration by name.
	 * 
	 * @param name
	 * @return
	 */
	public GSTRegistrationDTO findByName(String name, UserContext userContext);

}

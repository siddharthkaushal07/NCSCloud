package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.DomainDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface DomainServiceInt extends BaseServiceInt<DomainDTO> {

	/**
	 * Finds Domain by name.
	 * 
	 * @param name
	 * @return
	 */
	public DomainDTO findByName(String name, UserContext userContext);

}

package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.TaxDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface TaxServiceInt extends BaseServiceInt<TaxDTO> {

	/**
	 * Finds TaxManagement by name.
	 * 
	 * @param name
	 * @return
	 */
	public TaxDTO findByName(String name, UserContext userContext);

}

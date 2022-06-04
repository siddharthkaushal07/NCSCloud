package com.sunilos.ecom.service;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.SellerRegistrationDTO;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface SellerRegistrationServiceInt extends BaseServiceInt<SellerRegistrationDTO> {

	/**
	 * Finds SellerRegistration by name.
	 * 
	 * @param name
	 * @return
	 */
	public SellerRegistrationDTO findByName(String name, UserContext userContext);

	public SellerRegistrationDTO authenticate(String email, String password);
}

package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.OfferDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface OfferServiceInt extends BaseServiceInt<OfferDTO> {

	/**
	 * Finds OfferManagement by name.
	 * 
	 * @param name
	 * @return
	 */
	public OfferDTO findByName(String name, UserContext userContext);

}

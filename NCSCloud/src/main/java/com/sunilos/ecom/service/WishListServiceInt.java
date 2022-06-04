package com.sunilos.ecom.service;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.WishListDTO;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface WishListServiceInt extends BaseServiceInt<WishListDTO> {

	/**
	 * Finds WishList by name.
	 * 
	 * @param name
	 * @return
	 */
	public WishListDTO findByName(String name, UserContext userContext);

}

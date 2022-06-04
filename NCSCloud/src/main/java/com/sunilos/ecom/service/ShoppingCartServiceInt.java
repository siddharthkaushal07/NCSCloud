package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.ecom.dto.ShoppingCartDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface ShoppingCartServiceInt extends BaseServiceInt<ShoppingCartDTO> {

	/**
	 * Finds ShoppingCart by name.
	 * 
	 * @param name
	 * @return
	 */
	public ShoppingCartDTO findByName(String name, UserContext userContext);
	
	

}

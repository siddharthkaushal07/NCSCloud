package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.ProductDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface ProductServiceInt extends BaseServiceInt<ProductDTO> {

	/**
	 * Finds ProductManagement by name.
	 * 
	 * @param name
	 * @return
	 */
	public ProductDTO findByName(String name, UserContext userContext);

	ProductDTO findByDescription(String description, UserContext userContext);

}

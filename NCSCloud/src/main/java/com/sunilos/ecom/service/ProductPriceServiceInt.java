package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface ProductPriceServiceInt extends BaseServiceInt<ProductPriceDTO> {

	/**
	 * Finds ProductPrice by name.
	 * 
	 * @param name
	 * @return
	 */
	public ProductPriceDTO findByName(String name, UserContext userContext);
	
	public ProductPriceDTO findByPId(long pk, UserContext userContext);

}

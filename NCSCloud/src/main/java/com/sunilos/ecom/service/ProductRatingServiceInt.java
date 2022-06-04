package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ProductRatingDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface ProductRatingServiceInt extends BaseServiceInt<ProductRatingDTO> {

	/**
	 * Finds ProductRating by name.
	 * 
	 * @param name
	 * @return
	 */
	public ProductRatingDTO findByName(String name, UserContext userContext);

}

package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.SubCategoryDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface SubCategoryServiceInt extends BaseServiceInt<SubCategoryDTO> {

	/**
	 * Finds SubCategory by name.
	 * 
	 * @param name
	 * @return
	 */
	public SubCategoryDTO findByName(String name, UserContext userContext);

}

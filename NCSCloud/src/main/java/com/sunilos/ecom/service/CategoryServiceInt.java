package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.CategoryDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CategoryServiceInt extends BaseServiceInt<CategoryDTO> {

	/**
	 * Finds CategoryManagement by name.
	 * 
	 * @param name
	 * @return
	 */
	public CategoryDTO findByName(String name, UserContext userContext);

}

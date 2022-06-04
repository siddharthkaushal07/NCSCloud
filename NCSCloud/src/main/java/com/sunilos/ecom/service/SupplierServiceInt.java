package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.SupplierDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface SupplierServiceInt extends BaseServiceInt<SupplierDTO> {

	/**
	 * Finds Supplier by name.
	 * 
	 * @param name
	 * @return
	 */
	public SupplierDTO findByName(String name, UserContext userContext);

}

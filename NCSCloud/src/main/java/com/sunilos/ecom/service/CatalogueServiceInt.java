package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.CatalogueDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CatalogueServiceInt extends BaseServiceInt<CatalogueDTO> {

	/**
	 * Finds Catalogue by name.
	 * 
	 * @param name
	 * @return
	 */
	public CatalogueDTO findByName(String name, UserContext userContext);

}

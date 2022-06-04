package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.ecom.dto.ComplainDTO;
import com.sunilos.common.UserContext;


/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface ComplainServiceInt extends BaseServiceInt<ComplainDTO> {

	/**
	 * Finds Complain by name.
	 * 
	 * @param name
	 * @return
	 */
	public ComplainDTO findByName(String name, UserContext userContext);

}

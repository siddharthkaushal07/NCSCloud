package com.sunilos.ecom.service;

import java.util.List;
import com.sunilos.common.*;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.EnquiryDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface EnquiryServiceInt extends BaseServiceInt<EnquiryDTO> {

	/**
	 * Finds enquiry by name.
	 * 
	 * @param name
	 * @return
	 */
	public EnquiryDTO findByName(String name, UserContext userContext);

}

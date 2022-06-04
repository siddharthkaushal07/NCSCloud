package com.sunilos.ecom.dao;
import com.sunilos.common.BaseDAOInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.ProductPriceDTO;


/**
 * ProductPrice DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface ProductPriceDAOInt extends BaseDAOInt<ProductPriceDTO> {

	public ProductPriceDTO findByPId(long pk, UserContext userContext);

	
}

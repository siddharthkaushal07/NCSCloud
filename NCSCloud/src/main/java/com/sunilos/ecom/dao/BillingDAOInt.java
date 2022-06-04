package com.sunilos.ecom.dao;

import java.util.List;

import com.sunilos.common.BaseDAOInt;
import com.sunilos.ecom.dto.BillingDTO;;

public interface BillingDAOInt extends BaseDAOInt<BillingDTO>{

	public List<BillingDTO> getMaxBid();
	

}

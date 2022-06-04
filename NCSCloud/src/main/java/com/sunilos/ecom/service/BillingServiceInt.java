package com.sunilos.ecom.service;

import java.util.List;

import com.sunilos.common.BaseServiceInt;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.BillingDTO;


public interface BillingServiceInt extends BaseServiceInt<BillingDTO>{

	public BillingDTO findByName(String name, UserContext userContext);

	public List<BillingDTO> getMaxBid();
}

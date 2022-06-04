package com.sunilos.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.common.UserContext;
import com.sunilos.common.exception.DuplicateRecordException;
import com.sunilos.ecom.dao.BillingDAOInt;
import com.sunilos.ecom.dto.BillingDTO;

@Service
public class BillingServiceImpl extends BaseServiceImpl<BillingDTO, BillingDAOInt> implements BillingServiceInt{

	@Autowired
	BillingDAOInt billingDaoInt;
	
	@Override
	public BillingDTO findByName(String name, UserContext userContext) {
		// TODO Auto-generated method stub
		return baseDao.findByUniqueKey("name", name, userContext);
	}
	
	
	
	@Transactional(readOnly = false)
	public long addBill(BillingDTO dto, UserContext userContext) throws DuplicateRecordException {
		long pk = baseDao.add(dto, userContext);
		return pk;
	}



	@Override
	public List<BillingDTO> getMaxBid() {
		System.out.println("inside get max id inside billing service==============");
		return billingDaoInt.getMaxBid();
	}

}

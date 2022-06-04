package com.sunilos.ecom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.ecom.dto.SellerRegistrationDTO;


@Repository
public class SellerRegistrationDAOImpl extends BaseDAOImpl<SellerRegistrationDTO> implements SellerRegistrationDAOInt {

	@Override
	public Class<SellerRegistrationDTO> getDTOClass() {
		return SellerRegistrationDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(SellerRegistrationDTO dto, CriteriaBuilder builder, Root<SellerRegistrationDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			//whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

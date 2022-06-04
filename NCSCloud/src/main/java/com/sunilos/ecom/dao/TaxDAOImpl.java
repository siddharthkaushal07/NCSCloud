package com.sunilos.ecom.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.TaxDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaxDAOImpl extends BaseDAOImpl<TaxDTO> implements TaxDAOInt {

	@Override
	public Class<TaxDTO> getDTOClass() {
		return TaxDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(TaxDTO dto, CriteriaBuilder builder, Root<TaxDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

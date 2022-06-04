package com.sunilos.ecom.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.DomainDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DomainDAOImpl extends BaseDAOImpl<DomainDTO> implements DomainDAOInt {

	@Override
	public Class<DomainDTO> getDTOClass() {
		return DomainDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(DomainDTO dto, CriteriaBuilder builder, Root<DomainDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
//		if (dto.getId()>0) {
//
//			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
//		}

		return whereCondition;
	}
}

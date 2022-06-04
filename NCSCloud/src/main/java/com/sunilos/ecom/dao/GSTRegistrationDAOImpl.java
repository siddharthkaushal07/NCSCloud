package com.sunilos.ecom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.ecom.dto.GSTRegistrationDTO;


@Repository
public class GSTRegistrationDAOImpl extends BaseDAOImpl<GSTRegistrationDTO> implements GSTRegistrationDAOInt {

	@Override
	public Class<GSTRegistrationDTO> getDTOClass() {
		return GSTRegistrationDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(GSTRegistrationDTO dto, CriteriaBuilder builder, Root<GSTRegistrationDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getGstNo())) {

			//whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

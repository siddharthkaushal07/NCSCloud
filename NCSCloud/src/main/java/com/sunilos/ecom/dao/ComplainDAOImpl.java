package com.sunilos.ecom.dao;

import org.springframework.stereotype.Repository;
import com.sunilos.ecom.dto.ComplainDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ComplainDAOImpl extends BaseDAOImpl<ComplainDTO> implements ComplainDAOInt {

	@Override
	public Class<ComplainDTO> getDTOClass() {
		return ComplainDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ComplainDTO dto, CriteriaBuilder builder, Root<ComplainDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

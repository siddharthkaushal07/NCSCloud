package com.sunilos.ecom.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.EnquiryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnquiryDAOImpl extends BaseDAOImpl<EnquiryDTO> implements EnquiryDAOInt {

	@Override
	public Class<EnquiryDTO> getDTOClass() {
		return EnquiryDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(EnquiryDTO dto, CriteriaBuilder builder, Root<EnquiryDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

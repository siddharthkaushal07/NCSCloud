package com.sunilos.ecom.dao;

import org.springframework.stereotype.Repository;
import com.sunilos.ecom.dto.SupplierDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class SupplierDAOImpl extends BaseDAOImpl<SupplierDTO> implements SupplierDAOInt {

	@Override
	public Class<SupplierDTO> getDTOClass() {
		return SupplierDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(SupplierDTO dto, CriteriaBuilder builder, Root<SupplierDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

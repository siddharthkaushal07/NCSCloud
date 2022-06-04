package com.sunilos.ecom.dao;

import org.springframework.stereotype.Repository;
import com.sunilos.ecom.dto.ShippingDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ShippingDAOImpl extends BaseDAOImpl<ShippingDTO> implements ShippingDAOInt {

	@Override
	public Class<ShippingDTO> getDTOClass() {
		return ShippingDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ShippingDTO dto, CriteriaBuilder builder, Root<ShippingDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

package com.sunilos.ecom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.ecom.dto.WishListDTO;


@Repository
public class WishListDAOImpl extends BaseDAOImpl<WishListDTO> implements WishListDAOInt {

	@Override
	public Class<WishListDTO> getDTOClass() {
		return WishListDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(WishListDTO dto, CriteriaBuilder builder, Root<WishListDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			//whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

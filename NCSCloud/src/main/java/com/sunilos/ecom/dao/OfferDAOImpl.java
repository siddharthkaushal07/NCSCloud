package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.OfferDTO;
import com.sunilos.ecom.dto.ProductDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferDAOImpl extends BaseDAOImpl<OfferDTO> implements OfferDAOInt {

	@Autowired
	ProductDAOInt pdao;

	@Override
	public Class<OfferDTO> getDTOClass() {
		return OfferDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(OfferDTO dto, CriteriaBuilder builder, Root<OfferDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}

	@Override
	protected void populate(OfferDTO dto, UserContext userContext) {

		if (dto.getProductId() != null) {
			ProductDTO productDTO = pdao.findByPK(dto.getProductId(), userContext);
			if (productDTO != null) {
				dto.setName(productDTO.getName());
			}
		}

	}
}

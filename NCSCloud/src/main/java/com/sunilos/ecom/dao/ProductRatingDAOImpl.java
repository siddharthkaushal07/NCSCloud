package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.dto.ProductRatingDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductRatingDAOImpl extends BaseDAOImpl<ProductRatingDTO> implements ProductRatingDAOInt {

	@Override
	public Class<ProductRatingDTO> getDTOClass() {
		return ProductRatingDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ProductRatingDTO dto, CriteriaBuilder builder, Root<ProductRatingDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getComment())) {
			whereCondition.add(builder.like(qRoot.get("comment"), "%" + dto.getComment() + "%"));
		}

		if (!isZeroNumber(dto.getRating())) {
			whereCondition.add(builder.equal(qRoot.get("rating"), dto.getRating()));
		}

		return whereCondition;
}

	@Autowired
	ProductDAOInt productdao = null;

	@Override
	protected void populate(ProductRatingDTO dto, UserContext userContext) {
		ProductDTO productDTO = productdao.findByPK(dto.getProduct_id(), userContext);
		if (productDTO != null) {
			dto.setName(productDTO.getName());
		}
	}


}

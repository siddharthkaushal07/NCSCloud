package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CatalogueDTO;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.ecom.dto.ShoppingCartDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCartDAOImpl extends BaseDAOImpl<ShoppingCartDTO> implements ShoppingCartDAOInt {

	@Override
	public Class<ShoppingCartDTO> getDTOClass() {
		return ShoppingCartDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ShoppingCartDTO dto, CriteriaBuilder builder,
			Root<ShoppingCartDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		
		if (!isEmptyString(dto.getProductName())) {

			whereCondition.add(builder.like(qRoot.get("productName"), dto.getProductName() + "%"));
		}


		return whereCondition;
	}
	
	@Autowired
	ProductPriceDAOInt ppdi;

//	@Override
//	protected void populate(ShoppingCartDTO dto, UserContext userContext) {
//		if (dto.getProductPriceId() > 0) {
//			ProductPriceDTO productDTO = ppdi.findByPK(dto.getProductPriceId(), userContext);
//			dto.setName(productDTO.getName());
//		}
//	}

}

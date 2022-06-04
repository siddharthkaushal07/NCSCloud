package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.common.*;
import com.sunilos.common.exception.DuplicateRecordException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductModelDAOImpl extends BaseDAOImpl<ProductModelDTO> implements ProductModelDAOInt {

	@Override
	public Class<ProductModelDTO> getDTOClass() {
		return ProductModelDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ProductModelDTO dto, CriteriaBuilder builder,
			Root<ProductModelDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getModel_no())) {

			whereCondition.add(builder.like(qRoot.get("model_no"), dto.getModel_no() + "%"));
		}

		return whereCondition;
	}

	@Autowired
	ProductDAOInt productdao = null;

	@Override
	protected void populate(ProductModelDTO dto, UserContext userContext) {
		ProductDTO productDTO = productdao.findByPK(dto.getProduct_id(), userContext);
		if (productDTO != null) {
			dto.setName(productDTO.getName());
		}
	}

	@Override
	protected void checkDuplicate(ProductModelDTO dto, UserContext userContext) {

		System.out.println("checkduplicate----");
		ProductModelDTO dto1 = new ProductModelDTO();
		dto1.setModel_no(dto.getModel_no());
		List list = super.search(dto1, userContext);
		System.out.println(list + "------");
		if (!list.isEmpty()) {
			System.out.println("runnnn------");
			ORSResponse res = new ORSResponse();
			res.setSuccess(false);
			res.addMessage("Model Number Already Exist");
			throw new DuplicateRecordException("Model number already exists");

		}
	}

}

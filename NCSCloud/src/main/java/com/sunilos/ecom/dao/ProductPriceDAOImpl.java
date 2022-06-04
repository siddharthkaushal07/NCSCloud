package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.DomainDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductPriceDAOImpl extends BaseDAOImpl<ProductPriceDTO> implements ProductPriceDAOInt {

	@Override
	public Class<ProductPriceDTO> getDTOClass() {
		return ProductPriceDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ProductPriceDTO dto, CriteriaBuilder builder, Root<ProductPriceDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		
		if (dto.getProduct_id()>0) {
			
			System.out.println("hhhhhh"+ dto.getProduct_id());

			whereCondition.add(builder.equal(qRoot.get("product_id"), dto.getProduct_id()));
		}
		if (!isEmptyString(dto.getModel_no())) {

			whereCondition.add(builder.like(qRoot.get("model_no"), dto.getModel_no() + "%"));
		}

		return whereCondition;
	}

	@Autowired
	ProductDAOInt productdao = null;
	
	@Autowired
	ProductModelDAOInt productmodeldao = null;
	
	@Override
	protected void populate(ProductPriceDTO dto, UserContext userContext) {
		if(dto.getProduct_id()>0) {
			ProductDTO productDTO = productdao.findByPK(dto.getProduct_id(), userContext);
			dto.setName(productDTO.getName());
			}
			if(dto.getModel_id()>0) {
				ProductModelDTO productmodelDTO = productmodeldao.findByPK(dto.getModel_id(), userContext);
				dto.setModel_no(productmodelDTO.getModel_no());
				}
			
		}
	
	public ProductPriceDTO findByPId(long pk, UserContext userContext) {
		ProductPriceDTO dto = entityManager.find(getDTOClass(), pk);
		return dto;
	}


}




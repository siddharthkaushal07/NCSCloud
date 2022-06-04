package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.DomainDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl extends BaseDAOImpl<ProductDTO> implements ProductDAOInt {

	@Override
	public Class<ProductDTO> getDTOClass() {
		return ProductDTO.class;
	}
	

	@Autowired
	CategoryDAOInt CategoryDao = null;
	
	@Autowired
	SubCategoryDAOInt subCategoryDao = null;
	
	@Override
	protected void populate(ProductDTO dto, UserContext userContext) {
		if(dto.getCategory_id()>0) {
		CategoryDTO categoryDTO = CategoryDao.findByPK(dto.getCategory_id(), userContext);
		dto.setCategory_name(categoryDTO.getName());
		}
		if(dto.getSubcategory_id()>0) {
			SubCategoryDTO subcategoryDTO = subCategoryDao.findByPK(dto.getSubcategory_id(), userContext);
			dto.setSubcategory_name(subcategoryDTO.getName());
			}
		
	}
	
	

	@Override
	protected List<Predicate> getWhereClause(ProductDTO dto, CriteriaBuilder builder, Root<ProductDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {

			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}
		
		if (dto.getCategory_id()>0) {

			whereCondition.add(builder.equal(qRoot.get("category_id"), dto.getCategory_id()));
		}
		
		if (dto.getSubcategory_id()>0) {

			whereCondition.add(builder.equal(qRoot.get("subcategory_id"), dto.getSubcategory_id()));
		}

		return whereCondition;
	}
}

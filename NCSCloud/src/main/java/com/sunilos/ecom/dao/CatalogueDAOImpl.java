package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sunilos.ecom.dto.CatalogueDTO;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.dto.ProductRatingDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CatalogueDAOImpl extends BaseDAOImpl<CatalogueDTO> implements CatalogueDAOInt {

	@Override
	public Class<CatalogueDTO> getDTOClass() {
		return CatalogueDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CatalogueDTO dto, CriteriaBuilder builder, Root<CatalogueDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}

	@Autowired
	ProductDAOInt productdao = null;
	
	@Autowired
	CategoryDAOInt CategoryDao = null;
	
	@Autowired
	SubCategoryDAOInt subCategoryDao = null;
	
		@Override
		protected void populate(CatalogueDTO dto, UserContext userContext) {
			if(dto.getProduct_id()>0) {
				ProductDTO productDTO = productdao.findByPK(dto.getProduct_id(), userContext);
				dto.setName(productDTO.getName());
				}
			
			if(dto.getCategory_id()>0) {
			CategoryDTO categoryDTO = CategoryDao.findByPK(dto.getCategory_id(), userContext);
			dto.setCategory_name(categoryDTO.getName());
			}
			if(dto.getSubcategory_id()>0) {
				SubCategoryDTO subcategoryDTO = subCategoryDao.findByPK(dto.getSubcategory_id(), userContext);
				dto.setSubcategory_name(subcategoryDTO.getName());
				}
		}

		
	}		




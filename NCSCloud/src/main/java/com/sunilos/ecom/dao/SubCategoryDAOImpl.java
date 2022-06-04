package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.OfferDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubCategoryDAOImpl extends BaseDAOImpl<SubCategoryDTO> implements SubCategoryDAOInt {

	@Autowired
	CategoryDAOInt cdao=null;
	
	@Override
	public Class<SubCategoryDTO> getDTOClass() {
		return SubCategoryDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(SubCategoryDTO dto, CriteriaBuilder builder, Root<SubCategoryDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if(isNotNull(dto.getCategory_id())){
			whereCondition.add(builder.equal(qRoot.get("category_id"), dto.getCategory_id()));
		}
		return whereCondition;
	}

	@Override
	protected void populate(SubCategoryDTO dto, UserContext userContext) {

		if (dto.getCategory_id() != null) {
		  CategoryDTO categoryDTO = cdao.findByPK(dto.getCategory_id(), userContext);
			if (categoryDTO != null) {
				dto.setCategory_name(categoryDTO.getName());
			}
		}
	}
}

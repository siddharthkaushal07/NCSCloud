package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.DomainDTO;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl extends BaseDAOImpl<CategoryDTO> implements CategoryDAOInt {

	
	@Override
	public Class<CategoryDTO> getDTOClass() {
		return CategoryDTO.class;
	}
	
	
	@Autowired
	DomainDAOInt DomainService = null;
	
	@Override
	protected void populate(CategoryDTO dto, UserContext userContext) {
		DomainDTO domainDTO = DomainService.findByPK(dto.getDomain_Id(), userContext);
		if (domainDTO != null) {
			dto.setDomain_name(domainDTO.getName());
		}
	}
	

	@Override
	protected List<Predicate> getWhereClause(CategoryDTO dto, CriteriaBuilder builder, Root<CategoryDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		
		if (isNotNull(dto.getDomain_Id())) {

			whereCondition.add(builder.equal(qRoot.get("domain_Id"), dto.getDomain_Id()));
		}
		/*
		 * if (dto.getDomain_Id()>0) {
		 * 
		 * whereCondition.add(builder.equal(qRoot.get("domain_Id"),
		 * dto.getDomain_Id())); }
		 */

		return whereCondition;
	}
}
	
	
	
	




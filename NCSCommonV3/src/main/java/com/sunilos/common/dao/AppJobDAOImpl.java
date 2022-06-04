package com.sunilos.common.dao;

import org.springframework.stereotype.Repository;
import com.sunilos.common.dto.AppJobDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AppJobDAOImpl extends BaseDAOImpl<AppJobDTO> implements AppJobDAOInt {

	@Override
	public Class<AppJobDTO> getDTOClass() {
		return AppJobDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(AppJobDTO dto, CriteriaBuilder builder, Root<AppJobDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			//whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

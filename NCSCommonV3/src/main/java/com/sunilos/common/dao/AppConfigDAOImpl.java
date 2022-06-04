package com.sunilos.common.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.common.dto.AppConfigDTO;

@Repository
public class AppConfigDAOImpl extends BaseDAOImpl<AppConfigDTO> implements AppConfigDAOInt {

	@Override
	public Class<AppConfigDTO> getDTOClass() {
		return AppConfigDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(AppConfigDTO dto, CriteriaBuilder builder, Root<AppConfigDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getParamName())) {
			whereCondition.add(builder.like(qRoot.get("paramName"), dto.getParamName() + "%"));
		}

		if (!isEmptyString(dto.getParamValue())) {
			whereCondition.add(builder.like(qRoot.get("paramValue"), "%" + dto.getParamValue() + "%"));
		}

		return whereCondition;
	}
}

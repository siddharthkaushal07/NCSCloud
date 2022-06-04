package com.sunilos.common.dao;

import org.springframework.stereotype.Repository;
import com.sunilos.common.dto.JobLogDTO;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;


@Repository
public class JobLogDAOImpl extends BaseDAOImpl<JobLogDTO> implements JobLogDAOInt {

	@Override
	public Class<JobLogDTO> getDTOClass() {
		return JobLogDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(JobLogDTO dto, CriteriaBuilder builder, Root<JobLogDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			//whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}

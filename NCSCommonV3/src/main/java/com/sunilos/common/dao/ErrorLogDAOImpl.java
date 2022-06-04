package com.sunilos.common.dao;

import org.springframework.stereotype.Repository;
import com.sunilos.common.dto.ErrorLogDTO;
import com.sunilos.common.util.DataValidator;
import com.sunilos.common.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ErrorLogDAOImpl extends BaseDAOImpl<ErrorLogDTO> implements ErrorLogDAOInt {

	@Override
	public Class<ErrorLogDTO> getDTOClass() {
		return ErrorLogDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ErrorLogDTO dto, CriteriaBuilder builder, Root<ErrorLogDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (DataValidator.isNotNull(dto.getType())) {
			whereCondition.add(builder.equal(qRoot.get("type"), dto.getType()));
		}

		if (DataValidator.isNotNull(dto.getMessage())) {
			whereCondition.add(builder.like(qRoot.get("message"), "%" + dto.getMessage() + "%"));
		}

		return whereCondition;
	}

	@Override
	protected void populate(ErrorLogDTO dto, UserContext userContext) {
		if (dto != null && dto.getMessage() != null) {
			String str = dto.getMessage();
			if (str.length() > 4000) {
				str = str.substring(0, 4000);
			}
			dto.setMessage(str);
		}
	}

	public static void main(String[] args) {
		String str = "1234567890";
		System.out.println(str.substring(0, 4000));
	}

}

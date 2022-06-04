package com.sunilos.ecom.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.BillingDTO;
import com.sunilos.ecom.dto.OrderDTO;

@Repository
public class BillingDAOImpl extends BaseDAOImpl<BillingDTO> implements BillingDAOInt  {

	@Override
	public Class<BillingDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return BillingDTO.class;
	}


	

	
	/**
	 * Add a record
	 */
	public long addBill(BillingDTO dto, UserContext userContext) {
		
		System.out.println("inside AddBill billDAO impl-------------");
		checkDuplicate(dto, userContext);
		dto.setCreatedBy(userContext.getLoginId());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		dto.setSkey(new Date().getTime());

		// System.out.println("In base DTO add 11" + dto.toString());

		if (dto.getOrgId() == null) {
			dto.setOrgId(userContext.getOrgId());
			dto.setOrgName(userContext.getOrgName());
		}

		if (dto.getAppId() == null) {
			dto.setAppId(userContext.getAppId());
		}

		populate(dto, userContext);
		entityManager.persist(dto);
		return dto.getId();
	}
	@Override
	protected List<Predicate> getWhereClause(BillingDTO dto, CriteriaBuilder builder, Root<BillingDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}





	@Override
	public List<BillingDTO> getMaxBid() {
		
		System.out.println("inside billing dao getmaxbid");
		List list = null;
		/*
		* int pk = 0;
		*
		* pk = manager.createQuery("SELECT MAX(price) FROM OrderDTO").getFirstResult();
		*/

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq= entityManager.getCriteriaBuilder().createQuery();
		Root c = cq.from(BillingDTO.class);
		cq.select(cb.max(c.get("id")));
		TypedQuery t = entityManager.createQuery(cq);

		list = t.getResultList();



		return list;
	}

	
}

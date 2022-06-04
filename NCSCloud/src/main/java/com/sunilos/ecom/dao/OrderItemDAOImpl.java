package com.sunilos.ecom.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.common.UserContext;
import com.sunilos.common.exception.DuplicateRecordException;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.dto.OrderItemDTO;


@Repository
public class OrderItemDAOImpl extends BaseDAOImpl<OrderItemDTO> implements OrderItemDAOInt {

	@Override
	public Class<OrderItemDTO> getDTOClass() {
		return OrderItemDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(OrderItemDTO dto, CriteriaBuilder builder, Root<OrderItemDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getProductName())) {

			whereCondition.add(builder.like(qRoot.get("productName"), dto.getProductName() + "%"));
		}
		
		if(!isZeroNumber(dto.getOrder_id())) {
			whereCondition.add(builder.equal(qRoot.get("order_id"), dto.getOrder_id()));
		}

		return whereCondition;
	}
	
	
	@Override
	public long placeOrder(OrderItemDTO dto, UserContext userContext) {

		System.out.println("inside save basedao impl-------------");
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
	public List<OrderItemDTO> getOrderNo() {

	List list = null;
	/*
	* int pk = 0;
	*
	* pk = manager.createQuery("SELECT MAX(price) FROM OrderDTO").getFirstResult();
	*/

	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery cq= entityManager.getCriteriaBuilder().createQuery();
	Root c = cq.from(OrderItemDTO.class);
	cq.select(cb.max(c.get("order_id")));
	TypedQuery t = entityManager.createQuery(cq);

	list = t.getResultList();



	return list;

	}

	


}

package com.sunilos.ecom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.OrderDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<OrderDTO> implements OrderDAOInt {

	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	public Class<OrderDTO> getDTOClass() {
		return OrderDTO.class;
	}
	
	
		
	
	@Override
	protected List<Predicate> getWhereClause(OrderDTO dto, CriteriaBuilder builder, Root<OrderDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();
		System.out.println("inside order_daoimpl----------"+dto.getDescription());
		if (!isEmptyString(dto.getDescription())) {

			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}
		
		if (!isZeroNumber(dto.getOrder_id())) {

			whereCondition.add(builder.equal(qRoot.get("order_id"), dto.getOrder_id() ));
		}

		return whereCondition;
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		return max();
	}

	@Override
	public TypedQuery<OrderDTO> getMaxOrder() {
		
		return entityManager.createQuery("SELECT * FROM OrderDTO",
		          OrderDTO.class);
	}

	@Override
	public String findHighestSalary() {
		System.out.println("-- Dept with max salary --");
        entityManager = entityManagerFactory.createEntityManager();
TypedQuery<String> query = entityManager.createQuery("SELECT max(price) FROM OrderDTO",
                String.class);
        String dept = query.getSingleResult();
        System.out.println(dept);
        return dept;
        
	}




	@Override
	public long addOrder(OrderDTO dto, UserContext userContext) {
		
		
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
	

	
	
	
	
	
	
	
}

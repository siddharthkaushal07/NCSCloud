package com.sunilos.common;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sunilos.common.dto.UserDTO;
import com.sunilos.common.exception.DuplicateRecordException;
import com.sunilos.common.util.DataUtility;

public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		// this.sessionFactory =
		// entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
	}

	/**
	 * Find record by Unique key
	 * 
	 * @param attribute
	 * @param val
	 * @param dtoClass
	 * @return
	 */
	public T findByUniqueKey(String attribute, Object val, UserContext userContext) {

		Class<T> dtoClass = getDTOClass();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(dtoClass);

		Root<T> qRoot = cq.from(dtoClass);

		Predicate condition = builder.equal(qRoot.get(attribute), val);

		/*
		 * if ( userContext != null && !isZeroNumber(userContext.getOrgId())) {
		 * Predicate conditionGrp = builder.equal(qRoot.get("orgId"),
		 * userContext.getOrgId()); cq.where(condition, conditionGrp); } else {
		 * cq.where(condition); }
		 */
		cq.where(condition);

		TypedQuery<T> query = entityManager.createQuery(cq);

		List<T> list = query.getResultList();

		T dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}

		return dto;

	}

	public T findByPK(long pk, UserContext userContext) {
		T dto = entityManager.find(getDTOClass(), pk);
		return dto;
	}

	public T findBySkey(long skey, UserContext userContext) {
		return findByUniqueKey("skey", skey, userContext);
	}

	/**
	 * Build criteria query
	 * 
	 * @param dto
	 * @return
	 */
	protected TypedQuery<T> createCriteria(T dto, UserContext userContext) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// Create criteria
		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		// Columns information
		Root<T> qRoot = cq.from(getDTOClass());

		// Column of query
		cq.select(qRoot);

		// Create where conditions
		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);

		// Put organization filter
		if (dto.isGroupFilter() && userContext.getOrgId() != null && !isSuperUser(userContext)) {
			// System.out.println("------>" + dto.getClass());
			whereClause.add(builder.equal(qRoot.get("orgId"), userContext.getOrgId()));
		}

		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));

		List<Order> orderBys = getOrderByClause(dto, builder, qRoot);

		cq.orderBy(orderBys.toArray(new Order[orderBys.size()]));

		TypedQuery<T> query = entityManager.createQuery(cq);

		return query;

	}

	/**
	 * Get record count of search query
	 * 
	 * @param dto
	 * @param userContext
	 * @return
	 */
	protected Long getRecordCount(T dto, UserContext userContext) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// Create criteria
		CriteriaQuery<Long> cq = builder.createQuery(Long.class);

		Root<T> qRoot = cq.from(getDTOClass());

		cq.select(builder.count(qRoot));

		// Columns information

		// Create where conditions
		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);

		// Put organization filter
		if (dto.isGroupFilter() && !isSuperUser(userContext)) {
			System.out.println("------>" + dto.getClass());
			whereClause.add(builder.equal(qRoot.get("orgId"), userContext.getOrgId()));
		}

		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));

		return entityManager.createQuery(cq).getSingleResult();
	}

	/**
	 * Creates WHERE clause of search
	 * 
	 * @param dto
	 * @param builder
	 * @param qRoot
	 * @return
	 */
	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		TypedQuery<T> query = createCriteria(dto, userContext);

		System.out.println(" PAGE ->>>>>>>>>>>>>>>>" + pageNo + " --- " + pageSize);

		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		List list = query.getResultList();

		if (pageSize > 0) {

			System.out.println("Record count query started");

			Long recordCount = getRecordCount(dto, userContext);

			System.out.println("Record count are " + recordCount);

			list.add(recordCount);
		}

		return list;
	}

	public List search(T dto, UserContext userContext) {
		return search(dto, 0, 0, userContext);
	}

	/**
	 * Run HQL query
	 * 
	 * @param hql
	 * @param userContext
	 * @return
	 */
	public List runHQL(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		List l = q.getResultList();
		return l;
	}

	/**
	 * Add a record
	 */
	public long add(T dto, UserContext userContext) {
		
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

	/**
	 * Populate redundant values into dto. Overridden by chiled classes.
	 * 
	 * @param dto
	 */
	protected void populate(T dto, UserContext userContext) {

	}

	/**
	 * Update a record
	 */
	public void update(T dto, UserContext userContext) {
		checkDuplicate(dto, userContext);
		System.out.println("inside update BaseDaoImpl-----=-=-=-=-=-");
		T existDto = findByPK(dto.getId(), userContext);
System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiii"+existDto);
		dto.setOrgId(existDto.getOrgId());
		dto.setOrgName(existDto.getOrgName());
		dto.setAppId(existDto.getAppId());
		dto.setSkey(existDto.getSkey());

		dto.setCreatedBy(existDto.getCreatedBy());
		dto.setCreatedDatetime(existDto.getCreatedDatetime());

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		if (dto.getSkey() == null || dto.getSkey() == 0) {
			dto.setSkey(new Date().getTime());
		}
		populate(dto, userContext);
		entityManager.merge(dto);
	}

	/**
	 * Check unique keys
	 * 
	 * @param dto
	 */
	protected void checkDuplicate(T dto, UserContext userContext) {
		LinkedHashMap<String, Object> uniqueKeys = dto.uniqueKeys();
		System.out.println("uniqueKeys = = =    ----------- >>>> "+uniqueKeys);
		if (uniqueKeys == null) {
			return;
		}
		uniqueKeys.forEach((key, value) -> {
			System.out.println("key inside basedao impl-------------"+key);
			T dtoExist = findByUniqueKey(key, value, userContext);
//			System.out.println("dtoExist== "+ dtoExist);
//			System.out.println("dtoExist Id = >>>>>> "+dtoExist.getId());
//			System.out.println("dto Id=====>>>>> "+dto.getId());
//			if(dtoExist==null) {
//				System.out.println("dtoExist is null");
//			}
//			if(dto.getId()!=dtoExist.getId()) {
//				System.out.println("id is not equal");
//				System.out.println(dto.getId());
//				System.out.println(dtoExist.getId());
//			}else {
//				System.out.println("bnoth RE SAME");
//				
//			}
			
			if (dtoExist != null && dto.getId() != dtoExist.getId()) {
				throw new DuplicateRecordException(key + " already exists");
			}
		});
	}

	/**
	 * Delete a record
	 */
	public void delete(T dto, UserContext userContext) {
		entityManager.remove(dto);
	}

	/**
	 * Get DTO Class object
	 * 
	 * @return
	 */
	public abstract Class<T> getDTOClass();

	/**
	 * Check empty string
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isZeroNumber(Double val) {
		return val == null || val == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */

	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}

	protected boolean isNotNull(Object val) {
		return val != null;
	}

	/**
	 * Get order by clause
	 * 
	 * @param dto
	 * @param builder
	 * @param qRoot
	 * @return
	 */
	protected List<Order> getOrderByClause(T dto, CriteriaBuilder builder, Root<T> qRoot) {

		// Apply Order by clause

		LinkedHashMap<String, String> map = dto.orderBY();

		List<Order> orderBys = new ArrayList<Order>();

		if (map != null) {
			map.forEach((key, value) -> {
				if (value.equals("asc")) {
					orderBys.add(builder.asc(qRoot.get(key)));
				} else {
					orderBys.add(builder.desc(qRoot.get(key)));
				}
			});
		}

		return orderBys;
	}

	/**
	 * Check is logged in user is superuser of NCS
	 * 
	 * @param userContext
	 * @return
	 */
	private boolean isSuperUser(UserContext userContext) {
		if (userContext == null) {
			return false;
		}
		if ("NCS".equals(userContext.getOrgName()) && "Superadmin".equals(userContext.getRoleName())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get user by auth key. Database must create "user_auth_view" view
	 * 
	 */
	public UserDTO getUserByAuthKey(Long authKey) {

		String sql = "SELECT auth_id,user_id,user_login,org_id,org_name,app_id,app_name	 FROM user_auth_view where auth_id = "
				+ authKey;

		Query query = entityManager.createNativeQuery(sql);

		// query.setParameter(1, authKey);
		List<Object[]> list = query.getResultList();

		UserDTO dto = null;

		if (list.size() > 0) {
			Object[] row = list.get(0);
			dto = new UserDTO();
			dto.setId(Long.parseLong(row[1].toString()));
			dto.setLoginId(row[2].toString());
			dto.setOrgId(Long.parseLong(row[3].toString()));
			dto.setOrgName(row[4].toString());
		}
		return dto;

	}

	/**
	 * Import data
	 * 
	 * @param filePath
	 * @param ctx
	 * @throws Exception
	 */
	public void importData(String filePath, UserContext ctx) throws Exception {

		String row = null;
		String[] cols = null;
		String[] setMethods = null;
		String[] getMethods = null;
		String[] vals = null;

		File f = new File(filePath);
		Scanner sc = new Scanner(f);

		// Get header
		if (sc.hasNext()) {
			row = sc.nextLine();
			cols = row.split(",");
			setMethods = new String[cols.length];
			getMethods = new String[cols.length];
			for (int i = 0; i < cols.length; i++) {
				setMethods[i] = "set" + cols[i].substring(0, 1).toUpperCase() + cols[i].substring(1);
				getMethods[i] = "get" + cols[i].substring(0, 1).toUpperCase() + cols[i].substring(1);
			}
		}

		List<T> list = new ArrayList<T>();
		T dto = null;

		while (sc.hasNext()) {
			dto = getDTOClass().newInstance();
			row = sc.nextLine();
			vals = row.split(",");
			Method sm = null;
			Method gm = null;
			for (int i = 0; i < setMethods.length; i++) {
				gm = dto.getClass().getMethod(getMethods[i], null);
				Class returnType = gm.getReturnType();
				String typeName = returnType.getName();
				sm = dto.getClass().getMethod(setMethods[i], returnType);
				System.out.println(returnType.getName());
				if ("java.lang.String".equals(typeName)) {
					sm.invoke(dto, vals[i]);
				} else if ("java.lang.Long".equals(typeName)) {
					sm.invoke(dto, DataUtility.getLong(vals[i]));
				} else if ("java.lang.Integer".equals(typeName)) {
					sm.invoke(dto, DataUtility.getInteger(vals[i]));
				}
			}
			list.add(dto);
		}

	}

}

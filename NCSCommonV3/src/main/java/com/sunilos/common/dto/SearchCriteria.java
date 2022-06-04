package com.sunilos.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SearchCriteria  {

	private HashMap<String, Object> attributes = new HashMap<String, Object>();
	
	private ArrayList<SearchCriteria.OrderByElement> orderByList = new ArrayList<OrderByElement>();
	
	private String orderBy = null;

	private boolean asc;

	private int page = 0;

	private int noOfRecords = 0;

	private boolean pagging = false;

	private boolean nextPageExist = false;
	
	private boolean isRandom=false;

	private Class dto;

	private String[] columns; // Column in query
	private String hqlQuery; // Column in query

	private String[] labels; // Labels in query

	public SearchCriteria() {
	}

	public SearchCriteria(Class dto) {
		this.dto = dto;
	}

	public Class getDto() {
		return dto;
	}

	public void setDto(Class dto) {
		this.dto = dto;
	}

	/**
	 * Use getOrderByList()
	 * @return the orderBy
	 */
	@Deprecated
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Use setOrderByList(String attribute)
	 * @param orderBy
	 * 
	 */
	@Deprecated 
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public ArrayList<SearchCriteria.OrderByElement> getOrderByList() {
		return orderByList;
	}

	public void setOrderByList(ArrayList<SearchCriteria.OrderByElement> orderByList) {
		this.orderByList = orderByList;
	}

	/**
	 * Add attribute that will be ordered in Ascending form
	 * @param attribute
	 */
	public void setOrderByList(String attribute) {
		this.orderByList.add(new SearchCriteria.OrderByElement(attribute));
	}

	/**
	 * Add attribute that will be ordered 
	 * @param attribute
	 * @param isAsc : Asc if true
	 */
	public void setOrderByList(String attribute, boolean isAsc) {
		this.orderByList.add(new SearchCriteria.OrderByElement(attribute,isAsc));
	}

	
	/**
	 * @return the asc
	 */
	@Deprecated
	public boolean isAsc() {
		return asc;
	}

	/**
	 * @param asc
	 *            the asc to set
	 */
	@Deprecated
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * By default equal condition is added in Criteria query. If value is of
	 * String type then like condition is added. A prefix character can be added
	 * with key to change default equal condition. *** Prefix : Condition No
	 * Prefix : Equal - : No equal > : Greater Than < : Less Than >= : Greater
	 * Than Equals to <= : Less Than Equals to
	 * 
	 * @param key -
	 *            Attribute Name
	 * @param value -
	 *            Attribute Value
	 */

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	public Object removeAttribute(String key) {
		return attributes.remove(key);
	}

	public void removeAllAttribute() {
		attributes.clear();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 0) {
			this.page = 0;
		} else {
			this.page = page;
		}
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public boolean isPagging() {
		return pagging;
	}

	public void setPagging(boolean pagging) {
		this.pagging = pagging;
	}

	public boolean isNextPageExist() {
		return nextPageExist;
	}

	public void setNextPageExist(boolean nextPageExist) {
		this.nextPageExist = nextPageExist;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String getHqlQuery() {
		return hqlQuery;
	}

	public void setHqlQuery(String hqlQuery) {
		this.hqlQuery = hqlQuery;
	}

	public HashMap<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public static class OrderByElement{
		public boolean asc = true ;
		public String attribute = null ;
		
		public OrderByElement(String attribute){
			this.attribute = attribute;
		}
		
		public OrderByElement(String attribute, boolean isAsc){
			this.attribute = attribute;
			asc = isAsc;
		}
		
	}

	public boolean isRandom() {
		return isRandom;
	}

	public void setRandom(boolean isRandom) {
		this.isRandom = isRandom;
	}

}

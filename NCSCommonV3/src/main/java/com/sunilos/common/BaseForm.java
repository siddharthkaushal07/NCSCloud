package com.sunilos.common;

import java.lang.reflect.Method;

/**
 * Contains generic attributes of a form. It is extended by all form beans.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class BaseForm {

	/**
	 * Contains non-business primary key
	 */
	protected Long id = null;

	private Long[] ids;

	/**
	 * Current page number, 0 is first page index
	 */
	private int pageNo = 0;

	/**
	 * Number of records displayed on list page
	 */
	private int pageSize = 5;

	/**
	 * Value of button clicked
	 */
	private String operation;

	/**
	 * Created by User ID
	 */
	protected String createdBy;

	/**
	 * Modified by User ID
	 */
	protected String modifiedBy;

	/**
	 * Record created datetime
	 */
	protected long createdDatetime;

	/**
	 * Record last modified datetime
	 */
	protected long modifiedDatetime;

	protected Long orgId;
	protected String orgName;
	protected Long skey;
	protected Long appId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public long getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(long createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public long getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(long modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getSkey() {
		return skey;
	}

	public void setSkey(Long skey) {
		this.skey = skey;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	/**
	 * Converts from into dto.
	 * 
	 * @return
	 */
	public BaseDTO getDto() {
		return null;
	}
	
	public BaseDTO getForm() {
		return null;
	}

	/**
	 * Initialize DTO
	 * 
	 * @param dto
	 * @return
	 */
	public <T extends BaseDTO> T initDTO(T dto) {
		if (id != null && id > 0) {
			dto.setId(id);
		} else {
			dto.setId(null);
		}
		dto.setAppId(appId);
		dto.setOrgId(orgId);
		dto.setOrgName(orgName);
		dto.setSkey(skey);
		return dto;
	}

	
	public BaseForm getForm2() {
		
		return  null;
	}
	
	/*
	 * public < T extends BaseDTO> initDTO(T dto) { return new UserDTO(); }
	 */

	/**
	 * Converts fto into form.
	 * 
	 * @param bDto
	 */
	public void populate(BaseDTO bDto) {
		id = bDto.getId();
		skey = bDto.getSkey();
		appId = bDto.getAppId();
		orgId = bDto.getOrgId();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Method[] ms = this.getClass().getMethods();

		String mName = null;
		for (int i = 0; i < ms.length; i++) {
			mName = ms[i].getName();
			if (mName.startsWith("get")) {
				try {
					buffer.append("\n\t" + mName + " = " + ms[i].invoke(this, (Object[]) null));
				} catch (Exception e) {
				}
			}

		}
		return buffer.toString();
	}
}

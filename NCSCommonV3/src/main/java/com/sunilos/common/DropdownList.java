package com.sunilos.common;

/**
 * DropdownList interface is implemented by DTOs those are used to create drop
 * down list on HTML pages
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface DropdownList {

	/**
	 * Returns primary key as string
	 * 
	 * @return key
	 */
	public String getKey();

	/**
	 * Returns display text of list element
	 * 
	 * @return value
	 */
	public String getValue();
	
	/**
	 * Get primary key id as long
	 * @return
	 */
	public Long getId() ;
	
	
}
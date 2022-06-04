package com.sunilos.common;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dropdown list adapter
 * 
 * @author Sunil Sahu
 *
 */
public class DropDownListAdapter implements DropdownList {

	private String key = null;
	private String value = null;
	private long id = 0;

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public DropDownListAdapter(String k, String v) {
		key = k;
		value = v;
		id = Long.parseLong(k);
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Get a dropdown list
	 * 
	 * @param dpList
	 * @return
	 */
	public static List<DropDownListAdapter> getList(List<DropdownList> dpList) {
		if (dpList == null) {
			return null;
		}
		List<DropDownListAdapter> list = dpList.stream().map(e -> {
			return new DropDownListAdapter(e.getKey() + "", e.getValue());
		}).sorted(Comparator.comparing(DropDownListAdapter::getValue)).collect(Collectors.toList());
		return list;
	}

}

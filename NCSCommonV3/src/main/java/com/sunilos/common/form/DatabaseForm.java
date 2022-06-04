package com.sunilos.common.form;

import com.sunilos.common.BaseForm;

public class DatabaseForm extends BaseForm {

	public DatabaseForm() {
	}

	protected int limit = 50;

	protected int rowCount = 0;

	protected int colCount = 0;

	/**
	 * Contains name of file
	 */
	protected String sql = null;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

}

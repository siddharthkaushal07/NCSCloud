package com.sunilos.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SQL utility is used to build SQL queries
 * 
 * @author DELL
 *
 */
public class SQLUtility {

	/**
	 * Get string to append in SQL where clause
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {
		return "'" + str + "'";
	}

	// Convert date to string to append in SQL where clause
	public static String getString(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return getString(formatter.format(d));
	}

	// Convert int to string to append in SQL where clause
	public static String getString(int i) {
		return String.valueOf(i);
	}

	// Convert long to string to append in SQL where clause
	public static String getString(long i) {
		return String.valueOf(i);
	}

	// Convert double to string to append in SQL where clause
	public static String getString(double d) {
		return String.valueOf(d);
	}

	// Convert object to string to append in SQL where clause
	public static String getString(Object o) {
		if (o != null) {
			return getString(o.toString());
		} else {
			return null;
		}
	}

}

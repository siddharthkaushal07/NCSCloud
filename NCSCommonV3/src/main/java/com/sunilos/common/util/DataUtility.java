package com.sunilos.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

/**
 * Convert data into different formats 
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class DataUtility {

	public static String DATE_FORMAT_IND = "dd/MM/yyyy";
	public static String DATE_FORMAT = "MM/dd/yyyy";
	public static String DATE_FORMAT_MYSQL = "yyyy-MM-dd";
	private static final String USER_AGENT = "Mozilla/5.0";

	/**
	 * Parse date from given formatted string
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDate(String date, String format) {
		Date convertedDate = null;
		if (date != null)
			try {
				SimpleDateFormat targetFormat = new SimpleDateFormat(format);
				convertedDate = targetFormat.parse(date);
			} catch (ParseException localParseException) {
			}
		return convertedDate;
	}

	public static int getInteger(String v) {
		int i = 0;
		try {
			i = Integer.parseInt(v);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	public static long getLong(String v) {
		long i = 0;
		try {
			i = Long.parseLong(v);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	public static double getDouble(String v) {
		double d = 0;
		try {
			d = Double.parseDouble(v);
		} catch (Exception e) {
			d = 0;
		}
		return d;
	}

	public static float getFloat(String v) {
		float d = 0;
		try {
			d = Float.parseFloat(v);
		} catch (Exception e) {
			d = 0;
		}
		return d;
	}

	/**
	 * Converts Date to string in given format
	 * 
	 * @param indate
	 * @param format
	 * @return
	 */
	public static String getDate(Date indate, String format) {
		String dateString = null;

		SimpleDateFormat sdfr = new SimpleDateFormat(format);
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}

	public static Date getDate(String date) {
		return getDate(date, DATE_FORMAT);
	}

	public static String getDate(Date d) {
		return getDate(d, "MM/dd/yyyy");
	}

	public static String convertStringToDate(Date d) {
		return getDate(d, DATE_FORMAT_IND);
	}

	public static Date convertDateFormat(String date) {
		return getDate(date, "dd-MM-yyyy");
	}

	/**
	 * Get the HTTP URL Connection and Return the JSON Response.
	 * 
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public static String getOutput(HttpURLConnection con) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String str1 = response.toString();
		System.out.println(response.toString());
		return str1;
	}

	/**
	 * Send post request
	 * 
	 * @param postURL
	 * @return
	 * @throws Exception
	 */
	public static HttpURLConnection callPostMethod(String postURL) throws Exception {
		URL obj = new URL(postURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("content-type", "application/json");

		JSONObject json = new JSONObject();
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(json.toString().getBytes());
		os.flush();
		os.close();

		return con;
	}

	public static String encodeString(String val) {
		return new String(Base64.encodeBase64(val.getBytes()));
	}

	public static String decodeString(String val) {
		return new String(Base64.decodeBase64(val.getBytes()));
	}

}

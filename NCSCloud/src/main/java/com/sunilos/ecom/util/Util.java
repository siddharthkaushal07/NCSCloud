package com.sunilos.ecom.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Format Input data.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class Util {

	public static String DATE_FORMAT_IND = "dd/MM/yyyy";
	public static String DATE_FORMAT = "MM/dd/yyyy";
	public static String DATE_FORMAT_MYSQL = "yyyy-MM-dd";

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

	public static String convertToIndianCurrency(String num) {
		BigDecimal bd = new BigDecimal(num);
		long number = bd.longValue();
		long no = bd.longValue();
		int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
		int digits_length = String.valueOf(no).length();
		int i = 0;
		ArrayList<String> str = new ArrayList<>();
		HashMap<Integer, String> words = new HashMap<>();
		words.put(0, "");
		words.put(1, "One");
		words.put(2, "Two");
		words.put(3, "Three");
		words.put(4, "Four");
		words.put(5, "Five");
		words.put(6, "Six");
		words.put(7, "Seven");
		words.put(8, "Eight");
		words.put(9, "Nine");
		words.put(10, "Ten");
		words.put(11, "Eleven");
		words.put(12, "Twelve");
		words.put(13, "Thirteen");
		words.put(14, "Fourteen");
		words.put(15, "Fifteen");
		words.put(16, "Sixteen");
		words.put(17, "Seventeen");
		words.put(18, "Eighteen");
		words.put(19, "Nineteen");
		words.put(20, "Twenty");
		words.put(30, "Thirty");
		words.put(40, "Forty");
		words.put(50, "Fifty");
		words.put(60, "Sixty");
		words.put(70, "Seventy");
		words.put(80, "Eighty");
		words.put(90, "Ninety");
		String digits[] = { "", "Hundred", "Thousand", "Lakh", "Crore" };
		while (i < digits_length) {
			int divider = (i == 2) ? 10 : 100;
			number = no % divider;
			no = no / divider;
			i += divider == 10 ? 1 : 2;
			if (number > 0) {
				int counter = str.size();
				// String plural = (counter > 0 && number > 9) ? "s" : "";
				String plural = (counter > 0 && number > 9) ? "" : "";
				String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural
						: words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " "
								+ words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;
				str.add(tmp);
			} else {
				str.add("");
			}
		}

		Collections.reverse(str);
		String Rupees = String.join(" ", str).trim();

		String paise = (decimal) > 0
				? " And Paise " + words.get(Integer.valueOf((int) (decimal - decimal % 10))) + " "
						+ words.get(Integer.valueOf((int) (decimal % 10)))
				: "";
		return "Rupees " + Rupees + paise + " Only";
	}

	/**
	 * Count the weekend days.
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int countWeekendDays(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		// Note that month is 0-based in calendar, bizarrely.
		calendar.set(year, month - 1, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		int count = 0;
		for (int day = 1; day <= daysInMonth; day++) {
			calendar.set(year, month - 1, day);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
				count++;
				// Or do whatever you need to with the result.
			}
		}
		return count;
	}

	/**
	 * Days in Month
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int dayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		// Note that month is 0-based in calendar, bizarrely.
		calendar.set(year, month - 1, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;
	}

	/**
	 * Get sunday in month
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getSunday(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		// Note that month is 0-based in calendar, bizarrely.
		calendar.set(year, month - 1, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		int count = 0;
		for (int day = 1; day <= daysInMonth; day++) {
			calendar.set(year, month - 1, day);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SUNDAY) {
				count++;
				// Or do whatever you need to with the result.
			}
		}
		return count;
	}

	/**
	 * Get saturday in month
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getSaturday(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		// Note that month is 0-based in calendar, bizarrely.
		calendar.set(year, month - 1, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		int count = 0;
		for (int day = 1; day <= daysInMonth; day++) {
			calendar.set(year, month - 1, day);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SATURDAY) {
				count++;
			}
		}
		return count;
	}

	// Get month number from string in java (January = 01)
	public static int getMonthNumber(String month) {
		return Month.valueOf(month.toUpperCase()).getValue();
	}

	public static float getfloat(Object ob) {
		if (ob == null || ob == "") {
			return new Float(0.0);
		}
		if (ob instanceof BigDecimal) {
			return ((BigDecimal) ob).floatValue();
		}
		if (ob instanceof Float) {
			return ((Float) ob).floatValue();
		}

		if (ob instanceof BigInteger) {
			return ((BigInteger) ob).floatValue();
		}

		if (ob instanceof Double) {
			return ((Double) ob).floatValue();
		}

		if (ob instanceof Integer) {
			return ((Integer) ob).floatValue();
		}

		if (ob instanceof String) {
			return Float.valueOf((String) ob);
		}
		return new Float(0.0);
	}

	public static float checkNotNull(Float f) {
		if (f != null) {
			if (f > 0) {
				return f;
			}
		}
		return 0;
	}

	public static void main(String[] args) {

		float d = 1;
		float d1 = 1.6544F;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.print(df.format(d) + "\n");
		System.out.print("d1 : " + df.format(d1));

		String str = "/Attachment/download/{id}/{iii}";
		String[] arrOfStr = str.split("/", 2);
		for (String a : arrOfStr) {
			System.out.println(a);
		}
		System.out.println("==== " + arrOfStr.toString());

		// Save amount
		/*
		 * SalaryVoucherDTO dto = new SalaryVoucherDTO(); String colName = "setH" + 1;
		 * // Setter methods name try { Method headerMethod =
		 * SalaryVoucherDTO.class.getMethod(colName, Float.class);
		 * headerMethod.invoke(dto, 101F); } catch (Exception e) { e.printStackTrace();
		 * }
		 */
		// System.out.println(dto.getH1());
	}

	public static String getYouTubeId(String videoURL) {
		// TODO Auto-generated method stub
		return null;
	}

}

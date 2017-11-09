package com.senla.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static String dateToString(Date date) {
		if (date == null)
			return null;
		return dateFormat.format(date);
	}

	public static Date stringToDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}

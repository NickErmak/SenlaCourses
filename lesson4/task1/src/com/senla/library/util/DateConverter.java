package com.senla.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	public static String dateToString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}

	public static Date stringToDate(String date) throws ParseException {
		if (date.equals("null"))
			return null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.parse(date);
	}
}

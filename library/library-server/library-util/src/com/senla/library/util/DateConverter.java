package com.senla.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateConverter {
	private static Logger logger = Logger.getLogger(DateConverter.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static String dateToString(Date date) {
		if (date == null)
			return null;
		return dateFormat.format(date);
	}

	public static Date stringToDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
	}
	
	public static Date minusMonth(int monthQuantity) {
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH, -monthQuantity);
		return c.getTime();
	}
}

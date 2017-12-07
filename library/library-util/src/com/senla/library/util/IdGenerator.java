package com.senla.library.util;

public class IdGenerator {
	private static int id = 100;
	public static final int BOOK_ID_LAST_DIGIT = 1;
	public static final int ORDER_ID_LAST_DIGIT = 2;
	public static final int REQUEST_ID_LAST_DIGIT = 3;

	public static int generateId() {
		return 10 * (id += 1);
	}
}

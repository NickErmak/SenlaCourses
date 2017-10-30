package com.senla.library.util;

public class IdGenerator {

	private static int id = 100;

	public static synchronized int generateId() {
		return 10 * (id += 1);
	}

}

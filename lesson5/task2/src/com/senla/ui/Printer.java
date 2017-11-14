package com.senla.ui;

import java.io.IOException;

public class Printer {
	
	private static final String RETURN_BACK = "0. return back;";

	public static void print(Menu menu) {
		resetScreen();
		System.out.println(menu.getName());
		for (MenuItem item : menu.getMenuItems())
			System.out.println(item);
		System.out.println(RETURN_BACK);
	}

	private static void resetScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}

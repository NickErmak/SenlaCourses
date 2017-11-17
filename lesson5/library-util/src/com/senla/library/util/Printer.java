package com.senla.library.util;

import java.io.IOException;
import java.util.List;

import com.senla.library.bean.IBook;
import com.senla.library.bean.IEntity;
import com.senla.library.ui.IMenu;
import com.senla.library.ui.IMenuItem;
import com.senla.library.ui.IResponse;

public class Printer {

	public static void print(String message) {
		System.out.println(message);
	}

	public static void print(IEntity entity) {
		System.out.println(entity);
	}

	public static void print(List<? extends IEntity> list) {
		for (IEntity entity : list) {
			print(entity);
		}
	}

	public static void printQuery(List<IBook> bookList) {
		for (IBook book : bookList)
			print(book.getTitle() + " - " + book.getOrderBookList().size() + " pcs ");
	}

	public static void print(IMenu menu) {
		resetScreen();
		System.out.println("Library Manager");
		System.out.println(menu.getName());
		IMenuItem[] menuItem = menu.getMenuItems();
		for (int i = 1; i <= menuItem.length; i++)
			System.out.println(i + ". " + menuItem[i - 1]);
	}

	public static void print(IResponse response) {
		System.out.println(response);
		if (response.getList() != null)
			for (IEntity entity : response.getList()) {
				System.out.println(entity);
			}
	}

	private static void resetScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

}

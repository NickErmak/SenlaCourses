package com.senla.library.util;

import java.io.IOException;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;

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

	public static void print(IMenu menu, ConsoleMessage consoleMessage) {
		resetScreen();
		print(consoleMessage.toString());
		print(ConsoleMessage.PROGRAM_TITLE.toString());
		print(ConsoleMessage.DIVIDER.toString());
		print(menu.getName());
		List<IMenuItem> menuItem = menu.getMenuItems();
		for (int i = 1; i <= menuItem.size(); i++)
			print(i + ". " + menuItem.get(i - 1) + ";");
		if (!consoleMessage.equals(ConsoleMessage.START))
			print(ConsoleMessage.RETURN_ITEM.toString());
	}

	public static void print(IResponse response) {
		resetScreen();
		print(response.toString());
		if (response.getEntities() != null)
			for (IEntity entity : response.getEntities()) {
				print(entity);
			}
		if (!response.isExit())
			print(ConsoleMessage.RETURN_ITEM.toString());
	}
	
	public static void print(IQuery query) {
		resetScreen();
		print(query.toString());
	}

	private static void resetScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

}

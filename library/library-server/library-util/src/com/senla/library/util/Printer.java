package com.senla.library.util;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.library.api.bean.IEntity;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.api.ui.ConsoleBuilder;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;

public class Printer {
	private static Logger logger = Logger.getLogger(FileWorker.class);

	private static void resetScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			logger.error(e);
		}
	}

	public static void print(String message) {
		System.out.println(message);
	}

	public static void print(IEntity entity) {
		print(entity.toString());
	}

	public static void print(List<? extends IEntity> list) {
		for (IEntity entity : list) {
			print(entity);
		}
	}

	public static void print(IMenu menu, ConsoleMessage consoleMessage) {
		resetScreen();
		print(consoleMessage.toString());
		print(ConsoleBuilder.PROGRAM_TITLE.toString());
		print(ConsoleBuilder.DIVIDER.toString());
		print(menu.getName());
		List<IMenuItem> menuItem = menu.getMenuItems();
		for (int i = 1; i <= menuItem.size(); i++)
			print(i + ". " + menuItem.get(i - 1) + ";");
		if (!consoleMessage.equals(ConsoleMessage.START))
			print(ConsoleBuilder.RETURN_ITEM.toString());
	}

	public static void print(IQuery query) {
		print(query.toString());
	}

	public static void print(IResponse response) {
		resetScreen();
		print(response.toString());
		if (response.getEntities() != null) {
			for (IEntity entity : response.getEntities()) {
				print(entity);
			}
		}
		if (response.hasReturnOption()) {
			print(ConsoleBuilder.RETURN_ITEM.toString());
		}
	}
}

package com.senla.library.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.senla.library.ui.menu.Menu;
import com.senla.library.util.Printer;

public class MenuController {

	private Builder builder;
	private Navigator navigator;
	private Scanner scanner;

	public MenuController(Menu rootMenu) {
		builder = new Builder(rootMenu);
		navigator = new Navigator(rootMenu);
		scanner = new Scanner(System.in);
	}

	public void run() {
		try {
			navigator.printMenu();
			IQuery query = navigator.navigate(scanner.nextInt());
			if (query == null || !builder.buildMenu(query))
				run();
		} catch (NoSuchElementException e) {
			scanner.next();
			run(ConsoleMessage.INPUT_ERROR.toString());
		}
	}

	private void run(String message) {
		Printer.print(message);
		run();
	}

}

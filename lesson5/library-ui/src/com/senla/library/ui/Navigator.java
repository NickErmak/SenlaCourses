package com.senla.library.ui;

import com.senla.library.ui.IQuery;
import com.senla.library.ui.menu.Menu;
import com.senla.library.ui.menu.MenuItem;
import com.senla.library.util.Printer;

public class Navigator {

	private Menu currentMenu;

	public Navigator(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public void printMenu() {
		Printer.print(currentMenu);
	}

	public IQuery navigate(Integer index) {
		MenuItem menuItem = currentMenu.getMenuItems()[--index];
		if (menuItem.getNextMenu() != null) 
			currentMenu = menuItem.getNextMenu();
		return menuItem.doAction();
	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

}

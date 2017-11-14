package com.senla.ui;

public class Navigator {

	private Menu currentMenu;
	private Menu previousMenu;

	public Navigator(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public void printMenu() {
		Printer.print(currentMenu);
	}

	public void navigate(Integer index) {
		if (index == 0)
			returnBack();
		else {
			previousMenu = currentMenu;
			currentMenu = currentMenu.getMenuItems()[--index].getNextMenu();
		}
	}

	private void returnBack() {
		currentMenu = previousMenu;
		previousMenu = null;
	}

}

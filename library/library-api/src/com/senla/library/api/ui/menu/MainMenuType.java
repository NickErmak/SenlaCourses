package com.senla.library.api.ui.menu;

public enum MainMenuType {

	BOOK("Book menu"), ORDER("Order menu"), REQUEST("Request menu"), TOTAL("Total info"), EXIT("Exit");

	String menuItem;

	private MainMenuType(String menuItem) {
		this.menuItem = menuItem;
	}

	public String toString() {
		return menuItem;
	}
}

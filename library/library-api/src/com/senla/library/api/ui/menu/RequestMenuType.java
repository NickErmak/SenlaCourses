package com.senla.library.api.ui.menu;

public enum RequestMenuType {
	ADD("Add"), EXPORT("Export to file"), IMPORT("Import from file");

	private String menuItem;

	private RequestMenuType(String menuItem) {
		this.menuItem = menuItem;
	}

	public String toString() {
		return menuItem;
	}
}

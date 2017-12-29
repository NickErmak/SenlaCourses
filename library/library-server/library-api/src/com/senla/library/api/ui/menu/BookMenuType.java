package com.senla.library.api.ui.menu;

public enum BookMenuType {
	ADD("Add new"), WRITE_OFF("Write off"), SHOW_ALL("Show all"), SHOW_UNSOLD("Show unsold books"), 
	SHOW_QUERY("Show current queries"), SHOW_DESCRIPTION("Show description"), EXPORT("Export to file"),
	IMPORT("Import from file");

	private String menuItem;

	private BookMenuType(String menuItem) {
		this.menuItem = menuItem;
	}

	public String toString() {
		return menuItem;
	}
}

package com.senla.library.api.ui.menu;

public enum OrderCloneMenuType {
	CHANGE_NAME("Change name"), CHANGE_DATE("Change date"), CHANGE_STATUS("Change status");
	
	private String menuItem;

	private OrderCloneMenuType(String menuItem) {
		this.menuItem = menuItem;
	}

	public String toString() {
		return menuItem;
	}
}

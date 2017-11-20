package com.senla.library.api.ui.menu;

public enum TotalMenuType {
	
	SHOW_TOTAL_INCOME("Show total income");
	
	String menuItem;

	private TotalMenuType(String menuItem) {
		this.menuItem = menuItem;
	}

	public String toString() {
		return menuItem;
	}
}

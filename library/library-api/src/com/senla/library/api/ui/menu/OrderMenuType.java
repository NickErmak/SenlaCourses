package com.senla.library.api.ui.menu;

public enum OrderMenuType {
	
	ADD("Add"), ADD_BOOK_TO_ORDER("Add book to order"), COMPLETE("Complete"), 
	CANCEL("Cancel"), SHOW_DETAILS("Show details"), CLONE ("Clone"), SHOW_ALL("Show all"), 
	SHOW_FOR_DATE_PERIOD("Show for date period"), SHOW_COMPLETED_QUANTITY("Calculate quantity of completed orders");
	
	String menuItem;

	private OrderMenuType(String menuItem) {
		this.menuItem = menuItem;
	}

	public String toString() {
		return menuItem;
	}
}

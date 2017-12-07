package com.senla.library.api.transmitter.response;

public enum ResponseOrderMessage {	
	ADD("Add new order operation status: "),
	ADD_BOOK("Add book to order operation status: "),
	CANCEL("Cancel order operation status: "),
	COMPLETE("Complete order operation status: "),
	SHOW_DETAILS("Order details: "),
	SHOW_ALL_BY_DATE("Orders sorted by execution date:"),
	SHOW_ALL_BY_PRICE("Orders sorted by total amount"),
	SHOW_ALL_BY_STATUS("Order sorted by status"),
	ORDER_COMPLETED_QUANTITY("Quantity of completed orders = "),
	CLONE("Clone operation status: "),
	EXPORT("Export operation status: "),
	IMPORT("Import operation status: ");
	
	private String message;

	private ResponseOrderMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

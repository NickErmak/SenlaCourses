package com.senla.library.api.transmitter.query;

public enum QueryInputMessage {

	ADD_BOOK("Enter book information using -- as divider (\"title--dd/mm/yyyy--price--description\")"),
	GET_BOOK_ID("Enter book id"),
	ADD_ORDER("Enter order name"),
	GET_ORDER_ID("Enter order id"),
	ADD_BOOK_TO_ORDER("Enter order id and book id (\"orderID--bookID\")"),
	INPUT_DATES("Enter date-from and date-to (\"dd/mm/yyyy-dd/mm/yyyy\")");
	
	String message;

	private QueryInputMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

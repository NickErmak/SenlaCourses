package com.senla.library.api.transmitter.response;

public enum ResponseBookMessage {

	ADD("Add new book operation status: "),
	SHOW_DESCRIPTION("Book description: "),
	SHOW_ALL_APHABETICALLY("Books sorted alphabetically:"),
	SHOW_ALL_BY_DATE("Books sorted by publication date:"),
	SHOW_ALL_BY_PRICE("Books sorted by price:"),
	SHOW_ALL_BY_STOCK("Books sorted by stock:"),
	WRITE_OFF("Write off operation status: "),
	UNSOLD_BOOKS("Unsold books for 6 months:");
	
	String message;

	private ResponseBookMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

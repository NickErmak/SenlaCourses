package com.senla.library.api.ui;

public enum ConsoleMessage {
	START(""), NO_MESSAGE(""), SUCCESS("SUCCESS"), ERROR_INCORRECT_INPUT("Incorrect input. Please repeat!"), 
	ERROR_NO_SUCH_ITEM("No such number. Please make your choise again!"), 
	ERROR_NO_SUCH_ID("ID doesn't exist!"), ERROR_NO_CLONEABLE("Can't clone order!"),
	ERROR_OUT_OF_STOCK("Books out of stock. Make a request!");

	private String message;

	private ConsoleMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

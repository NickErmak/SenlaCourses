package com.senla.library.ui.transmission;

public enum ResponseMessage {

	EXIT("Changes saved. Close the program");

	String message;

	private ResponseMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

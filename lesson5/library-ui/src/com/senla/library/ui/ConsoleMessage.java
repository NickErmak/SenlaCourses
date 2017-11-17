package com.senla.library.ui;

public enum ConsoleMessage {
	
	INPUT_ERROR ("Incorrect input. Please repeat");

	String message;

	private ConsoleMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}


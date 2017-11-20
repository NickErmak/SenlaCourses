package com.senla.library.api.ui;

public enum ConsoleMessage {
	
	START (""),
	NO_MESSAGE (""),
	ERROR_INCORRECT_INPUT ("Incorrect input. Please repeat!"),
	ERROR_NO_SUCH_ITEM ("No such number. Please make your choise again!"),
	ERROR_NO_SUCH_ID ("ID doesn't exist!"),
	PROGRAM_TITLE ("Library Manager"),
	DIVIDER ("===================================="),
	RETURN_ITEM ("0. return;");
	
	String message;

	private ConsoleMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}


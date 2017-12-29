package com.senla.library.api.ui;

public enum ConsoleBuilder {
	PROGRAM_TITLE("Library Manager"), DIVIDER("===================================="), RETURN_ITEM("0. return;");

	private String message;

	private ConsoleBuilder(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

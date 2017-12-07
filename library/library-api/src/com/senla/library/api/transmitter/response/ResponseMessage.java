package com.senla.library.api.transmitter.response;

public enum ResponseMessage {
	EXIT("Save data operation status: "),
	TOTAL_SHOW_INCOME("Total income for saled books = ");

	private String message;

	private ResponseMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

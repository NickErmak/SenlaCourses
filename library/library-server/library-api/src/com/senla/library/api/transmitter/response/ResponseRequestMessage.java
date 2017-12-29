package com.senla.library.api.transmitter.response;

public enum ResponseRequestMessage {
	ADD("Add new order operation status: "), EXPORT("Export operation status: "), IMPORT("Import operation status: ");

	private String message;

	private ResponseRequestMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

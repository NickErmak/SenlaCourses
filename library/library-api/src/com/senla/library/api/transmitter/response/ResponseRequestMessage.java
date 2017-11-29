package com.senla.library.api.transmitter.response;

public enum ResponseRequestMessage {
	
	ADD("Add new order operation status: ");
	
	String message;

	private ResponseRequestMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

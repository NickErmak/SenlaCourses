package com.senla.library.api.facade;

public enum ExecutionType {
	
	SUCCESS("success"),
	ERROR("error");
	
	String message;

	private ExecutionType(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}

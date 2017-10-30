package com.senla.library.entity;

public enum Status {

	PROCESSING("processing"), COMPLETED("completed"), CANCELLED("cancelled");

	String statusType;

	private Status(String statusType) {
		this.statusType = statusType;
	}

	public String toString() {
		return statusType;
	}

	public static Status getStatus(String status) {
		if (status.equals(PROCESSING.statusType))
			return PROCESSING;
		else if (status.equals(COMPLETED.statusType))
			return COMPLETED;
		else if (status.equals(CANCELLED.statusType))
			return CANCELLED;
		else
			return null;
	}

}

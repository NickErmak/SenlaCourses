package com.senla.library.api.bean;

public enum OrderStatus {
	PROCESSING("processing"), COMPLETED("completed"), CANCELLED("cancelled");

	private String statusType;

	private OrderStatus(String statusType) {
		this.statusType = statusType;
	}	

	public static OrderStatus getStatus(String status) {
		if (status.equals(PROCESSING.statusType))
			return PROCESSING;
		else if (status.equals(COMPLETED.statusType))
			return COMPLETED;
		else if (status.equals(CANCELLED.statusType))
			return CANCELLED;
		else
			return null;
	}

	public String toString() {
		return statusType;
	}
}
